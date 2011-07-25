package com.ig.demoApp

import grails.plugin.spock.UnitSpec
import spock.lang.Unroll

class PresentationServiceSpec extends UnitSpec {

    @Unroll("#sno Instance Test")
    def "Get Instance Test"() {

        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)
        def presentationService = new PresentationService()

        when:
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
        BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        BootCampSession bootCampSessionInstance = presentationService.getInstance(bootCampSessionId)
        println bootCampSession.id

        then:
        bootCampSessionInstance.toString() == value

        where:
        sno | value          | bootCampSessionId
        1   | "Unit Testing" | 1
        2   | "null"         | 10

    }

    @Unroll("#sno Another Instance Test")
    def "Get Another Instance Test"() {

        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)
        def businessLogicService = Mock(BusinessLogicService)
        businessLogicService.getPresentationInstance(_) >> {new BootCampSession(name: "Unit Testing")}
        def presentationService = new PresentationService()
        presentationService.businessLogicService = businessLogicService

        when:
        BootCampSession bootCampSessionInstance = presentationService.getAnotherInstance(bootCampSessionId)

        then:
        bootCampSessionInstance.toString() == value

        where:
        sno | value          | bootCampSessionId
        1   | "Unit Testing" | 3
        2   | "Unit Testing" | 10
    }

}


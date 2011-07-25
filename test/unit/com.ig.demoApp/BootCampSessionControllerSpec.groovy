package com.ig.demoApp

import grails.plugin.spock.ControllerSpec
import spock.lang.Unroll

class BootCampSessionControllerSpec extends ControllerSpec {

    @Unroll("Edit Test #sno")
    def "edit action of BootCamp Session Controller with Mock()"() {

        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)
        def presentationService = Mock(PresentationService)
        presentationService.getInstance(_) >> {
            Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
            BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        }
        controller.presentationService = presentationService

        when:
        mockParams.id = id
        def model = controller.edit()

        then:
        model.toString() == passedModel
        redirectArgs.action == action

        where:

        sno | message | id | passedModel                              | action
        1   | null    | 1  | "[bootCampSessionInstance:Unit Testing]" | null
        2   | null    | 2  | "[bootCampSessionInstance:Unit Testing]" | null
    }

    @Unroll("Edit Test MockFor #sno")
    def "edit action of BootCamp Session Controller with MockFor()"() {

        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)
        def presentationService = mockFor(PresentationService)
        presentationService.demand.getInstance() { id ->
            Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
            BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        }
        controller.presentationService = presentationService.createMock()

        when:
        mockParams.id = id
        def model = controller.edit()

        then:
        controller.flash.message == message
        model.toString() == passedModel
        redirectArgs.action == action

        where:
        sno | message | id | passedModel                              | action
        1   | null    | 3  | "[bootCampSessionInstance:Unit Testing]" | null
        2   | null    | 4  | "[bootCampSessionInstance:Unit Testing]" | null
    }

    @Unroll("Edit Test MockFor #sno")
    def "Config passed in Model action of BootCamp Session Controller with MockFor()"() {

        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)
        def presentationService = mockFor(PresentationService)
        presentationService.demand.getInstance() {
            Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
            BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        }
        controller.presentationService = presentationService.createMock()

        when:
        mockParams.id = id
        mockConfig("demoApp{maxPresentations=2}")
        def model = controller.actionPassesConfigInModel()

        then:
        println model.toString()
        controller.flash.message == message
        model.toString() == passedModel
        redirectArgs.action == action

        where:
        sno | message | id | passedModel                                                  | action
        1   | null    | 3  | "[bootCampSessionInstance:Unit Testing, maxPresentations:2]" | null
        2   | null    | 4  | "[bootCampSessionInstance:Unit Testing, maxPresentations:2]" | null
    }
}


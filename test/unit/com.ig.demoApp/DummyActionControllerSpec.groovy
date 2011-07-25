package com.ig.demoApp

import grails.plugin.spock.ControllerSpec
import spock.lang.Unroll

class DummyActionControllerSpec extends ControllerSpec {


    @Unroll("JSON test #sno")
    def "JSON validity check"() {
        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)

        when:
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
        BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        mockParams.name = presentationName
        def ab = controller.checkBootCampSessionValidityJSON()

        then:
        mockResponse.contentAsString == expectedResult
        where:

        sno << [1, 2]
        presentationName << ["Unit Testing", 'Jmeter Testing']
        expectedResult << ['{"success":true}', '{"success":false}']

    }

    @Unroll("XML test #sno")
    def "XML validity check"() {
        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)

        when:
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
        BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        mockParams.name = presentationName
        def ab = controller.checkBootCampSessionValidityXML()

        then:
        mockResponse.contentAsString.contains(expectedResult)
        println "XML : " + mockResponse.contentAsString

        where:
        sno | presentationName | expectedResult
        1   | "Unit Testing"   | """<map><entry key="success">true</entry></map>"""
        2   | "Jmeter Testing" | """<map><entry key="success">false</entry></map>"""

    }

    @Unroll("AJAX test #sno")
    def "AJAX validity check"() {
        setup:
        mockDomain(BootCampSession)
        mockDomain(Presenter)

        when:
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
        BootCampSession bootCampSession = new BootCampSession(presenter: presenter, name: "Unit Testing", description: "Smallest Module Level Testing").save(flush: true)
        mockParams.name = presentationName
        def ab = controller.checkBootCampSessionValidityAJAX()
        println "AJAX : " + mockResponse.contentAsString

        then:
        mockResponse.contentAsString == expectedResult

        where:

        [sno, presentationName, expectedResult] << [[1, "Unit Testing", "true"], [2, "JMeter Testing", "false"]]

    }

    @Unroll("Forward action test")
    def "Forward Action test"() {
        setup:
        controller.forwardTestAction()

        expect:
        forwardArgs.action == 'renderTestAction'
    }

    def "Redirect Action test"() {
        setup:
        controller.redirectTestAction()

        expect:
        redirectArgs.controller == 'presenter'
        redirectArgs.action == 'list'
    }

    def "Render Action test"() {
        setup:
        controller.renderTestAction()

        expect:
        renderArgs.model.toString() == '[presenter:Farid]'
        renderArgs.model.presenter.toString() == 'Farid'
        renderArgs.view == '/index'
    }

    def "Throwing exception when Presenter not Found"() {
        given:
        mockDomain(Presenter)
        mockDomain(BootCampSession)
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
        mockParams.name = "Manoj"
        controller.throwExceptionForPresenter()

        expect:
        notThrown(RuntimeException)

        when:
        mockParams.name = "Sachin"
        controller.throwExceptionForPresenter()

        then:
        RuntimeException exception = thrown()
        exception.message == "Presenter not Found"
    }


}


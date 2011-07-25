package com.ig.demoApp

import grails.plugin.spock.TagLibSpec

class DemoAppTagLibSpec extends TagLibSpec {

    DemoAppTagLib demoAppTagLib = new DemoAppTagLib()

    def "test Message Appearing On HomePage"() {

        setup:
        mockDomain(Presenter)
        mockDomain(BootCampSession)

        when:
        Presenter presenter1 = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save()
        BootCampSession bootCampSession = new BootCampSession(presenter: presenter1, name: "Unit Testing", description: "Smallest Module Level Testing").save()
        String model = demoAppTagLib.message(['user': presenter1]){"Test"}.toString()

        then:
        model == "[presenter:Manoj, bootCampSessions:Unit Testing]"

    }

}


package com.ig.demoApp

import grails.plugin.spock.ControllerSpec
import spock.lang.Unroll

class LoginControllerSpec extends ControllerSpec {

    @Unroll("Login Handler #sno")
    def "Login Handler testing"() {

        setup:
        mockDomain(Presenter)
        mockForConstraintsTests(LoginCO)

        when:
        LoginCO loginCO = new LoginCO(email: emailProvided, password: passwordProvided)
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save(flush: true)
        controller.loginHandler(loginCO)

        then:
        loginCO.validate() == validateResult
        controller.flash.message == messageExpected
        redirectArgs.action == actionExpected
        mockSession.presenter?.name == expectedPresenterName

        where:
        sno | emailProvided            | passwordProvided | validateResult | messageExpected       | actionExpected | expectedPresenterName
        1   | "manoj@intelligrape.com" | "pass"           | true           | null                  | null           | "Manoj"
        2   | "m@m.com"                | "pass"           | true           | "User does not exist" | 'login'        | null
        3   | "manoj@intelligrape.com" | "pa"             | true           | "Password Incorrect"  | 'login'        | null

    }

}


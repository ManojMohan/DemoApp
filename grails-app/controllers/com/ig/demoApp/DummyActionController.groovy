package com.ig.demoApp

import grails.converters.JSON
import grails.converters.XML

class DummyActionController {

    def index = { }


    def checkBootCampSessionValidityJSON = {
        BootCampSession.findByName(params.name) ? render([success: true] as JSON) : render([success: false] as JSON)
    }

    def checkBootCampSessionValidityXML = {
        BootCampSession.findByName(params.name) ? render([success: true] as XML) : render([success: false] as XML)
    }

    def checkBootCampSessionValidityAJAX = {
        if (BootCampSession.findByName(params.name)) { render "true" }
        else {render "false" }
    }

    def forwardTestAction = {
        forward(action: 'renderTestAction')
    }

    def renderTestAction = {
        Presenter presenter = new Presenter(name: 'Farid', email: 'farid@intelligrape.com', age: 27, password: 'pass')
        render(view: '/index', model: [presenter: presenter])
    }

    def redirectTestAction = {
        redirect(controller: 'presenter', action: 'list')
    }

    def throwExceptionForPresenter = {
        if (Presenter.findByName(params.name)) {
            Presenter presenter = Presenter.findByName(params.name)
            new BootCampSession(presenter: presenter, name: "Agile Methodology", description: "LOREM IPSUM").save(flush: true)
            println "Presenter found"
        }
        else {
            println "Here"
            throw new RuntimeException("Presenter not Found")
        }
    }
}

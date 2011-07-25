package com.ig.demoApp

import java.text.DateFormat
import java.sql.Time
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class BootCampSessionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def presentationService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bootCampSessionInstanceList: BootCampSession.list(params), bootCampSessionInstanceTotal: BootCampSession.count()]
    }

    def create = {
        def bootCampSessionInstance = new BootCampSession()
        bootCampSessionInstance.properties = params
        return [bootCampSessionInstance: bootCampSessionInstance]
    }

    def save = {
        def bootCampSessionInstance = new BootCampSession(params)
        if (bootCampSessionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), bootCampSessionInstance.id])}"
            redirect(action: "show", id: bootCampSessionInstance.id)
        }
        else {
            render(view: "create", model: [bootCampSessionInstance: bootCampSessionInstance])
        }
    }

    def show = {
        def bootCampSessionInstance = presentationService.getAnotherInstance(params.id as int)
        if (!bootCampSessionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), params.id])}"
            redirect(action: "list")
        }
        else {
            [bootCampSessionInstance: bootCampSessionInstance]
        }
    }

    def edit = {
        BootCampSession bootCampSessionInstance = presentationService.getInstance(params.id as int)
        if (!bootCampSessionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), params.id])}"
            println "Here in no instance !!"
            redirect(action: "list")
        }
        else {
            println "We get an instance !!"
            return [bootCampSessionInstance: bootCampSessionInstance]
        }
    }

    def actionPassesConfigInModel = {
        BootCampSession bootCampSessionInstance = presentationService.getInstance(params.id as int)
        if (!bootCampSessionInstance) {
            redirect(action: "list")
        }
        else {
            return [bootCampSessionInstance: bootCampSessionInstance, maxPresentations: ConfigurationHolder.config.demoApp.maxPresentations]
        }
    }

    def update = {
        def bootCampSessionInstance = BootCampSession.get(params.id)
        if (bootCampSessionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bootCampSessionInstance.version > version) {

                    bootCampSessionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'bootCampSession.label', default: 'BootCampSession')] as Object[], "Another user has updated this BootCampSession while you were editing")
                    render(view: "edit", model: [bootCampSessionInstance: bootCampSessionInstance])
                    return
                }
            }
            bootCampSessionInstance.properties = params
            if (!bootCampSessionInstance.hasErrors() && bootCampSessionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), bootCampSessionInstance.id])}"
                redirect(action: "show", id: bootCampSessionInstance.id)
            }
            else {
                render(view: "edit", model: [bootCampSessionInstance: bootCampSessionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def bootCampSessionInstance = BootCampSession.get(params.id)
        if (bootCampSessionInstance) {
            try {
                bootCampSessionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bootCampSession.label', default: 'BootCampSession'), params.id])}"
            redirect(action: "list")
        }
    }

}

package com.ig.demoApp

class PresenterController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [presenterInstanceList: Presenter.list(params), presenterInstanceTotal: Presenter.count()]
    }

    def create = {
        def presenterInstance = new Presenter()
        presenterInstance.properties = params
        return [presenterInstance: presenterInstance]
    }

    def save = {
        def presenterInstance = new Presenter(params)
        if (presenterInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'presenter.label', default: 'Presenter'), presenterInstance.id])}"
            redirect(action: "show", id: presenterInstance.id)
        }
        else {
            render(view: "create", model: [presenterInstance: presenterInstance])
        }
    }

    def show = {
        def presenterInstance = Presenter.get(params.id)
        if (!presenterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'presenter.label', default: 'Presenter'), params.id])}"
            redirect(action: "list")
        }
        else {
            [presenterInstance: presenterInstance]
        }
    }

    def edit = {
        def presenterInstance = Presenter.get(params.id)
        if (!presenterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'presenter.label', default: 'Presenter'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [presenterInstance: presenterInstance]
        }
    }

    def update = {
        def presenterInstance = Presenter.get(params.id)
        if (presenterInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (presenterInstance.version > version) {
                    
                    presenterInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'presenter.label', default: 'Presenter')] as Object[], "Another user has updated this Presenter while you were editing")
                    render(view: "edit", model: [presenterInstance: presenterInstance])
                    return
                }
            }
            presenterInstance.properties = params
            if (!presenterInstance.hasErrors() && presenterInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'presenter.label', default: 'Presenter'), presenterInstance.id])}"
                redirect(action: "show", id: presenterInstance.id)
            }
            else {
                render(view: "edit", model: [presenterInstance: presenterInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'presenter.label', default: 'Presenter'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def presenterInstance = Presenter.get(params.id)
        if (presenterInstance) {
            try {
                presenterInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'presenter.label', default: 'Presenter'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'presenter.label', default: 'Presenter'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'presenter.label', default: 'Presenter'), params.id])}"
            redirect(action: "list")
        }
    }
}

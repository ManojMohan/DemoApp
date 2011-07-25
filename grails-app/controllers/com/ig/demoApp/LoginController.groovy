package com.ig.demoApp

class LoginController {

    static defaultAction = "login"

    def index = { }

    def login = {}

    def loginHandler = {LoginCO loginCO ->
        if (Presenter.findByEmail(loginCO.email)) {
            if (Presenter.findByPassword(loginCO.password)) {
                session.presenter = Presenter.findByEmail(loginCO.email)
                session.msg = "Logged In as ${session.presenter.name}"
                println "session user : ${session.presenter}"
                redirect(uri: "/")
            }
            else {
                flash.message = "Password Incorrect"
                redirect(action: 'login')
            }
        }
        else {
            flash.message = "User does not exist"
            redirect(action: 'login')
        }
    }

    def logout = {
        session.invalidate()
        flash.message = "Logged out .... Thank You"
        redirect(action: 'login')
    }

}

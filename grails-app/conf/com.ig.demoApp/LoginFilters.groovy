package com.ig.demoApp

class LoginFilters {

    def filters = {

        loginchk(controller: 'login', action: '*', invert: true) {
            before = {
                if (!session.presenter) {
                    flash.message = "Please Login to access the Application !!"
                    redirect(controller: 'login', action: 'login')
                    return false
                }
            }
        }
    }

}

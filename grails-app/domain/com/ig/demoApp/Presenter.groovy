package com.ig.demoApp

class Presenter {

    String name
    Integer age
    String email
    String password

    static hasMany = [bootCampSessions: BootCampSession]

    String toString() {
        return name
    }

    static constraints = {
    }

    static mapping = {
    }

}

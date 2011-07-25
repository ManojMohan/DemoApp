package com.ig.demoApp

class BootCampSession {

    Presenter presenter
    String name
    String description

    String toString() {
        return name
    }


    static constraints = {
        presenter(nullable: false, blank: false)
    }
}

package com.ig.demoApp

class BusinessLogicService {

    static transactional = true


    BootCampSession getPresentationInstance(Integer bootCampSessionId) {
        println "In Business service"
        return BootCampSession.findById(bootCampSessionId)
    }


}

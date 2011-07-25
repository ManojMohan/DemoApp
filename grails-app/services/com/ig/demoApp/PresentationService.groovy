package com.ig.demoApp

class PresentationService {

    def businessLogicService
    static transactional = true



    BootCampSession getInstance(Integer bootCampSessionId){
        println "In service ${BootCampSession.list()}"
        return BootCampSession.findById(bootCampSessionId)
    }

    BootCampSession getAnotherInstance(Integer bootCampSessionId) {
        return businessLogicService.getPresentationInstance(bootCampSessionId)
    }


}

package com.ig.demoApp

class PresentationService {

    def businessLogicService
    static transactional = true



    BootCampSession getInstance(Integer bootCampSessionId){
        return BootCampSession.findById(bootCampSessionId)
    }

    BootCampSession getAnotherInstance(Integer bootCampSessionId) {
        return businessLogicService.getPresentationInstance(bootCampSessionId)
    }


}

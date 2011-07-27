package com.ig.demoApp


class DemoAppTagLib {

    static namespace = "d"

    def message = { attrs ->
        Presenter presenter = attrs['user']
        out << g.render(template: '/bootCampSession/message', model: [presenter: presenter.name, bootCampSessions: BootCampSession.findAllByPresenter(presenter).join(", ")])
    }
}

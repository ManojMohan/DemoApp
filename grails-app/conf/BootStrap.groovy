import com.ig.demoApp.*

class BootStrap {

    def init = { servletContext ->
        buildSessions()


    }
    def destroy = {
    }

    void buildSessions() {
        Presenter presenter = new Presenter(name: "Manoj", age: 25, email: "manoj@intelligrape.com", password: "pass").save()
        Presenter presenter1 = new Presenter(name: "Ankur", age: 25, email: "ankur@intelligrape.com", password: "pass").save()
        Presenter presenter2 = new Presenter(name: "Uday", age: 25, email: "uday@intelligrape.com", password: "pass").save()
        Presenter presenter3 = new Presenter(name: "Vishal", age: 25, email: "vishal@intelligrape.com", password: "pass").save()
        Presenter presenter4 = new Presenter(name: "Imran", age: 25, email: "imran@intelligrape.com", password: "pass").save()
        BootCampSession bootCampSession = new BootCampSession(name: "Unit Testing", description: "LOREM IPSUM DOLLAR", presenter: presenter).save()
        BootCampSession bootCampSession1 = new BootCampSession(name: "Integration Testing", description: "LOREM IPSUM DOLLAR", presenter: presenter2).save()
        BootCampSession bootCampSession2 = new BootCampSession(name: "Functional Testing", description: "LOREM IPSUM DOLLAR", presenter: presenter3).save()
        BootCampSession bootCampSession3 = new BootCampSession(name: "Acceptance Testing", description: "LOREM IPSUM DOLLAR",  presenter: presenter4).save()


    }
}
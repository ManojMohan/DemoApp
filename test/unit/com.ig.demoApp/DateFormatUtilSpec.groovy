package com.ig.demoApp

import grails.plugin.spock.UnitSpec
import java.sql.Time

class DateFormatUtilSpec extends UnitSpec {


    def setupSpec() {
        println "Run before first test method"
    }

    def setup() {
        println "Run before every test method"
    }

    def cleanup() {
        println "Run after every test method"
    }

    def cleaupSpec() {
        println "Run after final test method"
    }

    def "test Get Date Object"() {

        when:
        Date date = DateFormatUtil.getDateObj(dateString)

        then:
        date.toString() == expectedDate

        where:

        sno | dateString   | expectedDate
        1   | "23-12-2011" | "Fri Dec 23 00:00:00 IST 2011"
        2   | "12-12-2011" | "Mon Dec 12 00:00:00 IST 2011"
    }

    def "test Get Date Object Using Expect"() {

        given:
        Date date = DateFormatUtil.getDateObj(dateString)

        expect:
        date.toString() == expectedDate

        where:

        sno | dateString   | expectedDate
        1   | "23-12-2011" | "Fri Dec 23 00:00:00 IST 2011"
        2   | "12-12-2011" | "Mon Dec 12 00:00:00 IST 2011"
    }


    def "test Get Time Object"() {

        when:
        Time time = DateFormatUtil.getTimeObj(timeProvided)

        then:
        time.toString() == expectedTime

        where:

        sno | timeProvided | expectedTime
        1   | "12:12:12"   | "12:12:12"
        2   | "00:01:01"   | "00:01:01"
        2   | "24:00:00"   | "00:00:00"
    }

}


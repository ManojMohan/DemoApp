package com.ig.demoApp

import java.text.SimpleDateFormat
import java.sql.Time


class DateFormatUtil {

    static Time getTimeObj(String timeHHMMSS) {
        SimpleDateFormat sdf = new SimpleDateFormat('HH:mm:ss')
        new Time(ignoreDdMmYyyy(sdf.parse(timeHHMMSS)).time)
    }

    static Date getDateObj(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat('dd-MM-yyyy')
        sdf.parse(date)

    }

    static Date ignoreDdMmYyyy(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat('HH-mm-ss')
        sdf.parse(sdf.format(date))
    }
}

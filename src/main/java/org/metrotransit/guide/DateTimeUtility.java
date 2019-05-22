package org.metrotransit.guide;

import java.util.Date;

class DateTimeUtility {

    static String getDifferenceFromCurrentTime(String trainTimeInMillis) {
        Date trainTime = new Date(Long.parseLong(trainTimeInMillis));

        long difference = trainTime.getTime() - new Date().getTime();
        long diffSeconds = difference / 1000 % 60;
        long diffMinutes = difference / (60 * 1000) % 60;
        long diffHours = difference / (60 * 60 * 1000) % 24;

        String response = "";

        if (diffHours > 0) {
            response = response.concat(diffHours + " hour(s) ");
        }

        if (diffMinutes > 0) {
            response =  response.concat(diffMinutes + " min(s) ");
        }

        if (diffSeconds > 0) {
            response =  response.concat(diffSeconds + " sec(s)");
        }

        return response;
    }
}

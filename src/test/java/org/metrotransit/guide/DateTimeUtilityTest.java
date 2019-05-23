package org.metrotransit.guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtilityTest {

    @Test
    public void shouldReturnBlankForSameTimeAsCurrent() {
        String differenceFromCurrentTime = DateTimeUtility.getDifferenceFromCurrentTime(String.valueOf(System.currentTimeMillis()));
        Assert.assertEquals("", differenceFromCurrentTime);
    }

    @Test
    public void shouldReturn12MinsDifference() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.MINUTE, 12);

        String differenceFromCurrentTime = DateTimeUtility.getDifferenceFromCurrentTime(String.valueOf(calendar.getTimeInMillis()));
        // Test execution can be slower sometime and return time might be a couple of secs less than 12
        Assert.assertTrue(differenceFromCurrentTime.startsWith("12") ||
                differenceFromCurrentTime.startsWith("11"));
    }

    @Test
    public void shouldNotReturnAnythingForNegativeDifference() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.MINUTE, -12);

        String differenceFromCurrentTime = DateTimeUtility.getDifferenceFromCurrentTime(String.valueOf(calendar.getTimeInMillis()));
        Assert.assertEquals("", differenceFromCurrentTime);
    }

}
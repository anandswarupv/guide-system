package org.metrotransit.guide;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class})
@PowerMockIgnore({"javax.net.ssl.*"})
public class ApplicationTest {

    @Test
    public void shouldReturnHowLongUntilNextBus() {
        //{"Description":"23 - Uptown - 38th St - Highland Village","ProviderID":"8","Route":"23"}
        //[{"Text":"EASTBOUND","Value":"2"},{"Text":"WESTBOUND","Value":"3"}]
        //[{"Text":"Kenneth St and Ford Pkwy","Value":"KEFO"},{"Text":"Ford Pkwy and Cleveland Ave","Value":"CLFO"}]
        //[{"Actual":true,"BlockNumber":1487,"DepartureText":"7 Min","DepartureTime":"\/Date(1558547820000-0500)\/",
        // "Description":"38th Street \/ Uptown","Gate":"","Route":"23","RouteDirection":"WESTBOUND","Terminal":"",
        // "VehicleHeading":0,"VehicleLatitude":44.91781,"VehicleLongitude":-93.19608}]
        String[] args = {"23 - Uptown - 38th St - Highland Village", "Kenneth St and Ford Pkwy", "west"};
        Application.main(args);
    }

    @Test
    public void shouldComplain() {
        Logger mockLogger = Mockito.mock(Logger.class);
        PowerMockito.mockStatic(LoggerFactory.class);
        PowerMockito.when(LoggerFactory.getLogger(SimpleClient.class)).thenReturn(mockLogger);

        String[] args = {"Express - Target - Hwy 252 and 73rd Av P&R - Mpls", "Target North Campus Building F"};
        Application.main(args);

        Mockito.verify(mockLogger).info("Please enter BUS ROUTE, BUS STOP NAME & DIRECTION");
    }

    @Test
    public void testForLightRail() {
        String[] args = {"METRO Blue Line", "Target Field Station Platform 1", "south"};
        Application.main(args);
    }

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
        Assert.assertEquals("12 min(s) ", differenceFromCurrentTime);
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
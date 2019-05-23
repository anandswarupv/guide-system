package org.metrotransit.guide;

import com.google.common.collect.Sets;
import org.metrotransit.guide.model.Direction;
import org.metrotransit.guide.model.Route;
import org.metrotransit.guide.model.Stop;
import org.metrotransit.guide.model.TimepointDeparture;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class ObjectMother {

    public static Set<TimepointDeparture> getDummyTimepointDepartures() {
        //[{"Actual":true,"BlockNumber":1487,"DepartureText":"7 Min","DepartureTime":"\/Date(1558547820000-0500)\/",
        // "Description":"38th Street \/ Uptown","Gate":"","Route":"23","RouteDirection":"WESTBOUND","Terminal":"",
        // "VehicleHeading":0,"VehicleLatitude":44.91781,"VehicleLongitude":-93.19608}]

        TimepointDeparture timepointDeparture = new TimepointDeparture();
        timepointDeparture.setActual(true);
        timepointDeparture.setBlockNumber(1487);
        timepointDeparture.setDepartureText("7 mins");
        timepointDeparture.setDescription("38th Street / Uptown");
        timepointDeparture.setRoute("23");
        timepointDeparture.setRouteDirection("WESTBOUND");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.MINUTE, 12);

        timepointDeparture.setDepartureTime("/Date(" + String.valueOf(calendar.getTimeInMillis() + "-0500)/"));

        return Sets.newHashSet(timepointDeparture);
    }

    public static Set<Stop> getDummyStops() {
        //[{"Text":"Kenneth St and Ford Pkwy","Value":"KEFO"},{"Text":"Ford Pkwy and Cleveland Ave","Value":"CLFO"}]
        Stop KEFO = new Stop();
        KEFO.setText("Kenneth St and Ford Pkwy");
        KEFO.setValue("KEFO");

        Stop CLFO = new Stop();
        CLFO.setText("Ford Pkwy and Cleveland Ave");
        CLFO.setValue("CLFO");

        return Sets.newHashSet(KEFO, CLFO);
    }

    public static Set<Direction> getDummyDirections() {
        //[{"Text":"EASTBOUND","Value":"2"},{"Text":"WESTBOUND","Value":"3"}]
        Direction directionForRoute23EastBound = new Direction();
        directionForRoute23EastBound.setText("EASTBOUND");
        directionForRoute23EastBound.setValue(2);

        Direction directionForRoute23WestBound = new Direction();
        directionForRoute23WestBound.setText("WESTBOUND");
        directionForRoute23WestBound.setValue(3);

        return Sets.newHashSet(directionForRoute23EastBound, directionForRoute23WestBound);
    }

    public static Set<Route> getDummyRoutes() {
        //{"Description":"23 - Uptown - 38th St - Highland Village","ProviderID":"8","Route":"23"}
        Route route23 = new Route();
        route23.setDescription("23 - Uptown - 38th St - Highland Village");
        route23.setProviderID(8);
        route23.setRoute(23);
        return Sets.newHashSet(route23);
    }
}

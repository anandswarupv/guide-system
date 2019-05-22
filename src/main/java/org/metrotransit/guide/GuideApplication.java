package org.metrotransit.guide;

import org.metrotransit.guide.model.Direction;
import org.metrotransit.guide.model.Route;
import org.metrotransit.guide.model.Stop;
import org.metrotransit.guide.model.TimepointDeparture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;


public class GuideApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    public static void main(String[] args) {

        if (args.length < 3) {
            LOGGER.info("Not enough information available to find the route details!");
            LOGGER.info("Please enter BUS ROUTE, BUS STOP NAME & DIRECTION");
        }

        Set<Route> allRoutes = SimpleClient.getAllRoutes();

        Optional<Route> searchedRoute = allRoutes.parallelStream()
                .filter(route -> route.getDescription().toLowerCase().contains(args[0].toLowerCase()))
                .findFirst();

        if (searchedRoute.isPresent()) {
            LOGGER.trace("Found Route: {}", searchedRoute.get());
            Set<Direction> directionsForRoute = SimpleClient.getDirectionsForRoute(searchedRoute.get().getRoute());

            Optional<Direction> searchedDirection = directionsForRoute.parallelStream().filter(direction -> direction.getText().toLowerCase().contains(args[2].toLowerCase())).findFirst();

            if (searchedDirection.isPresent()) {
                LOGGER.trace("Found Direction: {}", searchedDirection.get());
                Set<Stop> stops = SimpleClient.getStops(searchedRoute.get().getRoute(), searchedDirection.get().getValue());

                Optional<Stop> searchedStop = stops.parallelStream().filter(stop -> stop.getText().toLowerCase().contains(args[1].toLowerCase())).findFirst();

                if (searchedStop.isPresent()) {
                    LOGGER.trace("Found Stop: {}", searchedStop.get());
                    Set<TimepointDeparture> timepointDepartures = SimpleClient.getTimepointDepartures(searchedRoute.get().getRoute(), searchedDirection.get().getValue(), searchedStop.get().getValue());

                    if (!timepointDepartures.isEmpty()) {
                        Optional<TimepointDeparture> optionalTimepointDeparture = timepointDepartures.stream().findFirst();

                        optionalTimepointDeparture.ifPresent(timepointDeparture -> {
                            LOGGER.trace(timepointDepartures.toString());
                            String differenceFromCurrentTime = DateTimeUtility.getDifferenceFromCurrentTime(timepointDeparture.getDepartureTime());
                            LOGGER.debug("Time to next bus:: " + differenceFromCurrentTime);
                            System.out.println("Time to next bus:: " + differenceFromCurrentTime);
                        });
                    }
                }
            }
        }

    }

}

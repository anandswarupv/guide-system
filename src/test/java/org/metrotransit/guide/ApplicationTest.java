package org.metrotransit.guide;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.metrotransit.guide.model.Direction;
import org.metrotransit.guide.model.Route;
import org.metrotransit.guide.model.Stop;
import org.metrotransit.guide.model.TimepointDeparture;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, SimpleClient.class})
@PowerMockIgnore({"javax.net.ssl.*"})
public class ApplicationTest {

    @Test
    public void shouldReturn12Minutes() {
        Set<Route> routes = ObjectMother.getDummyRoutes();
        Set<Direction> directions = ObjectMother.getDummyDirections();
        Set<Stop> stops = ObjectMother.getDummyStops();
        Set<TimepointDeparture> timepointDepartures = ObjectMother.getDummyTimepointDepartures();

        PowerMockito.mockStatic(SimpleClient.class);
        PowerMockito.when(SimpleClient.getAllRoutes()).thenReturn(routes);
        PowerMockito.when(SimpleClient.getDirectionsForRoute(23)).thenReturn(directions);
        PowerMockito.when(SimpleClient.getStops(23, 3)).thenReturn(stops);
        PowerMockito.when(SimpleClient.getTimepointDepartures(23, 3, "KEFO")).thenReturn(timepointDepartures);
        PowerMockito.when(SimpleClient.class.getName()).thenReturn("mockSimpleClient");

        Logger mockLogger = Mockito.mock(Logger.class);
        PowerMockito.mockStatic(LoggerFactory.class);
        PowerMockito.when(LoggerFactory.getLogger(Application.class)).thenReturn(mockLogger);

        ArgumentCaptor<String> acLogger = ArgumentCaptor.forClass(String.class);

        String[] args = {"23 - Uptown - 38th St - Highland Village", "Kenneth St and Ford Pkwy", "west"};
        Application.main(args);

        Mockito.verify(mockLogger).info(acLogger.capture());

        // Test execution can be slower sometime and return time might be a couple of secs less than 12
        List<String> keys = acLogger.getAllValues();
        Assert.assertTrue(keys.parallelStream()
                .anyMatch(key -> key.startsWith("12") || key.startsWith("11")));
    }

    @Test
    public void shouldReturnBlank() {
        Set<Route> routes = ObjectMother.getDummyRoutes();
        Set<Direction> directions = ObjectMother.getDummyDirections();
        Set<Stop> stops = ObjectMother.getDummyStops();
        Set<TimepointDeparture> timepointDepartures = Sets.newHashSet();

        PowerMockito.mockStatic(SimpleClient.class);
        PowerMockito.when(SimpleClient.getAllRoutes()).thenReturn(routes);
        PowerMockito.when(SimpleClient.getDirectionsForRoute(23)).thenReturn(directions);
        PowerMockito.when(SimpleClient.getStops(23, 3)).thenReturn(stops);
        PowerMockito.when(SimpleClient.getTimepointDepartures(23, 3, "KEFO")).thenReturn(timepointDepartures);
        PowerMockito.when(SimpleClient.class.getName()).thenReturn("mockSimpleClient");

        Logger mockLogger = Mockito.mock(Logger.class);
        PowerMockito.mockStatic(LoggerFactory.class);
        PowerMockito.when(LoggerFactory.getLogger(Application.class)).thenReturn(mockLogger);

        ArgumentCaptor<String> acLogger = ArgumentCaptor.forClass(String.class);

        String[] args = {"23 - Uptown - 38th St - Highland Village", "Kenneth St and Ford Pkwy", "west"};
        Application.main(args);

        Mockito.verify(mockLogger, Mockito.never()).info(ArgumentMatchers.anyString());

    }

}
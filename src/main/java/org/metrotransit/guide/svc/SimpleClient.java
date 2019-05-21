package org.metrotransit.guide.svc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.google.common.net.HttpHeaders;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.metrotransit.guide.model.Direction;
import org.metrotransit.guide.model.Route;
import org.metrotransit.guide.model.Stop;
import org.metrotransit.guide.model.TimepointDepartures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class SimpleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    private static final String SEPARATOR = "/";
    private static final String ROUTE_API = "http://svc.metrotransit.org/NexTrip/Routes";
    private static final String DIRECTIONS_API = "http://svc.metrotransit.org/NexTrip/Directions/";
    private static final String STOPS_API = "http://svc.metrotransit.org/NexTrip/Stops/";
    private static final String TIMEPOINT_DEPARTURES_API = "http://svc.metrotransit.org/NexTrip/";

    private static final String ACCEPT_HEADER = "application/json";

    private static final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000)
            .setConnectionRequestTimeout(1000)
            .setConnectTimeout(1000)
            .build();

    private static final ObjectMapper objectMapper;

    private static final CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(500);
        connectionManager.setDefaultMaxPerRoute(100);

        objectMapper = new ObjectMapper();

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setConnectionManagerShared(true)
                .build();
    }

    public static Set<Direction> getDirectionsForRoute(Integer routeNumber) {
        try {
            HttpGet getRequest = new HttpGet(DIRECTIONS_API + routeNumber);
            String responseJson = getResponseJson(getRequest);

            if (StringUtils.isNotBlank(responseJson)) {
                return (Set<Direction>) objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(Set.class, Direction.class));
            }
        } catch (Exception ex) {
            LOGGER.error("Unable to get Directions: {}", ex.getMessage());
        }
        return Sets.newHashSet();
    }

    public static Set<Route> getAllRoutes() {
        try {
            HttpGet getRequest = new HttpGet(ROUTE_API);
            String responseJson = getResponseJson(getRequest);

            if (StringUtils.isNotBlank(responseJson)) {
                return (Set<Route>) objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(Set.class, Route.class));
            }

        } catch (Exception ex) {
            LOGGER.error("Unable to get Routes: {}", ex.getMessage());
        }
        return Sets.newHashSet();
    }

    public static Set<Stop> getStops(Integer route, Integer direction) {
        try {
            HttpGet getRequest = new HttpGet(STOPS_API + route + SEPARATOR + direction);
            String responseJson = getResponseJson(getRequest);

            if (StringUtils.isNotBlank(responseJson)) {
                return (Set<Stop>) objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(Set.class, Stop.class));
            }

        } catch (Exception ex) {
            LOGGER.error("Unable to get Stops: {}", ex.getMessage());
        }
        return Sets.newHashSet();
    }

    public static Set<TimepointDepartures> getTimepointDepartures(Integer routeNumber, Integer direction, String stopIdentifier) {

        HttpGet getRequest = new HttpGet(TIMEPOINT_DEPARTURES_API + routeNumber + SEPARATOR + direction + SEPARATOR + stopIdentifier);
        String responseJson = getResponseJson(getRequest);

        if (StringUtils.isNotBlank(responseJson)) {
            try {
                return (Set<TimepointDepartures>) objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(Set.class, TimepointDepartures.class));
            } catch (Exception e) {
                LOGGER.error("Unable to get TimepointDepartures: {}", e.getMessage());
            }
        }
        return Sets.newHashSet();
    }

    private static String getResponseJson(HttpGet getRequest) {

        try {

            getRequest.setHeader(HttpHeaders.CONTENT_TYPE, ACCEPT_HEADER);
            getRequest.setHeader(HttpHeaders.ACCEPT, ACCEPT_HEADER);
            getRequest.setHeader(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
            getRequest.setConfig(requestConfig);

            LOGGER.debug("Request to http://svc.metrotransit.org/: " + getRequest.toString());

            CloseableHttpResponse response = httpClient.execute(getRequest);

            if (response != null) {

                HttpEntity responseEntity = response.getEntity();
                String responseJson = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                LOGGER.debug("Response from http://svc.metrotransit.org/: " + response.toString().concat(responseJson));
                // For making sure to release the connection
                EntityUtils.consumeQuietly(responseEntity);

                if (StringUtils.isNotBlank(responseJson)) {
                    return responseJson;
                }
            }

        } catch (Exception ex) {
            LOGGER.error("Unable to get Stops: {}", ex.getMessage());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("Unable to close the HTTPClient {}", e.getMessage());
            }
        }

        return "";

    }

}

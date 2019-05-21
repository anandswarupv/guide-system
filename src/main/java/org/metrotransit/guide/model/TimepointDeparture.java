package org.metrotransit.guide.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Actual",
        "BlockNumber",
        "DepartureText",
        "DepartureTime",
        "Description",
        "Gate",
        "Route",
        "RouteDirection",
        "Terminal",
        "VehicleHeading",
        "VehicleLatitude",
        "VehicleLongitude"
})
public class TimepointDeparture {

    @JsonProperty("Actual")
    private Boolean actual;
    @JsonProperty("BlockNumber")
    private Integer blockNumber;
    @JsonProperty("DepartureText")
    private String departureText;
    @JsonProperty("DepartureTime")
    private String departureTime;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Gate")
    private Integer gate;
    @JsonProperty("Route")
    private String route;
    @JsonProperty("RouteDirection")
    private String routeDirection;
    @JsonProperty("Terminal")
    private String terminal;
    @JsonProperty("VehicleHeading")
    private Integer vehicleHeading;
    @JsonProperty("VehicleLatitude")
    private Double vehicleLatitude;
    @JsonProperty("VehicleLongitude")
    private Double vehicleLongitude;

    @JsonProperty("Actual")
    public Boolean getActual() {
        return actual;
    }

    @JsonProperty("Actual")
    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    @JsonProperty("BlockNumber")
    public Integer getBlockNumber() {
        return blockNumber;
    }

    @JsonProperty("BlockNumber")
    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    @JsonProperty("DepartureText")
    public String getDepartureText() {
        return departureText;
    }

    @JsonProperty("DepartureText")
    public void setDepartureText(String departureText) {
        this.departureText = departureText;
    }

    @JsonProperty("DepartureTime")
    public String getDepartureTime() {
        return departureTime;
    }

    @JsonProperty("DepartureTime")
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Gate")
    public Integer getGate() {
        return gate;
    }

    @JsonProperty("Gate")
    public void setGate(Integer gate) {
        this.gate = gate;
    }

    @JsonProperty("Route")
    public String getRoute() {
        return route;
    }

    @JsonProperty("Route")
    public void setRoute(String route) {
        this.route = route;
    }

    @JsonProperty("RouteDirection")
    public String getRouteDirection() {
        return routeDirection;
    }

    @JsonProperty("RouteDirection")
    public void setRouteDirection(String routeDirection) {
        this.routeDirection = routeDirection;
    }

    @JsonProperty("Terminal")
    public String getTerminal() {
        return terminal;
    }

    @JsonProperty("Terminal")
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @JsonProperty("VehicleHeading")
    public Integer getVehicleHeading() {
        return vehicleHeading;
    }

    @JsonProperty("VehicleHeading")
    public void setVehicleHeading(Integer vehicleHeading) {
        this.vehicleHeading = vehicleHeading;
    }

    @JsonProperty("VehicleLatitude")
    public Double getVehicleLatitude() {
        return vehicleLatitude;
    }

    @JsonProperty("VehicleLatitude")
    public void setVehicleLatitude(Double vehicleLatitude) {
        this.vehicleLatitude = vehicleLatitude;
    }

    @JsonProperty("VehicleLongitude")
    public Double getVehicleLongitude() {
        return vehicleLongitude;
    }

    @JsonProperty("VehicleLongitude")
    public void setVehicleLongitude(Double vehicleLongitude) {
        this.vehicleLongitude = vehicleLongitude;
    }

    @Override
    public String toString() {
        return "TimepointDeparture{" +
                "actual=" + actual +
                ", blockNumber=" + blockNumber +
                ", departureText='" + departureText + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", description='" + description + '\'' +
                ", gate=" + gate +
                ", route='" + route + '\'' +
                ", routeDirection='" + routeDirection + '\'' +
                ", terminal='" + terminal + '\'' +
                ", vehicleHeading=" + vehicleHeading +
                ", vehicleLatitude=" + vehicleLatitude +
                ", vehicleLongitude=" + vehicleLongitude +
                '}';
    }
}
package org.metrotransit.guide.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Description",
        "ProviderID",
        "Route"
})
public class Route implements Serializable
{

    @JsonProperty("Description")
    private String description;
    @JsonProperty("ProviderID")
    private Integer providerID;
    @JsonProperty("Route")
    private Integer route;
    private final static long serialVersionUID = -3035304393221004390L;

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Route withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("ProviderID")
    public Integer getProviderID() {
        return providerID;
    }

    @JsonProperty("ProviderID")
    public void setProviderID(Integer providerID) {
        this.providerID = providerID;
    }

    public Route withProviderID(Integer providerID) {
        this.providerID = providerID;
        return this;
    }

    @JsonProperty("Route")
    public Integer getRoute() {
        return route;
    }

    @JsonProperty("Route")
    public void setRoute(Integer route) {
        this.route = route;
    }

    public Route withRoute(Integer route) {
        this.route = route;
        return this;
    }

}

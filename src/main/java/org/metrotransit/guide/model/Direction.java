package org.metrotransit.guide.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Text",
        "Value"
})
public class Direction implements Serializable
{

    @JsonProperty("Text")
    private String text;
    @JsonProperty("Value")
    private Integer value;
    private final static long serialVersionUID = -2096954643068254914L;

    @JsonProperty("Text")
    public String getText() {
        return text;
    }

    @JsonProperty("Text")
    public void setText(String text) {
        this.text = text;
    }

    public Direction withText(String text) {
        this.text = text;
        return this;
    }

    @JsonProperty("Value")
    public Integer getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(Integer value) {
        this.value = value;
    }

    public Direction withValue(Integer value) {
        this.value = value;
        return this;
    }

}
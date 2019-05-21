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
public class Stop implements Serializable
{

    @JsonProperty("Text")
    private String text;
    @JsonProperty("Value")
    private String value;
    private final static long serialVersionUID = 2185061602334897101L;

    @JsonProperty("Text")
    public String getText() {
        return text;
    }

    @JsonProperty("Text")
    public void setText(String text) {
        this.text = text;
    }

    public Stop withText(String text) {
        this.text = text;
        return this;
    }

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(String value) {
        this.value = value;
    }

    public Stop withValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
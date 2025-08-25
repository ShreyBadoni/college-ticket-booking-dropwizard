package com.codeflu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import jakarta.validation.constraints.NotEmpty;

public class CTBSConfiguration extends Configuration {
    // Example: a message to show in our API
    @NotEmpty
    private String defaultMessage = "Welcome to College Ticket Booking System!";

    @JsonProperty
    public String getDefaultMessage() {
        return defaultMessage;
    }

    @JsonProperty
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}

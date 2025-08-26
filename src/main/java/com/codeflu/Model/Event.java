package com.codeflu.Model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private Long id;

    @NotBlank(message = "Event name is required")
    private String name;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;

    @Min(value = 1, message = "Total seats must be at least 1")
    private int totalSeats;

    @Min(value = 0, message = "Booked seats cannot be negative")
    private int bookedSeats;

}

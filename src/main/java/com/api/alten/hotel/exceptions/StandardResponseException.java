package com.api.alten.hotel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Base response class with the information and message of each exception.
 * @author Alexsandro Saraiva
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseException {
    private LocalDateTime timestamp;
    private String message;

}

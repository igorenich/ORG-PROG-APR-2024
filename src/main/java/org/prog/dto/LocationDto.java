package org.prog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {
    private StreetDto street;
    private String city;
    private String state;
    private String country;
    private String postcode; // додайте поле postcode
    private CoordinatesDto coordinates;
    private TimezoneDto timezone;
}
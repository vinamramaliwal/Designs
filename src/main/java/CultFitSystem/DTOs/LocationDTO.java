package CultFitSystem.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {
    private double latitude;
    private double longitude;
    private String address;  // Example: "123 Main St, Los Angeles, CA"
    private String city;
    private String state;
    private String country;
    private String postalCode;

    // Constructors, Getters, Setters
}

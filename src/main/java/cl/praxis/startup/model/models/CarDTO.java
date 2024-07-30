package cl.praxis.startup.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarDTO {
    private int idCar;
    private String carName;
    private String url;
    private int idProvider;
}

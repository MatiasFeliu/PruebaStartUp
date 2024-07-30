package cl.praxis.startup.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddressDTO {
    private int idAddress;
    private String addressName;
    private int addressNum;
    private int idUser;

}

package cl.praxis.startup.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private int id;
    private String email;
    private String password;
    private String name;
    private String nick;
    private int weight;
    private int idAuto;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String role;
}

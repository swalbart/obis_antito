package th.obi.rest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class EmergencyContact {

    @Id // makes id to primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increments id with every new entry.
    private Long id;
    private String name;
    private String mail;
    private Long device_id; // foreign key

    public EmergencyContact(String name, String mail, Long device_id){
        this.name = name;
        this.mail = mail;
        this.device_id = device_id;
    }
}

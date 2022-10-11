package th.obi.rest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;

// Lombok annotations
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Device {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
    private String name;

    public Device(String name) {
        this.name = name;
    }

}
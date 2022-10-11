package th.obi.rest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// Lombok annotations
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Route {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
    private String name;
    private Boolean isTracked;
    private Long device_id; // foreign key

    public Route(String name, Long device_id, Boolean isTracked) {
        this.name = name;
        this.isTracked = isTracked;
        this.device_id = device_id;
    }

}
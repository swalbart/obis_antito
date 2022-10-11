package th.obi.rest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Location {

    @Id // makes id to primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increments id with every new entry.
    private Long id;
    private String name;
    private Float latitude;
    private Float longitude;

    private Timestamp timestamp;
    private Long route_id; // foreign key

    public Location(String name, Float lat, Float lon, Timestamp time, Long route_id){
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
        this.timestamp = time;
        this.route_id = route_id;
    }
}

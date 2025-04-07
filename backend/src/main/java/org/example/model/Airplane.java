package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Airplane extends PanacheEntity {
    public String model;
    public String airline;
    public int capacity;

    @JsonIgnoreProperties("airplane")
    @OneToMany(mappedBy = "airplane")
    public List<Flight> flights;
}

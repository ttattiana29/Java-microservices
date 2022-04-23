package ru.itmo.kotikilab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Owner> owners;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Owner> getOwners() {
//        return owners;
//    }
//
//    public void setOwners(List<Owner> owners) {
//        this.owners = owners;
//    }

    public Role(String name) {
        this.name = name;
    }



}

package com.wp.Pets.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name="owner")
@Component
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerId;

    @Column(nullable = false)
    private String name;


// DOES NOT LET ME DO THIS GIVES FOREVER LOOP WHEN ADDING OWNER TO PET
//     code below sets up the one to many relationship between owner and pet
//     where one owner can have many pets
//     @OneToMany specifies that this is the one side of the relationship
//     mappedBy specifies the name of the field in the other class
//     where the primary key of the Pet class (petId) would serve as a foreign key here
//     orphanRemoval = true makes sure no pets are in the database without owners
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(int ownerId, String name, List<Pet> pets) {
        this.ownerId = ownerId;
        this.name = name;
        this.pets = pets;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", pets=" + pets +
                '}';
    }
}



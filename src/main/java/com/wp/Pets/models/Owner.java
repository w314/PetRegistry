package com.wp.Pets.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name="owners")
@Component
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerId;

    @Column(nullable = false)
    private String Name;


    // code below sets up the one to many relationship between owner and pet
    // where one owner can have many pets
    // @OneToMany specifies that this is the one side of the relationship
    // mappedBy specifies the name of the field in the other class
    // where the primary key of the owner would serve as a foreign key
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(int id, String name, List<Pet> pets) {
        this.ownerId = id;
        Name = name;
        this.pets = pets;
    }

    public int getId() {
        return ownerId;
    }

    public void setId(int id) {
        this.ownerId = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
                "id=" + ownerId +
                ", Name='" + Name + '\'' +
                ", pets=" + pets +
                '}';
    }
}



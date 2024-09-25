package com.wp.Pets.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

// @Entity makes the class a DB table
@Entity
// @Table specifies the name of the table
@Table(name="pet")
// @Component makes the class a bean
@Component
public class Pet {

    // @Id and @GeneratedValue together specify an autogenerated primary key
    @Id
    // strategy defines how we want to generate our values
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int petId;

    // @Column is not necessary to create a db column
    // all fields will be columns unless marked by @Transient
    // @Column let's us set constrains on the column
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    // this code sets of the many side of the many to one relationship between pet and owner
    // each owner can have many pets, but pets can have only one owner
    // this does not need the @Column as there are no constrains (it can be null, pets)
    // @ManyToOne specifies that this is the many side of the relationship
    @ManyToOne()
    // @JoinColumns specifies the column name on the one side of the relationship, the primary key in the other table
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    public Pet() { }

//    public Pet(int id, String name, String type) {
//        this.petId = id;
//        this.name = name;
//        this.type = type;
//    }


    public Pet(int petId, String name, String type, Owner owner) {
        this.petId = petId;
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", owner=" + owner +
                '}';
    }
}

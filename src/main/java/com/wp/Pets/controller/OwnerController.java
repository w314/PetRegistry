package com.wp.Pets.controller;

import com.wp.Pets.repository.OwnerDAO;
import com.wp.Pets.models.Owner;
import com.wp.Pets.models.Pet;
import com.wp.Pets.repository.PetRepository;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerDAO ownerDAO;
    private PetRepository petDAO;

    @Autowired
    private OwnerController(OwnerDAO ownerDAO, PetRepository petDAO) {
        this.ownerDAO = ownerDAO;
        this.petDAO = petDAO;
    }


    @PostMapping
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner o = ownerDAO.save(owner);
        return ResponseEntity.status(201).body(o);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwner() {
        List<Owner> owners = ownerDAO.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOwnerById(@PathVariable int id) {
        Optional ownerOptional = ownerDAO.findById(id);

        if(ownerOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Owner with id: " + id + " does not exists");
        }

        Owner owner = (Owner) ownerOptional.get();

        return ResponseEntity.ok(owner);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner, @PathVariable int ownerId) {

        Optional ownerOptional = ownerDAO.findById(ownerId);

        if(ownerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Owner o = (Owner) ownerOptional.get();

        o.setName(owner.getName());

        Owner updatedOwner = ownerDAO.save(o);

        return ResponseEntity.ok(updatedOwner);
    }



    @GetMapping("/{ownerId}/pets")
    private ResponseEntity<Object> getAllPetsofOwner(@PathVariable int ownerId) {
        System.out.println("Owner ID: " + String.valueOf(ownerId));
        Optional<Owner> ownerOptional = ownerDAO.findById(ownerId);
        if(ownerOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Owner with id " + ownerId + " does not exist.");
        }

        Owner owner = ownerOptional.get();

        List<Pet> pets = petDAO.findByOwner(owner);
        return ResponseEntity.ok(pets);

    }

    @DeleteMapping("/{ownerId}")
    @Cascade(CascadeType.ALL)
    public ResponseEntity<Owner> deleteOwner(@PathVariable int ownerId) {
        Optional ownerOptional = ownerDAO.findById(ownerId);
        if(ownerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        Owner deletedOwner = (Owner) ownerOptional.get();
        ownerDAO.deleteById(ownerId);
        return ResponseEntity.ok(deletedOwner);
    }



}


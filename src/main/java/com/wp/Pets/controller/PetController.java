package com.wp.Pets.controller;

import com.wp.Pets.repository.OwnerDAO;
import com.wp.Pets.models.Owner;
import com.wp.Pets.models.Pet;
import com.wp.Pets.repository.PetRepository;
import com.wp.Pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // makes the class a bean
@RequestMapping("/pets") // sets up our PetController to get all HTTP request at the /pets endpoint
public class PetController {

    // create petDAO field where you can inject dao
    private PetRepository petDAO;
    private OwnerDAO ownerDAO;

    @Autowired // will wire the PetDAO bean to our PetController bean
    public PetController(PetRepository petDAO, OwnerDAO ownerDAO) {
        this.petDAO = petDAO;
        this.ownerDAO = ownerDAO;
    }

    @Autowired
    PetService petService;

    @PostMapping
    private ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet p = petDAO.save(pet);
        return ResponseEntity.status(201).body(p);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = this.petService.getAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @PatchMapping("/{petId}")
    private ResponseEntity<Object> addOwnerId(@PathVariable int petId, @RequestBody int ownerId) {
        Optional<Owner> ownerOptional = ownerDAO.findById(ownerId);
        if(ownerOptional.isEmpty()) {
            return ResponseEntity.status(404).body("No owner found with id: " + ownerId);
        }
        Owner owner = ownerOptional.get();

        Pet pet = petDAO.findById(petId).get();
        pet.setOwner(owner);
        Pet updatePet = petDAO.save(pet);
        return ResponseEntity.accepted().body(updatePet);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Object> getPetById(@PathVariable int petId) {

        Optional<Pet> petOptional = petDAO.findById(petId);
        if(petOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pet pet = petOptional.get();
        return ResponseEntity.ok(pet);
    }




}

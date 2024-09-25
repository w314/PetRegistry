package com.wp.Pets.service;

import com.wp.Pets.models.Pet;
import com.wp.Pets.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> getAllPets() {
        List<Pet> pets = this.petRepository.findAll();
        return pets;
    }


}

package com.wp.Pets.repository;

import com.wp.Pets.models.Owner;
import com.wp.Pets.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {


    public List<Pet> findByOwner(Owner owner);

}

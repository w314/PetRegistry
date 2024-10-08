package com.wp.Pets.repository;

import com.wp.Pets.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // makes our interface a bean
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
}

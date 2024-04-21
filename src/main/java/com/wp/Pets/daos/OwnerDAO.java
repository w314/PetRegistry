package com.wp.Pets.daos;

import com.wp.Pets.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // makes our interface a bean
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
}

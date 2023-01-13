package com.craftmanship.insurance.repositories;

import com.craftmanship.insurance.entities.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CoverageRepository extends JpaRepository<Coverage, Long> {

}

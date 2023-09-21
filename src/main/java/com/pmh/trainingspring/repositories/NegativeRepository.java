package com.pmh.trainingspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pmh.trainingspring.model.Negative;

@Repository
public interface NegativeRepository extends JpaRepository<Negative, Long> {
	
	@Query(value = "select distinct * from negative where name like %:name%;", nativeQuery = true)
	List<Negative> findNegativeByName(@Param("name") String name);
}

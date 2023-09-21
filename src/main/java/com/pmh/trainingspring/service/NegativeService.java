package com.pmh.trainingspring.service;

import java.util.List;
import java.util.Optional;

import com.pmh.trainingspring.model.Negative;

public interface NegativeService {
	List<Negative> findAll();
	Optional<Negative> findByID(Long id);
	List<Negative> findByName(String name);
	void createNegative(Negative negative);
	void updateNegative(Negative negative);
	void deleteNegative(Long id);
}

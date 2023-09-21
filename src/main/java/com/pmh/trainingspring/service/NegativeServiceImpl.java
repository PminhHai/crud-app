package com.pmh.trainingspring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmh.trainingspring.model.Negative;
import com.pmh.trainingspring.repositories.NegativeRepository;

@Service
public class NegativeServiceImpl implements NegativeService {
	
	@Autowired
	NegativeRepository negativeRepository;
	
	@Transactional
	@Override
	public List<Negative> findAll() {
		
		return negativeRepository.findAll();
	}
	
	@Transactional
	@Override
	public Optional<Negative> findByID(Long id) {
		
		return negativeRepository.findById(id);
	}

	@Transactional
	@Override
	public void createNegative(Negative negative) {
		negativeRepository.save(negative);
		
	}
	
	@Transactional
	@Override
	public void updateNegative(Negative negative) {
		negativeRepository.save(negative);
		
	}
	
	@Transactional
	@Override
	public void deleteNegative(Long id) {
		negativeRepository.deleteById(id);
		
	}
	
	@Transactional
	@Override
	public List<Negative> findByName(String name) {
		
		return negativeRepository.findNegativeByName(name);
	}

}

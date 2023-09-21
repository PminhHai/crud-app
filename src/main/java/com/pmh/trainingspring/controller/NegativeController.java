package com.pmh.trainingspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmh.trainingspring.dto.AddNegativeDTO;
import com.pmh.trainingspring.dto.NegativeDTO;
import com.pmh.trainingspring.model.Negative;
import com.pmh.trainingspring.response.MessageResponse;
import com.pmh.trainingspring.service.NegativeService;

@RestController
@RequestMapping("/api/negative")
public class NegativeController {
	
	@Autowired
	NegativeService  negativeService;
	
	@GetMapping("/allnegative")
	public List<NegativeDTO> allNegative() {
		List<Negative> negatives = negativeService.findAll();
		
		List<NegativeDTO> negativeDTOs = new ArrayList<>();
		
		for(int i = 0; i < negatives.size(); i++) {
			NegativeDTO negativeDTO = new NegativeDTO();
			
			negativeDTO.setId(negatives.get(i).getId());
			negativeDTO.setName(negatives.get(i).getName());
			
			negativeDTOs.add(negativeDTO);
		}
		
		return negativeDTOs;
	}
	
	@GetMapping("/negativeByID")
	public ResponseEntity<?> negativeByID(@RequestParam Long id) {
		Optional<Negative> optionalNegative = negativeService.findByID(id);
		
		if(!optionalNegative.isPresent()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Negative is not exist!"));
		}
		
		Negative negative = optionalNegative.get();
		
		NegativeDTO negativeDTO = new NegativeDTO();
		
		negativeDTO.setId(negative.getId());
		negativeDTO.setName(negative.getName());
		
		return ResponseEntity.ok(negativeDTO);
	}
	
	@GetMapping("/negativeByName")
	public ResponseEntity<?> negativeByName(@RequestParam String name) {
		List<Negative> negatives = negativeService.findByName(name);
		
		if(negatives.size() <= 0 ) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Negative is not exist!"));
		}
		
		List<NegativeDTO> negativeDTOs = new ArrayList<>();
		
		for(int i = 0; i < negatives.size(); i++) {
			NegativeDTO negativeDTO = new NegativeDTO();
			
			negativeDTO.setId(negatives.get(i).getId());
			negativeDTO.setName(negatives.get(i).getName());
			
			negativeDTOs.add(negativeDTO);
		}
		
		return ResponseEntity.ok(negativeDTOs);
	}
	
	@PostMapping("/addNegative")
	public ResponseEntity<?> createNegative(@RequestBody AddNegativeDTO negativeDTO){
		Negative negative = new Negative();
		
		negative.setName(negativeDTO.getName());
		
		negativeService.createNegative(negative);
		
		return ResponseEntity.ok(new MessageResponse("Add negative successfully!"));
	}
	
	@PutMapping("/updateNegative")
	public ResponseEntity<?> createNegative(@RequestBody NegativeDTO negativeDTO){
		Optional<Negative> optionalNegative = negativeService.findByID(negativeDTO.getId());
		
		if(!optionalNegative.isPresent()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Negative is not exist!"));
		}
		
		Negative negative = optionalNegative.get();
		
		negative.setName(negativeDTO.getName());
		
		negativeService.updateNegative(negative);
		
		return ResponseEntity.ok(new MessageResponse("Update negative successfully!"));
	}
	
	@DeleteMapping("/deleteNegative")
	public ResponseEntity<?> deleteNegative(@RequestParam Long id){
		Optional<Negative> optionalNegative = negativeService.findByID(id);
		
		if(!optionalNegative.isPresent()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Negative is not exist!"));
		}
		
		Negative negative = optionalNegative.get();
		
		negativeService.deleteNegative(negative.getId());
		
		return ResponseEntity.ok(new MessageResponse("Delete negative successfully!"));
	}
}

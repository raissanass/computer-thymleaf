
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Computer;
import com.example.demo.repository.ComputerRepository;

@Service
public class ComputerService {
	@Autowired 
	ComputerRepository repository;
	
	
	public List<Computer> findALLcomputer()
	{
		return (List<Computer>)repository.findAll();
	}
	
	
	public Computer findComputerById(int id)
	{
		Optional<Computer> result = repository.findById(id);
		if(result.isPresent())
		{
		 	return result.get();
		}
		return new Computer();
	}
	
	
	public Computer addComputer(Computer computer)
	{
		return repository.save(computer);
	}
	
	public Computer updateComputer(Computer computer)
	{
		Optional<Computer> result = repository.findById(computer.getId());
		Computer existing = result.get();
		existing.setSerialNumber(computer.getSerialNumber());
		existing.setModel(computer.getModel());
		existing.setReleaseDate(computer.getReleaseDate());
	    existing.setStatus(computer.isStatus());
	    
	    return repository.save(existing);
	}
	
	public void deletebyId(int id)
	{
		repository.deleteById(id);
	}
}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}

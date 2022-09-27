package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {

}

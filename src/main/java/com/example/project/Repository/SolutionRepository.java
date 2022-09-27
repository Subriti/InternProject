package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Solution;


@Repository
public interface SolutionRepository extends JpaRepository<Solution,Integer> {

}

package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Sfacl;

@Repository
public interface SfaclRepository extends JpaRepository<Sfacl,Integer> {

}

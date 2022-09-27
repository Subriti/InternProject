package com.example.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Staff;
import com.example.project.Model.Status;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

	// select* from Staff where status=?
	@Query("SELECT s FROM Staff s WHERE s.staffStatus=?1")
	List<Staff> findStaffByStatus(Status staffStatus);
}

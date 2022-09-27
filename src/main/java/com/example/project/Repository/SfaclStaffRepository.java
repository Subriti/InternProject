package com.example.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Designation;
import com.example.project.Model.Sfacl;
import com.example.project.Model.SfaclStaff;

@Repository
public interface SfaclStaffRepository extends JpaRepository<SfaclStaff,Integer> {

	//By Designation
	@Query("SELECT s FROM SfaclStaff s WHERE s.designationId=?1")
	List<SfaclStaff> findStaffByDesignation(Designation designationId);

	//By CorpId
	@Query("SELECT s FROM SfaclStaff s WHERE s.corpId=?1")
	List<SfaclStaff> findStaffByCorp(Sfacl corpId);

	//By both
	@Query("SELECT s FROM SfaclStaff s WHERE s.corpId=?1 AND s.designationId=?2")
	List<SfaclStaff> findSfaclStaff(Sfacl corpId, Designation designationId);

	/*
	 * @Query("SELECT s FROM SfaclStaff s WHERE s.corpId = :corpId and s.designationId = :designationId")
	 * List<SfaclStaff> findSfaclStaff(@Param("corpId") Sfacl corpId, @Param("designationId") Designation designationId);
	 */
}

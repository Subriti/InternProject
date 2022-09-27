
package com.example.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Area;
import com.example.project.Model.AreaStaff;
import com.example.project.Model.Type;


@Repository
public interface AreaStaffRepository extends JpaRepository<AreaStaff, Integer> {

	// By both
	  @Query("SELECT a.areaName, s.staffName, n.typeName FROM AreaStaff t JOIN Area a on a.areaId=t.areaId JOIN Staff s on s.staffId= t.staffId JOIN Type n on n.typeId= s.staffType WHERE t.areaId=?1 AND s.staffType=?2"
	  ) List<Object> findStaffByArea(Area areaId, Type typeId);
	 

	// By area
	@Query("SELECT a.areaName, s.staffName FROM AreaStaff t JOIN Area a on a.areaId=t.areaId JOIN Staff s on s.staffId= t.staffId WHERE t.areaId=?1")
	List<Object> findStaffByArea(Area areaId);
}

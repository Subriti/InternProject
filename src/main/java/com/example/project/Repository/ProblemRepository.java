package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
	
}































/*
 * // By From and To Date
 * 
 * @Query("SELECT p.problemId, p.problemDesc, p.issueDate, s.staffName, c.corpName, a.areaName FROM Problem p JOIN Report r on r.problemId=p.problemId JOIN Staff s on s.staffId=r.staffId JOIN Sfacl c on c.corpId=r.corpId JOIN Area a on a.areaId=c.areaId WHERE r.staffId=?1 and p.issueDate between ?2 and ?3 order by p.problemId"
 * ) List<Object> findProblem(Staff staffId, Date fromDate, Date toDate);
 * 
 * //By Staff
 * 
 * @Query("SELECT p.problemId, p.problemDesc, p.issueDate, s.staffName, c.corpName, a.areaName, st.statusName FROM Problem p JOIN Report r on r.problemId=p.problemId JOIN ReportLog l on l.reportId= r.reportId JOIN Staff s on s.staffId=l.assignedTo JOIN Status st on st.statusId=l.assignedStatus JOIN Sfacl c on c.corpId=r.corpId JOIN Area a on a.areaId=c.areaId WHERE r.staffId=:staffId order by p.problemId"
 * ) List<Object> findProblem(Staff staffId);
 * 
 * // all problems
 * 
 * @Query("SELECT p.problemId, p.problemDesc, p.issueDate, s.staffName, c.corpName, a.areaName FROM Problem p JOIN Report r on r.problemId=p.problemId JOIN Staff s on s.staffId=r.staffId JOIN Sfacl c on c.corpId=r.corpId JOIN Area a on a.areaId=c.areaId order by p.problemId"
 * ) List<Object> findProblem();
 */
package com.example.project.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.ObjectModels;
import com.example.project.Model.Problem;
import com.example.project.Repository.ProblemRepository;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class ProblemService {

	private final ProblemRepository problemRepository;

	@Autowired
	public ProblemService(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	public List<Problem> getProblems() {
		return problemRepository.findAll();
	}

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	ZoneId defaultZoneId = ZoneId.systemDefault();
	LocalDate currentDate = LocalDate.now();
	Date endDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());

	int staffId;

	public List<ObjectModels> findProblem(String jsonRequest) {
		JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonRequest);

		String sId = jsonObject.get("staff_id").toString();
		staffId = Integer.parseInt(sId);
		System.out.println("Staff id is " + sId);

		String dId = jsonObject.get("from_date").toString();
		System.out.println("From date is " + dId);

		String tId = jsonObject.get("to_date").toString();
		System.out.println("To date is " + tId);

		String queryStaff = "select p.problem_id, p.problem_desc, p.issue_date, s.staff_name, c.corp_name, a.area_name, st.status_name FROM Problem p JOIN Report r on r.problem_id=p.problem_id JOIN Report_Log l on l.report_id= r.report_id JOIN Staff s on s.staff_id=l.assigned_to JOIN Status st on st.status_id=l.assigned_status JOIN Sfacl c on c.corp_id=r.corp_id JOIN Area a on a.area_id=c.area_id WHERE r.staff_id="
				+ staffId + " order by p.problem_id";

		String queryStaffFromDateString = "select p.problem_id, p.problem_desc, p.issue_date, s.staff_name, c.corp_name, a.area_name FROM Problem p JOIN Report r on r.problem_id=p.problem_id JOIN Staff s on s.staff_id=r.staff_id JOIN Sfacl c on c.corp_id=r.corp_id JOIN Area a on a.area_id=c.area_id WHERE r.staff_id="
				+ staffId + " and p.issue_date between '" + dId + "' and '" + endDate + "' order by p.problem_id";

		String queryOnlyFromDateString = "select p.problem_id, p.problem_desc, p.issue_date, s.staff_name, c.corp_name, a.area_name FROM Problem p JOIN Report r on r.problem_id=p.problem_id JOIN Staff s on s.staff_id=r.staff_id JOIN Sfacl c on c.corp_id=r.corp_id JOIN Area a on a.area_id=c.area_id WHERE p.issue_date between '"
				+ dId + "' and '" + endDate + "' order by p.problem_id";

		String queryOnlyFromDatesString = "select p.problem_id, p.problem_desc, p.issue_date, s.staff_name, c.corp_name, a.area_name FROM Problem p JOIN Report r on r.problem_id=p.problem_id JOIN Staff s on s.staff_id=r.staff_id JOIN Sfacl c on c.corp_id=r.corp_id JOIN Area a on a.area_id=c.area_id WHERE p.issue_date between '"
				+ dId + "' and '" + tId + "' order by p.problem_id";

		String queryStaffBothDateString = "select p.problem_id, p.problem_desc, p.issue_date, s.staff_name, c.corp_name, a.area_name FROM Problem p JOIN Report r on r.problem_id=p.problem_id JOIN Staff s on s.staff_id=r.staff_id JOIN Sfacl c on c.corp_id=r.corp_id JOIN Area a on a.area_id=c.area_id WHERE r.staff_id="
				+ staffId + " and p.issue_date between '" + dId + "' and '" + tId + "' order by p.problem_id";

		String queryAll = "select p.problem_id, p.problem_desc, p.issue_date, s.staff_name, c.corp_name, a.area_name FROM Problem p JOIN Report r on r.problem_id=p.problem_id JOIN Staff s on s.staff_id=r.staff_id JOIN Sfacl c on c.corp_id=r.corp_id JOIN Area a on a.area_id=c.area_id order by p.problem_id";

		// findProblemByStaff(staffId);
		if (staffId > 0 & dId == "" & tId == "") {
			return getAll(queryStaff);
		} else if (staffId > 0 & dId != "" & tId == "") {
			return getAll(queryStaffFromDateString);
		} else if (staffId > 0 & dId != "" & tId != "") {
			return getAll(queryStaffBothDateString);
		} else if (staffId <= 0 & dId != "" & tId == "") {
			return getAll(queryOnlyFromDateString);
		} else if (staffId <= 0 & dId != "" & tId != "") {
			return getAll(queryOnlyFromDatesString);
		}
		return getAll(queryAll);

	}

	public List<ObjectModels> getAll(String queryString) {
		System.out.println(queryString);

		return template.query(queryString, new ResultSetExtractor<List<ObjectModels>>() {

			@Override
			public List<ObjectModels> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ObjectModels> list = new ArrayList<ObjectModels>();
				while (rs.next()) {
					ObjectModels o = new ObjectModels();
					o.setProblemId(rs.getInt(1));
					o.setProblemDesc(rs.getString(2));
					o.setIssueDate(rs.getDate(3));
					o.setStaffName(rs.getString(4));
					o.setCorpName(rs.getString(5));
					o.setAreaName(rs.getString(6));
					// o.setStatusName(rs.getString(7));
					list.add(o);
				}
				return list;
			}

		});
	}

	public void addNewProblem(Problem problem) {
		problemRepository.save(problem);
	}

	public void deleteProblem(int problemId) {
		boolean exists = problemRepository.existsById(problemId);
		if (!exists) {
			throw new IllegalStateException("problem with id " + problemId + "does not exist");
		}
		problemRepository.deleteById(problemId);
	}

	@Transactional
	public void updateProblem(int problemId, Problem Newproblem) {
		Problem problem = problemRepository.findById(problemId)
				.orElseThrow(() -> new IllegalStateException("problem with id " + problemId + " does not exist"));

		if (Newproblem.getProblemDesc() != null && Newproblem.getProblemDesc().length() > 0) {
			problem.setProblemDesc(Newproblem.getProblemDesc());
		}

		if (Newproblem.getIssueDate() != null) {
			problem.setIssueDate(Newproblem.getIssueDate());
		}

	}
}
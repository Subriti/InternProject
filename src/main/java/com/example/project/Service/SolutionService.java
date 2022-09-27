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

import com.example.project.CommonProblemSolutionModel;
import com.example.project.Model.Solution;
import com.example.project.Repository.SolutionRepository;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class SolutionService {

	private final SolutionRepository solutionRepository;

	@Autowired
	public SolutionService(SolutionRepository solutionRepository) {
		this.solutionRepository = solutionRepository;
	}

	public List<Solution> getSolutions() {
		return solutionRepository.findAll();
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

	public List<CommonProblemSolutionModel> findSolution(String jsonRequest) {
		JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonRequest);

		String sId = jsonObject.get("is_solved").toString();
		System.out.println("\nisSolved status is " + sId);

		String dId = jsonObject.get("from_date").toString();
		System.out.println("From date is " + dId);

		String tId = jsonObject.get("to_date").toString();
		System.out.println("To date is " + tId);

		String asignedButNoSolvingAttemptsString = "select p.problem_id, p.problem_desc, s.corp_name, a.area_name, DATE_PART('day', report.reported_date::timestamp - p.issue_date ::timestamp)  from report JOIN problem p on p.problem_id = report.problem_id JOIN sfacl s ON s.corp_id = report.corp_id JOIN area a on a.area_id = s.area_id where report_id not in (select report_id from report_log)";

		String assignedButNoSolutionWithDateFilterString = "select p.problem_id, p.problem_desc, s.corp_name, a.area_name, DATE_PART('day', report.reported_date::timestamp - p.issue_date ::timestamp)  from report JOIN problem p on p.problem_id = report.problem_id JOIN sfacl s ON s.corp_id = report.corp_id JOIN area a on a.area_id = s.area_id where report_id not in (select report_id from report_log) and p.issue_date between '"
				+ dId + "' and '" + tId + "'";

		String assignedButNoSolutionWithFilterString = "select p.problem_id, p.problem_desc, s.corp_name, a.area_name, DATE_PART('day', report.reported_date::timestamp - p.issue_date ::timestamp)  from report JOIN problem p on p.problem_id = report.problem_id JOIN sfacl s ON s.corp_id = report.corp_id JOIN area a on a.area_id = s.area_id where report_id not in (select report_id from report_log) and p.issue_date between '"
				+ dId + "' and '" + endDate + "'";

		String queryAll = "select p.problem_id, p.problem_desc, p.issue_date, c.corp_name, a.area_name from Problem p JOIN report ON report.problem_id = p.problem_id JOIN sfacl c ON c.corp_id = report.corp_id JOIN area a ON a.area_id = c.area_id order by problem_id";

		String getProblemString = "select p.problem_id, p.problem_desc, p.issue_date, c.corp_name, a.area_name from Problem p JOIN report ON report.problem_id = p.problem_id JOIN sfacl c ON c.corp_id = report.corp_id JOIN area a ON a.area_id = c.area_id  where p.problem_id in (select p.problem_id from Problem p JOIN solution s ON s.problem_id = p.problem_id where s.is_solved=true) order by problem_id ";

		String getProblemWithDateString = "select p.problem_id, p.problem_desc, p.issue_date, c.corp_name, a.area_name from Problem p JOIN report ON report.problem_id = p.problem_id JOIN sfacl c ON c.corp_id = report.corp_id JOIN area a ON a.area_id = c.area_id  where p.problem_id in (select p.problem_id from Problem p JOIN solution s ON s.problem_id = p.problem_id where s.is_solved=true and p.issue_date between '"
				+ dId + "' and '" + tId + "') order by problem_id ";

		String getProblemWithNoDateString = "select p.problem_id, p.problem_desc, p.issue_date, c.corp_name, a.area_name from Problem p JOIN report ON report.problem_id = p.problem_id JOIN sfacl c ON c.corp_id = report.corp_id JOIN area a ON a.area_id = c.area_id  where p.problem_id in (select p.problem_id from Problem p JOIN solution s ON s.problem_id = p.problem_id where s.is_solved=true and p.issue_date between '"
				+ dId + "' and '" + endDate + "') order by problem_id ";

		if (sId == "true" & dId == "" & tId == "") {
			return getAll(getProblemString);
		} else if (sId == "true" & dId != "" & tId != "") {
			return getAll(getProblemWithDateString);
		} else if (sId == "true" & dId != "" & tId == "") {
			return getAll(getProblemWithNoDateString);
		} else if (sId == "false" & dId == "" & tId == "") {
			return getUnSolved(asignedButNoSolvingAttemptsString);

		} else if (sId == "false" & dId != "" & tId != "") {
			return getUnSolved(assignedButNoSolutionWithDateFilterString);

		} else if (sId == "false" & dId != "" & tId == "") {
			return getUnSolved(assignedButNoSolutionWithFilterString);

		}
		return getAll(queryAll);
	}

	public List<CommonProblemSolutionModel> getAll(String queryString) {
		System.out.println(queryString);

		return template.query(queryString, new ResultSetExtractor<List<CommonProblemSolutionModel>>() {

			@Override
			public List<CommonProblemSolutionModel> extractData(ResultSet rs) throws SQLException, DataAccessException {

				CommonProblemSolutionModel s = new CommonProblemSolutionModel();
				List<CommonProblemSolutionModel> parentList = new ArrayList<>();
				List<CommonProblemSolutionModel> childList = new ArrayList<>();

				while (rs.next()) {
					s = new CommonProblemSolutionModel();
					String getSolutionString = "select s.solution_desc, st.staff_name, s.solved_date from Solution s JOIN Staff st on st.staff_id = s.solved_by where s.problem_id in ("
							+ (rs.getInt(1)) + ")";

					s.setProblemId(rs.getInt(1));
					System.out.println("\n\nProblem ID is " + s.getProblemId());

					s.setProblemDesc(rs.getString(2));

					s.setIssueDate(rs.getDate(3));

					s.setCorpName(rs.getString(4));
					s.setAreaName(rs.getString(5));

					childList = new ArrayList<>();
					childList = getAllChildList(getSolutionString);

					s.setListSolution(childList);
					parentList.add(s);
				}

				return parentList;
			}

		});
	}

	public String Encryption() {
		return "";
	}
	private List<CommonProblemSolutionModel> getAllChildList(String queryString) {
		System.out.println(queryString);

		return template.query(queryString, new ResultSetExtractor<List<CommonProblemSolutionModel>>() {

			@Override
			public List<CommonProblemSolutionModel> extractData(ResultSet rs) throws SQLException, DataAccessException {

				CommonProblemSolutionModel s = new CommonProblemSolutionModel();
				List<CommonProblemSolutionModel> childList = new ArrayList<>();

				while (rs.next()) {
					s = new CommonProblemSolutionModel();

					s.setSolutionDesc(rs.getString(1));
					s.setStaffName(rs.getString(2));
					s.setSolvedDate(rs.getDate(3));

					childList.add(s);
				}

				return childList;

			}

		});
	}

	public List<CommonProblemSolutionModel> getUnSolved(String queryString) {
		System.out.println(queryString);

		return template.query(queryString, new ResultSetExtractor<List<CommonProblemSolutionModel>>() {

			@Override
			public List<CommonProblemSolutionModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<CommonProblemSolutionModel> list = new ArrayList<>();
				while (rs.next()) {
					CommonProblemSolutionModel s = new CommonProblemSolutionModel();
					s.setProblemId(rs.getInt(1));
					s.setProblemDesc(rs.getString(2));
					s.setUnsolvedDays(rs.getInt(5));
					s.setCorpName(rs.getString(3));
					s.setAreaName(rs.getString(4));

					list.add(s);
				}
				return list;
			}

		});
	}

	public void addNewSolution(Solution solution) {
		solutionRepository.save(solution);

	}

	public void deleteSolution(int solutionId) {
		boolean exists = solutionRepository.existsById(solutionId);
		if (!exists) {
			throw new IllegalStateException("solution with id " + solutionId + "does not exist");
		}
		solutionRepository.deleteById(solutionId);
	}

	@Transactional
	public void updateSolution(int solutionId, Solution Newsolution) {
		Solution solution = solutionRepository.findById(solutionId)
				.orElseThrow(() -> new IllegalStateException("solution with id " + solutionId + " does not exist"));

		if (Newsolution.getSolutionDesc() != null && Newsolution.getSolutionDesc().length() > 0) {
			solution.setSolutionDesc(Newsolution.getSolutionDesc());
		}

		if (Newsolution.getProblemId() != null) {
			solution.setProblemId(Newsolution.getProblemId());
		}

		if (Newsolution.getSolvedDate() != null) {
			solution.setSolvedDate(Newsolution.getSolvedDate());
		}

	}
}

package com.niit.collaborationback.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.niit.collaborationback.dao.AppliedJobsDAO;
import com.niit.collaborationback.model.AppliedJobs;
import com.niit.collaborationback.model.User;

public class AppliedJobsController {
	@Autowired
	private AppliedJobsDAO  appliedJobsDAO;

	
public AppliedJobsDAO getAppliedJobsDAO() {
		return appliedJobsDAO;
	}

	public void setAppliedJobsDAO(AppliedJobsDAO appliedJobsDAO) {
		this.appliedJobsDAO = appliedJobsDAO;
	}

@GetMapping("/allappliedjobs")

public List<AppliedJobs> ajobs()
	{	
	return appliedJobsDAO.list();
}

@GetMapping("/alljobId/{jobId}")
public List<AppliedJobs> ajobsId(@PathVariable("jobId")int jobId)
{	
	return appliedJobsDAO.getByJobId(jobId);
	}

@GetMapping("/alljobuserid/{userId}") 
public List<AppliedJobs> ajobsuid(@PathVariable("userId") int userId)
{	
return appliedJobsDAO.getByUserId(userId);
}

@GetMapping("/alljobusername/{username}") 
public List<AppliedJobs> ajobsuname(@PathVariable("username")String userName)
{	
return appliedJobsDAO.getByUserName(userName);
}


@SuppressWarnings({ "rawtypes", "unchecked" })
@GetMapping("/appliedjobs/{ajobid}") 
public ResponseEntity getaJobs(@PathVariable("ajobid") int jobId) {

	AppliedJobs ajob = appliedJobsDAO.getByAJobId(jobId);
	if (ajob == null) {
		return new ResponseEntity("No  Applied Job found for ID " + jobId, HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity(ajob, HttpStatus.OK);
}
@PostMapping(value = "/appliedjobs")
public ResponseEntity applyJob(@RequestBody AppliedJobs appliedJobs, HttpSession session) {
	User user = (User) session.getAttribute("user");
	appliedJobs.setDateTime(new Date());
	appliedJobs.setUserId(user.getUserId());
	appliedJobs.setEmail(user.getEmail());
	
	appliedJobsDAO.saveOrUpdate(appliedJobs);

	return new ResponseEntity(appliedJobs, HttpStatus.OK);
}
@SuppressWarnings({ "rawtypes", "unchecked" })
@DeleteMapping("/appliedjobs/{jobid}") 
public ResponseEntity deleteaJob(@PathVariable int id) {
	AppliedJobs ajob=appliedJobsDAO.getByAJobId(id);
		if (ajob==null) {
		return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
	}
		appliedJobsDAO.delete(id);
	return new ResponseEntity(id, HttpStatus.OK);

}
}

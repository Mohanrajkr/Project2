package com.niit.collaborationback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationback.dao.JobDAO;
import com.niit.collaborationback.model.Job;



@RestController
public class JobController {
	@Autowired
	private JobDAO jobDAO;
	
	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	@GetMapping("/job")
	public ResponseEntity<List<Job>> getJob() {
		List<Job> listjob = jobDAO.list();
		return new ResponseEntity<List<Job>>(listjob, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/job/{jobId}")
	public ResponseEntity getByJobid(@PathVariable("jobId") int jobId) {

		Job job = jobDAO.getByJobid(jobId);
		if (job == null) {
			return new ResponseEntity("No Job found for ID " + jobId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/job")
	public ResponseEntity createJob(@RequestBody Job job) {

		jobDAO.create(job);

		return new ResponseEntity(job, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/job/{jobId}")
	public ResponseEntity deleteJob(@PathVariable int jobId) {
		Job job=jobDAO.getByJobid(jobId);
 		if (job==null) {
			return new ResponseEntity("No Job found for ID " + jobId, HttpStatus.NOT_FOUND);
		}
 		jobDAO.delete(jobId);
		return new ResponseEntity(jobId, HttpStatus.OK);
   }
	
	@SuppressWarnings("unchecked")
	@PutMapping("/job/{jobId}")
	public ResponseEntity saveorupdateJob(@PathVariable int jobId, @RequestBody Job job) {

		job = jobDAO.saveOrUpdate(job);
		if (null == job) {
			return new ResponseEntity("No Job found for ID " + jobId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
}

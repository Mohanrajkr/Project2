package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.AppliedJobs;

public interface AppliedJobsDAO {
public List<AppliedJobs>list();
	
	public AppliedJobs saveOrUpdate(AppliedJobs jobs);
	
	public List<AppliedJobs> getByEmail(String email);
	
	public  List<AppliedJobs> getByUserid(int userId);
	
	public AppliedJobs getByTitle(String title);
	
	public AppliedJobs getByJobid(int jobId);
	
	public List<AppliedJobs>getMyAppliedJobs(String email);
}

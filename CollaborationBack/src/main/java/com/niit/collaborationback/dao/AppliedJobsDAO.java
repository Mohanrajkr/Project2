package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.AppliedJobs;

public interface AppliedJobsDAO {
public List<AppliedJobs> getByJobId(int jobId);    
	
	public List<AppliedJobs> getByUserName(String userName); 
	
	public List<AppliedJobs> getByUserId(int userId);
	
	public void saveOrUpdate(AppliedJobs ajob);

	public AppliedJobs getByAJobId(int jobId);	
	
	public void delete(int id);

	public List<AppliedJobs> list();
}

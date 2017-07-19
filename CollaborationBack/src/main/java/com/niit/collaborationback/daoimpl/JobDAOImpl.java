package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationback.dao.JobDAO;
import com.niit.collaborationback.model.Job;


@Repository("JobDAO")
public class JobDAOImpl implements JobDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Job> list() {
		@SuppressWarnings("unchecked")
		List<Job> listJob = (List<Job>) 
		sessionFactory.getCurrentSession().createCriteria(Job.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listJob;	
	}
	@Transactional
	public Job saveOrUpdate(Job job) {
		sessionFactory.getCurrentSession().save(job);
         return job;
	}
	
	@Transactional	
	public Job create(Job job) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(job);
		return job;
	}
	@Transactional
	public void delete(int jobId) {
		Job jobToDelete = new Job();
		jobToDelete.setJobId(jobId);
		sessionFactory.getCurrentSession().delete(jobToDelete);
	}
	@Transactional
	public Job getByJobid(int jobId){ 
		Job JobId = (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);

		return JobId;
	}
	@Transactional
	public Job getByJobcategory(String companyName) {
		Job JobCategory = (Job) sessionFactory.getCurrentSession().get(Job.class, companyName);

		return JobCategory;
	}
}

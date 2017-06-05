package com.niit.collaborationback.daoimpl;

import java.util.List;

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
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		List<Job> listJob = sessionFactory.getCurrentSession().createQuery("from Job").list();
		return listJob;
	}
	@Transactional
	public void save(Job job) {
		sessionFactory.getCurrentSession().save(job);

	}
	@Transactional
	public void update(Job job) {
		sessionFactory.getCurrentSession().update(job);

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
	public Job getByJobcategory(String jobCategory) {
		Job JobCategory = (Job) sessionFactory.getCurrentSession().get(Job.class, jobCategory);

		return JobCategory;
	}
}

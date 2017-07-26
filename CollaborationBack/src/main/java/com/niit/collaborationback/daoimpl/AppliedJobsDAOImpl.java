package com.niit.collaborationback.daoimpl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationback.dao.AppliedJobsDAO;
import com.niit.collaborationback.model.AppliedJobs;

@Repository("AppliedJobsDAO")
public class AppliedJobsDAOImpl implements AppliedJobsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public AppliedJobsDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<AppliedJobs> list() {
		return sessionFactory.getCurrentSession().createQuery("from AppliedJobs").list();
	}

	public List<AppliedJobs> getByJobId(int jobId) {
		Session session=sessionFactory.openSession();
		
		String hql = "from AppliedJobs where jobId ='" + jobId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AppliedJobs> ajobs=query.list();
		session.close();
		return ajobs;
	}

	public List<AppliedJobs> getByUserName(String userName) {
		Session session=sessionFactory.openSession();
		String hql = "from AppliedJobs where userName ='" + userName + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		@SuppressWarnings("unchecked")
		List<AppliedJobs> uemails=query.list();
		session.close();
		return uemails;
	}

	public void saveOrUpdate(AppliedJobs ajob) {
		sessionFactory.getCurrentSession().saveOrUpdate(ajob);
		
	}

	public AppliedJobs getByAJobId(int jobId) {   
		AppliedJobs ajobListByID = (AppliedJobs) sessionFactory.getCurrentSession().get(AppliedJobs.class, jobId);

		return ajobListByID;
	}
	public void delete(int id) {
		AppliedJobs ajobDelete = new AppliedJobs();
		//ajobDelete.setId(id);
		sessionFactory.getCurrentSession().delete(ajobDelete);
		
	}
	public List<AppliedJobs> getByUserId(int userId) {
		
		Session session=sessionFactory.openSession();
		String hql = "from AppliedJobs where userId ='" + userId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AppliedJobs> uemails=query.list();
		session.close();
		return uemails;
	}
}

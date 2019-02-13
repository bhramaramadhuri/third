package com.comtroller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonDao {
	@Autowired
	SessionFactory sessionFactory;
	public void savePerson(Person p)
	{
		sessionFactory.getCurrentSession().save(p);
	}
	public void deletePerson(Person p)
	{
	sessionFactory.getCurrentSession().delete(p);
	
	}
	public void updatePerson(Person p)
	{
		sessionFactory.getCurrentSession().update(p);
	}
	public List<Person> FetchPerson()
	{
		List<Person> lp=sessionFactory.getCurrentSession().createQuery("from Person").list();
		return lp;
	}
	public Person getPersonById(int pid)
	{
	Person lp=(Person)sessionFactory.getCurrentSession().createQuery("from Person where id=:pid").setParameter("pid",pid).uniqueResult();
		return lp;
	}
}

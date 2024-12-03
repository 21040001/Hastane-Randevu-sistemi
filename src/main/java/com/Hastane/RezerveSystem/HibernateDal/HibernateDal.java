package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository 
public class HibernateDal implements DALRezerveInterface, DALDoctorInterface, DALUserInterface{
	private final EntityManager entityManager;
	
	@Autowired
	public HibernateDal(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	@Transactional
	public List<ObjectOfRezerveTable> getAllRezerves() {
		Session session = entityManager.unwrap(Session.class);
		List<ObjectOfRezerveTable> rezerves = session.createNativeQuery("Select * FROM HastaneRS.dbo.rezerves ",ObjectOfRezerveTable.class).getResultList();
		return rezerves;
	}

	@Override
	@Transactional
	public List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName) {
		Session session = entityManager.unwrap(Session.class);
		List<ObjectOfRezerveTable> rezerves = session.createNativeQuery("Select * FROM HastaneRS.dbo.rezerves where doctor='"+doctorName+"'",ObjectOfRezerveTable.class).getResultList();
		return rezerves;
	}

	@Override
	@Transactional
	public List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId) {
		Session session = entityManager.unwrap(Session.class);
		List<ObjectOfRezerveTable> rezerves = session.createNativeQuery("Select * FROM HastaneRS.dbo.rezerves where idUser="+userId,ObjectOfRezerveTable.class).getResultList();
		return rezerves;
	}

	@Override
	@Transactional
	public ObjectOfRezerveTable getRezerveById(int rezerveId) {
		Session session = entityManager.unwrap(Session.class);
		ObjectOfRezerveTable user = session.get(ObjectOfRezerveTable.class, rezerveId);
		return user;
	}

	@Override
	@Transactional
	public ObjectOfUserTable getUserById( int id) {
		Session session = entityManager.unwrap(Session.class);
		ObjectOfUserTable user = session.get(ObjectOfUserTable.class, id);
		return user;
	}

	@Override
	@Transactional
	public ObjectOfDoctorsTable getDoctorByID(int id) {
		Session session = entityManager.unwrap(Session.class);
		ObjectOfDoctorsTable doctor = session.get(ObjectOfDoctorsTable.class, id);
		return doctor;
	}

	@Override
	@Transactional
	public List<ObjectOfDoctorsTable> getAllDoctors() {
		Session session = entityManager.unwrap(Session.class);
		List<ObjectOfDoctorsTable> doctors = session.createNativeQuery("Select * FROM HastaneRS.dbo.doctors ",ObjectOfDoctorsTable.class).getResultList();
		return doctors;
	}
}

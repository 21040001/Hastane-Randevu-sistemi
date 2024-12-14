package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository 
public class HibernateDal implements DALRezerveInterface, DALDoctorInterface, DALUserInterface, DALMessagesInterface{
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
		List<ObjectOfRezerveTable> rezerves = session.createNativeQuery("Select * FROM HastaneRS.dbo.rezerves where id_user="+userId,ObjectOfRezerveTable.class).getResultList();
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
		ObjectOfDoctorsTable doctor = session.createNativeQuery("Select * FROM HastaneRS.dbo.doctors where id = "+id,ObjectOfDoctorsTable.class).getSingleResult();
		return doctor;
	}

	@Override
	@Transactional
	public List<String> getAllDoctors() {
		Session session = entityManager.unwrap(Session.class);
		List<String> doctors = session.createNativeQuery("Select name FROM HastaneRS.dbo.doctors ",String.class).getResultList();
		return doctors;
	}


	@Override
	@Transactional
	public String getPasswordByTcNumber(int TcNumber) {
		Session session = entityManager.unwrap(Session.class);
		try {
			String password = session.createNativeQuery("Select password FROM HastaneRS.dbo.doctors where tc_number = "+TcNumber, String.class).getSingleResult();	
			return password;
		}catch(NoResultException e){
			String password = session.createNativeQuery("Select password FROM HastaneRS.dbo.users where TCNumber = "+TcNumber, String.class).getSingleResult();	
			return password;
		}
	}


	@Override
	@Transactional
	public void addRandevu(ObjectOfRezerveTable object) {
		Session session = entityManager.unwrap(Session.class);
		try {
			session.persist(object);
		}catch(NoResultException e){
			System.out.print(e);
		}
		
	}


	@Override
	@Transactional
	public int getIdUserByTcNumber(int TcNumber) {
		Session session = entityManager.unwrap(Session.class);
		try {
			int id = session.createNativeQuery("Select id FROM HastaneRS.dbo.doctors where tc_number = "+TcNumber, Integer.class).getSingleResult();	
			return id;
		}catch(NoResultException e){
			int id = session.createNativeQuery("Select id FROM HastaneRS.dbo.users where TCNumber = "+TcNumber, Integer.class).getSingleResult();	
			return id;
		}
	}


	@Override
	@Transactional
	public boolean itIsDoctor(int TcNumber) {
		Session session = entityManager.unwrap(Session.class);
		try {
			int id = session.createNativeQuery("SELECT 1 FROM doctors WHERE tc_number = " + TcNumber, Integer.class).getSingleResult();
			return true;
		}catch(NoResultException e){
			System.out.println(e);
			return false;
		}
	}


	@Override
	@Transactional
	public ObjectOfMessagesTable message(int id) {
		Session session = entityManager.unwrap(Session.class);
		try {
			ObjectOfMessagesTable message = session.createNativeQuery("Select id FROM HastaneRS.dbo.messages where id = "+id, ObjectOfMessagesTable.class).getSingleResult();	
			return message;
		}catch(NoResultException e){
			System.out.print(e);
			return null;
		}
	}


	@Override
	@Transactional
	public List<ObjectOfMessagesTable> getAllMessages(int userId) {
		Session session = entityManager.unwrap(Session.class);
		List<ObjectOfMessagesTable> messages = session.createNativeQuery("Select * FROM HastaneRS.dbo.messages where receiver = "+userId,ObjectOfMessagesTable.class).getResultList();
		return messages;
	}


	@Override
	@Transactional
	public void sendToMail(ObjectOfMessagesTable object) {
		Session session = entityManager.unwrap(Session.class);
		try {
			session.persist(object);
		}catch(NoResultException e){
			System.out.print(e);
		}
	}


	@Override
	@Transactional
	public int getDoctorIdByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		int doctorId = session.createNativeQuery("Select id FROM HastaneRS.dbo.doctors where name='"+name+"'",Integer.class).getSingleResult();
		return doctorId;
	}


	@Override
	@Transactional
	public void deleteRandevu(int randevuId) {
		Session session = entityManager.unwrap(Session.class);
		try {
			session.createNativeQuery("DELETE FROM rezerves WHERE id="+randevuId,Integer.class).executeUpdate();
		}catch(NoResultException e){
			System.out.print(e);
		}
	}
	
}

package com.Hastane.RezerveSystem.BusinessLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;
import com.Hastane.RezerveSystem.HibernateDal.*;

@Service
public class BusinessLayerQ implements BusinessLayer{
	
	private final DALDoctorInterface doctor;
	private final DALRezerveInterface randevu;
	private final DALUserInterface user;
	private final DALMessagesInterface message;
	
	@Autowired
	public BusinessLayerQ(DALDoctorInterface doctor, DALRezerveInterface randevu, DALUserInterface user, DALMessagesInterface message) {
		this.doctor = doctor;
		this.randevu = randevu;
		this.user = user;
		this.message = message;
	}

	@Override
	public List<ObjectOfRezerveTable> getAllRezerves() {
		return randevu.getAllRezerves();
	}

	@Override
	public List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName) {
		return randevu.getDoctorsRezervesByDoctorName(doctorName);
	}

	@Override
	public List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId) {
		return randevu.getUserRezervesByUserId(userId);
	}

	@Override
	public ObjectOfRezerveTable getRezerveById(int rezerveId) {
		return randevu.getRezerveById(rezerveId);
	}

	@Override
	public ObjectOfUserTable getUserById(int id) {
		return user.getUserById(id);
	}

	@Override
	public ObjectOfDoctorsTable getDoctorByID(int id) {
		return doctor.getDoctorByID(id);
	}

	@Override
	public List<String> getAllDoctors() {
		return doctor.getAllDoctors();
	}

	@Override
	public String getPasswordByTcNumber(int TcNumber) {
		return doctor.getPasswordByTcNumber(TcNumber);
	}

	@Override
	public void addRandevu(ObjectOfRezerveTable object) {
		randevu.addRandevu(object);
	}

	@Override
	public int getIdUserByTcNumber(int tcNumber) {
		return user.getIdUserByTcNumber(tcNumber);
	}

	@Override
	public boolean itIsDoctor(int TcNumber) {
		return doctor.itIsDoctor(TcNumber);
	}

	@Override
	public ObjectOfMessagesTable message(int id) {
		return message.message(id);
	}

	@Override
	public List<ObjectOfMessagesTable> getAllMessages(int userId) {
		return message.getAllMessages(userId);
	}

	@Override
	public void sendToMail(ObjectOfMessagesTable object) {
		message.sendToMail(object);
	}

	@Override
	public int getDoctorIdByName(String name) {
		return doctor.getDoctorIdByName(name);
	}

	@Override
	public void deleteRandevu(int randevuId) {
		randevu.deleteRandevu(randevuId);
	}
	
}

package com.Hastane.RezerveSystem.BusinessLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;
import com.Hastane.RezerveSystem.HibernateDal.*;

@Service
public class BusinessLayerQ implements BusinessLayer{
	
	private final DALDoctorInterface doctor;
	private final DALRezerveInterface rezerve;
	private final DALUserInterface user;
	
	@Autowired
	public BusinessLayerQ(DALDoctorInterface doctor, DALRezerveInterface rezerve, DALUserInterface user) {
		this.doctor = doctor;
		this.rezerve = rezerve;
		this.user = user;
	}

	@Override
	public List<ObjectOfRezerveTable> getAllRezerves() {
		return rezerve.getAllRezerves();
	}

	@Override
	public List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName) {
		return rezerve.getDoctorsRezervesByDoctorName(doctorName);
	}

	@Override
	public List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId) {
		return rezerve.getUserRezervesByUserId(userId);
	}

	@Override
	public ObjectOfRezerveTable getRezerveById(int rezerveId) {
		return rezerve.getRezerveById(rezerveId);
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
	public List<ObjectOfDoctorsTable> getAllDoctors() {
		return doctor.getAllDoctors();
	}
	
}

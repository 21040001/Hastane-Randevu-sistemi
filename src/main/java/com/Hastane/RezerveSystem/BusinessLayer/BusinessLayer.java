package com.Hastane.RezerveSystem.BusinessLayer;

import java.util.List;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

public interface BusinessLayer {
	List<ObjectOfRezerveTable> getAllRezerves();
	List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName);
	List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId);
	ObjectOfRezerveTable getRezerveById(int rezerveId);
	ObjectOfUserTable getUserById(int id);
	ObjectOfDoctorsTable getDoctorByID(int id);
	List<ObjectOfDoctorsTable> getAllDoctors();
}

package com.Hastane.RezerveSystem.BusinessLayer;

import java.util.List;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

public interface BusinessLayer {
	List<ObjectOfRezerveTable> getAllRezerves();
	List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName);
	List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId);
	ObjectOfRezerveTable getRezerveById(int rezerveId);
	ObjectOfUserTable getUserById(int id);
	ObjectOfDoctorsTable getDoctorByID(int id);
	List<String> getAllDoctors();
	String getPasswordByTcNumber(int TcNumber);
	void addRandevu(ObjectOfRezerveTable object);
	int getIdUserByTcNumber(int tcNumber);
	boolean itIsDoctor(int TcNumber);
	ObjectOfMessagesTable message(int id);
	List<ObjectOfMessagesTable> getAllMessages(int userId);
	void sendToMail(ObjectOfMessagesTable object);
	int getDoctorIdByName(String name);
	void deleteRandevu(int randevuId);
}

package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;

import com.Hastane.RezerveSystem.Entity.*;

//Bu interface icinde sadece Rezerve Table ile ilgili sorularin methodlari ve degerleri kontrol ediliyor 
public interface DALRezerveInterface {
	List<ObjectOfRezerveTable> getAllRezerves();
	List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName);
	List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId);
	ObjectOfRezerveTable getRezerveById(int rezerveId);
	void addRandevu(ObjectOfRezerveTable object);
	void deleteRandevu(int randevuId);
}

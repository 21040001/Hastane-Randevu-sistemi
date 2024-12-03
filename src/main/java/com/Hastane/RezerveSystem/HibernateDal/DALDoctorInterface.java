package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;

//Bu interface icinde sadece Doctor isimli Table ile ilgili sorularin methodlari ve degerleri kontrol ediliyor
public interface DALDoctorInterface {
	ObjectOfDoctorsTable getDoctorByID(int id);
	List<ObjectOfDoctorsTable> getAllDoctors();
}

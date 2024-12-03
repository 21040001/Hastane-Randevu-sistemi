package com.Hastane.RezerveSystem.HibernateDal;

import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

//Bu interface icinde sadece Doctor isimli Table ile ilgili sorularin methodlari ve degerleri kontrol ediliyor
public interface DALUserInterface {
	ObjectOfUserTable getUserById(int id);
}

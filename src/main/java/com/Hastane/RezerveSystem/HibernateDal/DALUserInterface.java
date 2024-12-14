package com.Hastane.RezerveSystem.HibernateDal;

import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

// Bu interface, kullanıcılar (User tablosu) ile ilgili işlemleri tanımlar
public interface DALUserInterface {

    // Kullanıcı ID'sine göre kullanıcı bilgilerini döndürür
    ObjectOfUserTable getUserById(int id);

    // TC numarasına göre kullanıcı ID'sini döndürür
    int getIdUserByTcNumber(int tcNumber);
}

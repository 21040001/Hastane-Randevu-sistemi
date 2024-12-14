package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;
import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;

// Bu interface, doktorlar (Doctor tablosu) ile ilgili işlemleri tanımlar
public interface DALDoctorInterface {

    // Belirtilen doktor ID'sine göre doktor bilgilerini döndürür
    ObjectOfDoctorsTable getDoctorByID(int id);

    // Tüm doktorların isim listesini döndürür
    List<String> getAllDoctors();

    // Belirli bir TC numarasına göre doktorun şifresini döndürür
    String getPasswordByTcNumber(int TcNumber);

    // TC numarasının bir doktora ait olup olmadığını kontrol eder
    boolean itIsDoctor(int TcNumber);

    // Doktorun adını kullanarak ID'sini döndürür
    int getDoctorIdByName(String name);
}

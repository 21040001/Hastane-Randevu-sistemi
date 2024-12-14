package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;
import com.Hastane.RezerveSystem.Entity.*;

// Bu interface, Rezerve tablosu ile ilgili işlemleri tanımlar
public interface DALRezerveInterface {

    // Tüm rezervasyon kayıtlarını döndürür
    List<ObjectOfRezerveTable> getAllRezerves();

    // Belirtilen doktor adına ait rezervasyonları döndürür
    List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName);

    // Belirtilen kullanıcı ID'sine ait rezervasyonları döndürür
    List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId);

    // Belirtilen rezervasyon ID'sine göre rezervasyon kaydını döndürür
    ObjectOfRezerveTable getRezerveById(int rezerveId);

    // Yeni bir rezervasyon ekler
    void addRandevu(ObjectOfRezerveTable object);

    // Belirtilen rezervasyon ID'sine göre rezervasyonu siler
    void deleteRandevu(int randevuId);
}

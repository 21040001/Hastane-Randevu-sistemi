package com.Hastane.RezerveSystem.BusinessLayer;

import java.util.List;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;

// İş katmanı için bir arayüz tanımı
// Uygulama mantığının temel işlevlerini sağlar
public interface BusinessLayer {

    // Tüm rezervasyonları döndürür
    List<ObjectOfRezerveTable> getAllRezerves();

    // Belirli bir doktor adına göre rezervasyonları döndürür
    List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName);

    // Belirli bir kullanıcı ID'sine göre rezervasyonları döndürür
    List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId);

    // Belirli bir rezervasyon ID'sine göre rezervasyon kaydını döndürür
    ObjectOfRezerveTable getRezerveById(int rezerveId);

    // Belirli bir kullanıcı ID'sine göre kullanıcı bilgilerini döndürür
    ObjectOfUserTable getUserById(int id);

    // Belirli bir doktor ID'sine göre doktor bilgilerini döndürür
    ObjectOfDoctorsTable getDoctorByID(int id);

    // Tüm doktorların isim listesini döndürür
    List<String> getAllDoctors();

    // Belirli bir TC numarasına göre kullanıcı veya doktor şifresini döndürür
    String getPasswordByTcNumber(int TcNumber);

    // Yeni bir rezervasyon ekler
    void addRandevu(ObjectOfRezerveTable object);

    // Belirli bir TC numarasına göre kullanıcı ID'sini döndürür
    int getIdUserByTcNumber(int tcNumber);

    // TC numarasının bir doktora ait olup olmadığını kontrol eder
    boolean itIsDoctor(int TcNumber);

    // Belirli bir mesajı ID'sine göre döndürür
    ObjectOfMessagesTable message(int id);

    // Belirli bir kullanıcıya ait tüm mesajları döndürür
    List<ObjectOfMessagesTable> getAllUserMessages(int userId);

    // Mesaj gönderir ve veritabanına ekler
    void sendToMail(ObjectOfMessagesTable object);

    // Doktorun adını kullanarak ID'sini döndürür
    int getDoctorIdByName(String name);

    // Belirli bir rezervasyonu ID'sine göre siler
    void deleteRandevu(int randevuId);
    
    // Belirtilen kullanıcı ID'sine ait tüm mesajları döndürür
    List<ObjectOfMessagesTable> getAllDoctorMessages(int doctorId);
}

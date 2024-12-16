package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;

// Bu interface, mesajlar (Messages tablosu) ile ilgili işlemleri tanımlar
public interface DALMessagesInterface {

    // Belirtilen mesaj ID'sine göre mesaj kaydını döndürür
    ObjectOfMessagesTable message(int id);

    // Belirtilen kullanıcı ID'sine ait tüm mesajları döndürür
    List<ObjectOfMessagesTable> getAllUserMessages(int userId);
    
    // Belirtilen kullanıcı ID'sine ait tüm mesajları döndürür
    List<ObjectOfMessagesTable> getAllDoctorMessages(int doctorId);

    // Yeni bir mesaj gönderir ve veritabanına kaydeder
    void sendToMail(ObjectOfMessagesTable object);
}

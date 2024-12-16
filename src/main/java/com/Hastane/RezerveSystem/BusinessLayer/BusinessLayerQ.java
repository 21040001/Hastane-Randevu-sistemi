package com.Hastane.RezerveSystem.BusinessLayer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;
import com.Hastane.RezerveSystem.HibernateDal.*;

// İş katmanı sınıfı: Veritabanı erişim katmanıyla (DAL) kullanıcı arayüzü arasındaki iş mantığını yönetir
@Service
public class BusinessLayerQ implements BusinessLayer {

    private final DALDoctorInterface doctor; // Doktorla ilgili işlemler için DAL katmanı
    private final DALRezerveInterface randevu; // Randevularla ilgili işlemler için DAL katmanı
    private final DALUserInterface user; // Kullanıcılarla ilgili işlemler için DAL katmanı
    private final DALMessagesInterface message; // Mesajlarla ilgili işlemler için DAL katmanı

    // Yapıcı metot: Gerekli DAL katmanları dependency injection ile sağlanır
    @Autowired
    public BusinessLayerQ(DALDoctorInterface doctor, DALRezerveInterface randevu, DALUserInterface user, DALMessagesInterface message) {
        this.doctor = doctor;
        this.randevu = randevu;
        this.user = user;
        this.message = message;
    }

    @Override
    public List<ObjectOfRezerveTable> getAllRezerves() {
        // Tüm randevuları getirir
        return randevu.getAllRezerves();
    }

    @Override
    public List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName) {
        // Belirli bir doktora ait randevuları getirir
        return randevu.getDoctorsRezervesByDoctorName(doctorName);
    }

    @Override
    public List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId) {
        // Belirli bir kullanıcıya ait randevuları getirir
        return randevu.getUserRezervesByUserId(userId);
    }

    @Override
    public ObjectOfRezerveTable getRezerveById(int rezerveId) {
        // Belirli bir randevuyu ID'sine göre getirir
        return randevu.getRezerveById(rezerveId);
    }

    @Override
    public ObjectOfUserTable getUserById(int id) {
        // Kullanıcı bilgilerini ID'sine göre getirir
        return user.getUserById(id);
    }

    @Override
    public ObjectOfDoctorsTable getDoctorByID(int id) {
        // Doktor bilgilerini ID'sine göre getirir
        return doctor.getDoctorByID(id);
    }

    @Override
    public List<String> getAllDoctors() {
        // Tüm doktorların isim listesini getirir
        return doctor.getAllDoctors();
    }

    @Override
    public String getPasswordByTcNumber(int TcNumber) {
        // Belirli bir TC numarasına ait şifreyi getirir
        return doctor.getPasswordByTcNumber(TcNumber);
    }

    @Override
    public void addRandevu(ObjectOfRezerveTable object) {
        // Yeni bir randevu ekler
        randevu.addRandevu(object);
    }

    @Override
    public int getIdUserByTcNumber(int tcNumber) {
        // TC numarasına ait kullanıcı ID'sini getirir
        return user.getIdUserByTcNumber(tcNumber);
    }

    @Override
    public boolean itIsDoctor(int TcNumber) {
        // TC numarasının bir doktora ait olup olmadığını kontrol eder
        return doctor.itIsDoctor(TcNumber);
    }

    @Override
    public ObjectOfMessagesTable message(int id) {
        // Belirli bir mesajı ID'sine göre getirir
        return message.message(id);
    }

    @Override
    public List<ObjectOfMessagesTable> getAllUserMessages(int userId) {
        // Belirli bir kullanıcıya ait tüm mesajları getirir
        return message.getAllUserMessages(userId);
    }

    @Override
    public void sendToMail(ObjectOfMessagesTable object) {
        // Mesajı veritabanına ekler (e-posta gönderme işlemi simüle edilir)
        message.sendToMail(object);
    }

    @Override
    public int getDoctorIdByName(String name) {
        // Doktorun adını kullanarak ID'sini getirir
        return doctor.getDoctorIdByName(name);
    }

    @Override
    public void deleteRandevu(int randevuId) {
        // Belirli bir randevuyu ID'sine göre siler
        randevu.deleteRandevu(randevuId);
    }

	@Override
	public List<ObjectOfMessagesTable> getAllDoctorMessages(int doctorId) {
		return message.getAllDoctorMessages(doctorId);
	}
}

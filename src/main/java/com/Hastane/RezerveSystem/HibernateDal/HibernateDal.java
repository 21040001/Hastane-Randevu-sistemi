package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.Hastane.RezerveSystem.Entity.ObjectOfDoctorsTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfRezerveTable;
import com.Hastane.RezerveSystem.Entity.ObjectOfUserTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository // Spring tarafından bir repository olarak işaretlenir
public class HibernateDal implements DALRezerveInterface, DALDoctorInterface, DALUserInterface, DALMessagesInterface {

    private final EntityManager entityManager;

    @Autowired
    public HibernateDal(EntityManager entityManager) {
        super();
        this.entityManager = entityManager; // Hibernate için EntityManager kullanımı
    }

    @Override
    @Transactional
    public List<ObjectOfRezerveTable> getAllRezerves() {
        // Tüm rezervasyonları veritabanından çeker
        Session session = entityManager.unwrap(Session.class);
        List<ObjectOfRezerveTable> rezerves = session.createNativeQuery(
            "Select * FROM HastaneRS.dbo.rezerves ", ObjectOfRezerveTable.class).getResultList();
        return rezerves;
    }

    @Override
    @Transactional
    public List<ObjectOfRezerveTable> getDoctorsRezervesByDoctorName(String doctorName) {
        // Belirtilen doktor adına göre rezervasyonları çeker
        Session session = entityManager.unwrap(Session.class);
        List<ObjectOfRezerveTable> rezerves = session.createNativeQuery(
            "Select * FROM HastaneRS.dbo.rezerves where doctor='" + doctorName + "'", ObjectOfRezerveTable.class).getResultList();
        return rezerves;
    }

    @Override
    @Transactional
    public List<ObjectOfRezerveTable> getUserRezervesByUserId(int userId) {
        // Belirtilen kullanıcı ID'sine göre rezervasyonları çeker
        Session session = entityManager.unwrap(Session.class);
        List<ObjectOfRezerveTable> rezerves = session.createNativeQuery(
            "Select * FROM HastaneRS.dbo.rezerves where id_user=" + userId, ObjectOfRezerveTable.class).getResultList();
        return rezerves;
    }

    @Override
    @Transactional
    public ObjectOfRezerveTable getRezerveById(int rezerveId) {
        // Belirtilen rezervasyon ID'sine göre rezervasyon kaydını çeker
        Session session = entityManager.unwrap(Session.class);
        return session.get(ObjectOfRezerveTable.class, rezerveId);
    }

    @Override
    @Transactional
    public ObjectOfUserTable getUserById(int id) {
        // Belirtilen kullanıcı ID'sine göre kullanıcı kaydını çeker
        Session session = entityManager.unwrap(Session.class);
        return session.get(ObjectOfUserTable.class, id);
    }

    @Override
    @Transactional
    public ObjectOfDoctorsTable getDoctorByID(int id) {
        // Belirtilen doktor ID'sine göre doktor kaydını çeker
        Session session = entityManager.unwrap(Session.class);
        return session.createNativeQuery(
            "Select * FROM HastaneRS.dbo.doctors where id = " + id, ObjectOfDoctorsTable.class).getSingleResult();
    }

    @Override
    @Transactional
    public List<String> getAllDoctors() {
        // Tüm doktor adlarını listeler
        Session session = entityManager.unwrap(Session.class);
        return session.createNativeQuery("Select name FROM HastaneRS.dbo.doctors ", String.class).getResultList();
    }

    @Override
    @Transactional
    public String getPasswordByTcNumber(int TcNumber) {
        // Belirtilen TC numarasına göre şifreyi döndürür
        Session session = entityManager.unwrap(Session.class);
        try {
            return session.createNativeQuery(
                "Select password FROM HastaneRS.dbo.doctors where tc_number = " + TcNumber, String.class).getSingleResult();
        } catch (NoResultException e) {
            return session.createNativeQuery(
                "Select password FROM HastaneRS.dbo.users where TCNumber = " + TcNumber, String.class).getSingleResult();
        }
    }

    @Override
    @Transactional
    public void addRandevu(ObjectOfRezerveTable object) {
        // Yeni bir rezervasyon ekler
        Session session = entityManager.unwrap(Session.class);
        try {
            session.persist(object);
        } catch (NoResultException e) {
            System.out.print(e);
        }
    }

    @Override
    @Transactional
    public int getIdUserByTcNumber(int TcNumber) {
        // TC numarasına göre kullanıcı ID'sini döndürür
        Session session = entityManager.unwrap(Session.class);
        try {
            return session.createNativeQuery(
                "Select id FROM HastaneRS.dbo.doctors where tc_number = " + TcNumber, Integer.class).getSingleResult();
        } catch (NoResultException e) {
            return session.createNativeQuery(
                "Select id FROM HastaneRS.dbo.users where TCNumber = " + TcNumber, Integer.class).getSingleResult();
        }
    }

    @Override
    @Transactional
    public boolean itIsDoctor(int TcNumber) {
        // TC numarasının bir doktora ait olup olmadığını kontrol eder
        Session session = entityManager.unwrap(Session.class);
        try {
            session.createNativeQuery(
                "SELECT 1 FROM doctors WHERE tc_number = " + TcNumber, Integer.class).getSingleResult();
            return true;
        } catch (NoResultException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional
    public ObjectOfMessagesTable message(int id) {
        // Belirtilen mesaj ID'sine göre mesaj kaydını çeker
        Session session = entityManager.unwrap(Session.class);
        try {
            return session.createNativeQuery(
                "Select id FROM HastaneRS.dbo.messages where id = " + id, ObjectOfMessagesTable.class).getSingleResult();
        } catch (NoResultException e) {
            System.out.print(e);
            return null;
        }
    }

    @Override
    @Transactional
    public List<ObjectOfMessagesTable> getAllUserMessages(int userId) {
        // Belirtilen kullanıcı ID'sine göre mesajları listeler
        Session session = entityManager.unwrap(Session.class);
        return session.createNativeQuery(
            "Select * FROM HastaneRS.dbo.messages where sender = " + userId, ObjectOfMessagesTable.class).getResultList();
    }

    @Override
    @Transactional
    public void sendToMail(ObjectOfMessagesTable object) {
        // Mesaj gönderir ve veritabanına ekler
        Session session = entityManager.unwrap(Session.class);
        try {
            session.persist(object);
        } catch (NoResultException e) {
            System.out.print(e);
        }
    }

    @Override
    @Transactional
    public int getDoctorIdByName(String name) {
        // Doktor adından ID'sini çeker
        Session session = entityManager.unwrap(Session.class);
        return session.createNativeQuery(
            "Select id FROM HastaneRS.dbo.doctors where name='" + name + "'", Integer.class).getSingleResult();
    }

    @Override
    @Transactional
    public void deleteRandevu(int randevuId) {
        // Belirtilen randevu ID'sine göre randevuyu siler
        Session session = entityManager.unwrap(Session.class);
        try {
            session.createNativeQuery(
                "DELETE FROM rezerves WHERE id=" + randevuId, Integer.class).executeUpdate();
        } catch (NoResultException e) {
            System.out.print(e);
        }
    }

	@Override
	public List<ObjectOfMessagesTable> getAllDoctorMessages(int doctorId) {
		// Belirtilen kullanıcı ID'sine göre mesajları listeler
        Session session = entityManager.unwrap(Session.class);
        return session.createNativeQuery(
            "Select * FROM HastaneRS.dbo.messages where receiver = " + doctorId, ObjectOfMessagesTable.class).getResultList();
	}
}

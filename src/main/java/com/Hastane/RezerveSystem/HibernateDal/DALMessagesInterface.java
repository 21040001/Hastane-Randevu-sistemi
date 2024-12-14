package com.Hastane.RezerveSystem.HibernateDal;

import java.util.List;

import com.Hastane.RezerveSystem.Entity.ObjectOfMessagesTable;

public interface DALMessagesInterface {
	ObjectOfMessagesTable message(int id);
	List<ObjectOfMessagesTable> getAllMessages(int userId);
	void sendToMail(ObjectOfMessagesTable object);
}

package com.Hastane.RezerveSystem.Entity;

import java.sql.Time;

import jakarta.persistence.*;

@Entity
@Table(name="rezerves")
public class ObjectOfRezerveTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname; 
	@Column(name = "day")
	private String day;
	@Column(name = "hour")
	private Time hour;
	@Column(name = "doctor")
	private String doctor;
	@Column(name = "rezerveType")
	private String rezerveType;
	@Column(name = "idUser")
	private int idUser;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Time getHour() {
		return hour;
	}
	public void setHour(Time hour) {
		this.hour = hour;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getRezerveType() {
		return rezerveType;
	}
	public void setRezerveType(String rezerveType) {
		this.rezerveType = rezerveType;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
}

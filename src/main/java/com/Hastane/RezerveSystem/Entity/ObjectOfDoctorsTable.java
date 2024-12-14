package com.Hastane.RezerveSystem.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="doctors")
public class ObjectOfDoctorsTable {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Id
	@Column(name = "TcNumber")
	private int TcNumber;
	@Column(name = "email")
	private String email;
	@Column(name = "birt")
	private Date birt;
	@Column(name = "gender")
	private String gender;
	@Column(name = "password")
	private String password;
	@Column(name = "adress")
	private String adress;
	@Column(name = "profesion")
	private String profesion;
	@Column(name = "experience")
	private String experience;
	@Column(name = "languages")
	private String languages;
	
	
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
	public int getTcNumber() {
		return TcNumber;
	}
	public void setTcNumber(int tcNumber) {
		TcNumber = tcNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirt() {
		return birt;
	}
	public void setBirt(Date birt) {
		this.birt = birt;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	
	
}

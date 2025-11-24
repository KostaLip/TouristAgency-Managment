package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Destinacija implements Serializable {
	
	private static final long  serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "Destinacija_SEQ_GENERATOR", sequenceName = "destinacija_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Destinacija_SEQ_GENERATOR")
	private int id;
	
	private String mesto;
	private String drzava;
	private String opis;
	
	@OneToMany(mappedBy = "destinacija", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Hotel> hotel;
	
	public Destinacija() {
		
	}
	
	public Destinacija(int id, String mesto, String drzava, String opis) {
		super();
		this.id = id;
		this.mesto = mesto;
		this.drzava = drzava;
		this.opis = opis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getOpis() {
		return opis;
	}

	public List<Hotel> getHotel() {
		return hotel;
	}

	public void setHotel(List<Hotel> hotel) {
		this.hotel = hotel;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	

}

package rva.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Aranzman implements Serializable {
	
	private static final long  serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "Aranzman_SEQ_GENERATOR", sequenceName = "aranzman_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Aranzman_SEQ_GENERATOR")
	private int id;
	
	private double ukupnaCena;
	private boolean placeno;
	private Date datumRealizacije;
	
	@ManyToOne
	@JoinColumn(name = "hotel")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "agencija")
	private TuristickaAgencija agencija;
	
	public Aranzman() {
		
	}
	
	public Aranzman(int id, double ukupnaCena, boolean placeno, Date datumRealizacije) {
		super();
		this.id = id;
		this.ukupnaCena = ukupnaCena;
		this.placeno = placeno;
		this.datumRealizacije = datumRealizacije;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public TuristickaAgencija getAgencija() {
		return agencija;
	}

	public void setAgencija(TuristickaAgencija agencija) {
		this.agencija = agencija;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public Date getDatumRealizacije() {
		return datumRealizacije;
	}

	public void setDatumRealizacije(Date datumRealizacije) {
		this.datumRealizacije = datumRealizacije;
	}
	
	
	
}

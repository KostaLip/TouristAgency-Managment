package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.TuristickaAgencija;

@Service
public interface TuristickaAgencijaService extends CrudService<TuristickaAgencija> {
	List<TuristickaAgencija> getTuristickaAgencijaByNaziv(String naziv);
}

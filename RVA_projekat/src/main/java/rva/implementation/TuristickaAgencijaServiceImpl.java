package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Aranzman;
import rva.models.TuristickaAgencija;
import rva.repository.TuristickaAgencijaRepository;
import rva.services.TuristickaAgencijaService;

@Component
public class TuristickaAgencijaServiceImpl implements TuristickaAgencijaService {
	
	@Autowired
	private TuristickaAgencijaRepository repo;

	@Override
	public List<TuristickaAgencija> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public TuristickaAgencija create(TuristickaAgencija t) {
		return repo.save(t);
	}

	@Override
	public Optional<TuristickaAgencija> update(TuristickaAgencija t, int id) {
		if(existsById(id)) {
			t.setId(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<TuristickaAgencija> getTuristickaAgencijaByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}
	
	@Override
	public Optional<TuristickaAgencija> findByID(int id) {
		return repo.findById(id);
	}

}

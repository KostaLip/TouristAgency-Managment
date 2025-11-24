package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Aranzman;
import rva.models.Hotel;
import rva.models.TuristickaAgencija;
import rva.repository.AranzmanRepository;
import rva.services.AranzmanService;

@Component
public class AranzmanServiceImpl implements AranzmanService {
	
	@Autowired
	private AranzmanRepository repo;

	@Override
	public List<Aranzman> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Aranzman create(Aranzman t) {
		return repo.save(t);
	}

	@Override
	public Optional<Aranzman> update(Aranzman t, int id) {
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
	public List<Aranzman> getAranzmanByPlaceno(boolean placeno) {
		return repo.findByPlacenoEquals(placeno);
	}

	@Override
	public List<Aranzman> findByForeignKey(Hotel hotel) {
		return repo.findByHotel(hotel);
	}

	@Override
	public List<Aranzman> findByForeignKey(TuristickaAgencija agencija) {
		return repo.findByAgencija(agencija);
	}

	@Override
	public Optional<Aranzman> findByID(int id) {
		return repo.findById(id);
	}

}

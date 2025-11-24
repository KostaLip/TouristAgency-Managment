package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Aranzman;
import rva.models.Destinacija;
import rva.repository.DestinacijaRepository;
import rva.services.DestinacijaService;

@Component
public class DestinacijaServiceImpl implements DestinacijaService {
	
	@Autowired
	private DestinacijaRepository repo;

	@Override
	public List<Destinacija> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Destinacija create(Destinacija t) {
		return repo.save(t);
	}

	@Override
	public Optional<Destinacija> update(Destinacija t, int id) {
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
	public List<Destinacija> getDestinacijaByMesto(String mesto) {
		return repo.findByMestoContainingIgnoreCase(mesto);
	}
	
	@Override
	public Optional<Destinacija> findByID(int id) {
		return repo.findById(id);
	}

}

package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Aranzman;
import rva.models.Destinacija;
import rva.models.Hotel;
import rva.repository.DestinacijaRepository;
import rva.repository.HotelRepository;
import rva.services.HotelService;

@Component
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository repo;

	@Override
	public List<Hotel> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Hotel create(Hotel t) {
		return repo.save(t);
	}

	@Override
	public Optional<Hotel> update(Hotel t, int id) {
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
	public List<Hotel> getHotelByBrojZvezdica(int brojZvezdica) {
		return repo.findByBrojZvezdicaLessThanOrderByBrojZvezdicaAsc(brojZvezdica);
	}

	@Override
	public List<Hotel> findByForeignKey(Destinacija destinacija) {
		return repo.findByDestinacija(destinacija);
	}
	
	@Override
	public Optional<Hotel> findByID(int id) {
		return repo.findById(id);
	}

}

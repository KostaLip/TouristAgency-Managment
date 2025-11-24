package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Destinacija;
import rva.models.Hotel;
import rva.services.DestinacijaService;
import rva.services.HotelService;

@CrossOrigin
@RestController
public class HotelController {

	@Autowired
	private HotelService service;
	
	@Autowired
	private DestinacijaService destinacijaService;
	
	@GetMapping("/hotel")
	public List<Hotel> getAllHotel() {
		return service.getAll();
	}
	
	@GetMapping("/hotel/{id}")
	public ResponseEntity<?> getHotelById(@PathVariable int id) {
		Optional<Hotel> hotel = service.findByID(id);
		if(hotel.isPresent()) {
			return ResponseEntity.ok(hotel.get());
		}
		return ResponseEntity.status(404).body("Resource with required ID: " + id + " does not exist");
	}
	
	@PostMapping("/hotel")
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
		if(service.existsById(hotel.getId())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		Hotel savedHotel = service.create(hotel);
		URI uri = URI.create("/hotel/id/" + savedHotel.getId());
		return ResponseEntity.created(uri).body(savedHotel);
	}
	
	@PutMapping("/hotel/id/{id}")
	public ResponseEntity<?> updateHotel(@RequestBody Hotel hotel, @PathVariable int id) {
		Optional<Hotel> updatedHotel = service.update(hotel, id);
		if(updatedHotel.isPresent()) {
			return ResponseEntity.ok(updatedHotel.get());
		}
		return ResponseEntity.status(400).body("Resource with required id: " + id + " could not be updated because it does not exists");
	}
	
	@DeleteMapping("/hotel/id/{id}")
	public ResponseEntity<?> deleteHotel(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with required id: " + id + "  could not be deleted because it does not exists");
	}
	
	@GetMapping("/hotel/broj_zvezdica/{brojZvezdica}")
	public ResponseEntity<?> getHotelByBrojZvezdica (@PathVariable int brojZvezdica) {
		List<Hotel> hotel = service.getHotelByBrojZvezdica(brojZvezdica);
		if(hotel.isEmpty()) {
			return ResponseEntity.status(404).body("Resource with less then: " + brojZvezdica + " stars does not exists");
		}
		return ResponseEntity.ok(hotel);
	}
	
	@GetMapping("/hotel/destinacija/{foreignKey}")
	public ResponseEntity<?> getHotelByDestinacija(@PathVariable int foreignKey) {
		Optional<Destinacija> destinacijaOptional = destinacijaService.findByID(foreignKey);
		if(destinacijaOptional.isPresent()) {
			List<Hotel> hoteli = service.findByForeignKey(destinacijaOptional.get());
			if(hoteli.isEmpty()) {
				return ResponseEntity.status(400).body("Resource with foreign key: " + foreignKey + " does not exists");
			} else {
				return ResponseEntity.ok(hoteli);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key: " + foreignKey);
	}
	
}

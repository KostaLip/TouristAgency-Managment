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

import rva.models.Aranzman;
import rva.models.Hotel;
import rva.models.TuristickaAgencija;
import rva.services.AranzmanService;
import rva.services.HotelService;
import rva.services.TuristickaAgencijaService;

@CrossOrigin
@RestController
public class AranzmanController {
	
	@Autowired
	private AranzmanService service;
	
	@Autowired
	private TuristickaAgencijaService turistickaAgencijaService;
	
	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/aranzman")
	public List<Aranzman> getAllAranzman() {
		return service.getAll();
	}
	
	@GetMapping("/aranzman/{id}")
	public ResponseEntity<?> getAranzmanById(@PathVariable int id) {
		Optional<Aranzman> aranzman = service.findByID(id);
		if(aranzman.isPresent()) {
			return ResponseEntity.ok(aranzman.get());
		}
		return ResponseEntity.status(404).body("Resource with required ID: " + id + " does not exist");
	}
	
	@PostMapping("/aranzman")
	public ResponseEntity<?> createAranzman(@RequestBody Aranzman aranzman) {
		if(service.existsById(aranzman.getId())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		Aranzman savedAranzman = service.create(aranzman);
		URI uri = URI.create("/aranzman/id/" + savedAranzman.getId());
		return ResponseEntity.created(uri).body(savedAranzman);
	}
	
	@PutMapping("/aranzman/id/{id}")
	public ResponseEntity<?> updateAranzman(@RequestBody Aranzman aranzman, @PathVariable int id) {
		Optional<Aranzman> updatedAranzman = service.update(aranzman, id);
		if(updatedAranzman.isPresent()) {
			return ResponseEntity.ok(updatedAranzman.get());
		}
		return ResponseEntity.status(400).body("Resource with required id: " + id + " could not be updated because it does not exists");
	}
	
	@DeleteMapping("/aranzman/id/{id}")
	public ResponseEntity<?> deleteAranzman(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with required id: " + id + "  could not be deleted because it does not exists");
	}
	
	@GetMapping("/aranzman/placeno/{placeno}")
	public ResponseEntity<?> getAranzmanByPlaceno (@PathVariable boolean placeno) {
		List<Aranzman> aranzman = service.getAranzmanByPlaceno(placeno);
		if(aranzman.isEmpty()) {
			return ResponseEntity.status(404).body("Resource with placeno: " + placeno + " does not exists");
		}
		return ResponseEntity.ok(aranzman);
	}
	
	@GetMapping("/aranzman/turisticka_agencija/{foreignKey}")
	public ResponseEntity<?> getAranzmanByTuristickaAgencija(@PathVariable int foreignKey) {
		Optional<TuristickaAgencija> turistickaAgencijaOptional = turistickaAgencijaService.findByID(foreignKey);
		if(turistickaAgencijaOptional.isPresent()) {
			List<Aranzman> aranzmani = service.findByForeignKey(turistickaAgencijaOptional.get());
			if(aranzmani.isEmpty()) {
				return ResponseEntity.status(400).body("Resource with foreign key: " + foreignKey + " does not exists");
			} else {
				return ResponseEntity.ok(aranzmani);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key: " + foreignKey);
	}
	
	@GetMapping("/aranzman/hotel/{foreignKey}")
	public ResponseEntity<?> getAranzmanByHotel(@PathVariable int foreignKey) {
		Optional<Hotel> hotelOptional = hotelService.findByID(foreignKey);
		if(hotelOptional.isPresent()) {
			List<Aranzman> aranzmani = service.findByForeignKey(hotelOptional.get());
			if(aranzmani.isEmpty()) {
				return ResponseEntity.status(400).body("Resource with foreign key: " + foreignKey + " does not exists");
			} else {
				return ResponseEntity.ok(aranzmani);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key: " + foreignKey);
	}
	
}

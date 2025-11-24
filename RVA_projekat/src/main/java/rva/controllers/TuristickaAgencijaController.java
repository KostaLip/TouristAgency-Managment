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

import rva.services.TuristickaAgencijaService;
import rva.models.TuristickaAgencija;

@CrossOrigin
@RestController
public class TuristickaAgencijaController {

	@Autowired
	private TuristickaAgencijaService service;
	
	@GetMapping("/turisticka_agencija")
	public List<TuristickaAgencija> getAllTuristickaAgencija() {
		return service.getAll();
	}
	
	@GetMapping("/turisticka_agencija/{id}")
	public ResponseEntity<?> getTuristickaAgencijaById(@PathVariable int id) {
		Optional<TuristickaAgencija> turistickaAgencija = service.findByID(id);
		if(turistickaAgencija.isPresent()) {
			return ResponseEntity.ok(turistickaAgencija.get());
		}
		return ResponseEntity.status(404).body("Resource with required ID: " + id + " does not exist");
	}
	
	@GetMapping("/turisticka_agencija/naziv/{naziv}")
	public ResponseEntity<?> getTuristickaAgencijaByNaziv (@PathVariable String naziv) {
		List<TuristickaAgencija> turistickaAgencija = service.getTuristickaAgencijaByNaziv(naziv);
		if(turistickaAgencija.isEmpty()) {
			return ResponseEntity.status(404).body("Resource with name: " + naziv + " does not exists");
		}
		return ResponseEntity.ok(turistickaAgencija);
	}
	
	@PostMapping("/turisticka_agencija")
	public ResponseEntity<?> createTuristickaAgencija(@RequestBody TuristickaAgencija turistickaAgencija) {
		if(service.existsById(turistickaAgencija.getId())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		TuristickaAgencija savedTuristickaAgencija = service .create(turistickaAgencija);
		URI uri = URI.create("/turisticka_agencija/id/" + savedTuristickaAgencija.getId());
		return ResponseEntity.created(uri).body(savedTuristickaAgencija);
	}
	
	@PutMapping("/turisticka_agencija/id/{id}")
	public ResponseEntity<?> updateTuristickaAgencija(@RequestBody TuristickaAgencija turistickaAgencija, @PathVariable int id) {
		Optional<TuristickaAgencija> updatedTuristickaAgencija = service.update(turistickaAgencija, id);
		if(updatedTuristickaAgencija.isPresent()) {
			return ResponseEntity.ok(updatedTuristickaAgencija.get());
		}
		return ResponseEntity.status(400).body("Resource with required id: " + id + " could not be updated because it does not exists");
	}
	
	@DeleteMapping("/turisticka_agencija/id/{id}")
	public ResponseEntity<?> deleteTuristickaAgencija(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with required id: " + id + "  could not be deleted because it does not exists");
	}
}

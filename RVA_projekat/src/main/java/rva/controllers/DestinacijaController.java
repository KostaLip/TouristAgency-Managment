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
import rva.services.DestinacijaService;

@CrossOrigin
@RestController
public class DestinacijaController {
	
	@Autowired
	private DestinacijaService service;
	
	@GetMapping("/destinacija")
	public List<Destinacija> getAllDestinacija() {
		return service.getAll();
	}
	
	@GetMapping("/destinacija/{id}")
	public ResponseEntity<?> getDestinacijaById(@PathVariable int id) {
		Optional<Destinacija> destinacija = service.findByID(id);
		if(destinacija.isPresent()) {
			return ResponseEntity.ok(destinacija.get());
		}
		return ResponseEntity.status(404).body("Resource with required ID: " + id + " does not exist");
	}
	
	@PostMapping("/destinacija")
	public ResponseEntity<?> createDestinacija(@RequestBody Destinacija destinacija) {
		if(service.existsById(destinacija.getId())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		Destinacija savedDestinacija = service.create(destinacija);
		URI uri = URI.create("/destinacija/id/" + savedDestinacija.getId());
		return ResponseEntity.created(uri).body(savedDestinacija);
	}
	
	@PutMapping("/destinacija/id/{id}")
	public ResponseEntity<?> updateDestinacija(@RequestBody Destinacija destinacija, @PathVariable int id) {
		Optional<Destinacija> updatedDestinacija = service.update(destinacija, id);
		if(updatedDestinacija.isPresent()) {
			return ResponseEntity.ok(updatedDestinacija.get());
		}
		return ResponseEntity.status(400).body("Resource with required id: " + id + " could not be updated because it does not exists");
	}
	
	@DeleteMapping("/destinacija/id/{id}")
	public ResponseEntity<?> deleteDestinacija(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with required id: " + id + "  could not be deleted because it does not exists");
	}
	
	@GetMapping("/destinacija/mesto/{mesto}")
	public ResponseEntity<?> getDestinacijaByMesto (@PathVariable String mesto) {
		List<Destinacija> destinacija = service.getDestinacijaByMesto(mesto);
		if(destinacija.isEmpty()) {
			return ResponseEntity.status(404).body("Resource with mesto: " + mesto + " does not exists");
		}
		return ResponseEntity.ok(destinacija);
	}

}

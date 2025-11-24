package rva.integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rva.models.Aranzman;

@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AranzmanControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Aranzman>> response = template.exchange("/aranzman", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aranzman>> () {});
		ArrayList<Aranzman> list = (ArrayList<Aranzman>) response.getBody();
		for(int i = 0; i < list.size(); i++) {
			if(highestId <= list.get(i).getId()) {
				highestId = list.get(i).getId() + 1;
			}
		}
	}
	
	void getHighestId() {
		createHighestId();
		highestId--;
	}
	
	@Test
	@Order(1)
	void testGetAllAranzman() {
		ResponseEntity<List<Aranzman>> response = template.exchange("/aranzman", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aranzman>> () {});
		
		int statusCode = response.getStatusCode().value();
		List<Aranzman> aranzmani = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!aranzmani.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetAranzmanById() {
		int id = 1;
		
		ResponseEntity<Aranzman> response = template.exchange("/aranzman/" + id, HttpMethod.GET, null,
				Aranzman.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	}
	
	@Test
	@Order(3)
	void testGetAranzmanByPlaceno() {
		boolean placeno = true;
		
		ResponseEntity<List<Aranzman>> response = template.exchange("/aranzman/placeno/" + placeno, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aranzman>>() {});
		int statusCode = response.getStatusCode().value();
		List<Aranzman> aranzmani = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(aranzmani.get(0));
		for(Aranzman aranzman : aranzmani) {
			assertTrue(aranzman.isPlaceno() == placeno);
		}
	}
	
	@Test
	@Order(4)
	void testGetAranzmanByTuristickaAgencija() {
		long turistickaAgencijaId = 1;
		ResponseEntity<List<Aranzman>> response = template.exchange("/aranzman/turisticka_agencija/" + turistickaAgencijaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aranzman>>(){});
		int statusCode = response.getStatusCode().value();
		List<Aranzman> aranzmani =  response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(aranzmani.get(0));
		for(Aranzman aranzman: aranzmani) {
			assertTrue(aranzman.getAgencija().getId() == 1);
		}
	}
	
	@Test
	@Order(5)
	void testGetAranzmanByHotel() {
		long hotelId = 1;
		ResponseEntity<List<Aranzman>> response = template.exchange("/aranzman/hotel/" + hotelId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aranzman>>(){});
		int statusCode = response.getStatusCode().value();
		List<Aranzman> aranzmani =  response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(aranzmani.get(0));
		for(Aranzman aranzman: aranzmani) {
			assertTrue(aranzman.getHotel().getId() == 1);
		}
	}
	
	@Test
	@Order(6)
	void testCreateAranzman() {
		Aranzman aranzman = new Aranzman();
		aranzman.setPlaceno(true);
		aranzman.setUkupnaCena(5000);
		
		HttpEntity<Aranzman> entity = new HttpEntity<Aranzman>(aranzman);
		createHighestId();
		ResponseEntity<Aranzman> response = template.exchange("/aranzman", HttpMethod.POST, entity, Aranzman.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/aranzman/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(aranzman.isPlaceno(), response.getBody().isPlaceno());
		assertEquals(aranzman.getUkupnaCena(), response.getBody().getUkupnaCena());
	}
	
	@Test
	@Order(7)
	void testUpdateAranzman() {
		Aranzman aranzman = new Aranzman();
		aranzman.setPlaceno(true);
		aranzman.setUkupnaCena(5000);
		
		HttpEntity<Aranzman> entity = new HttpEntity<Aranzman>(aranzman);
		getHighestId();
		ResponseEntity<Aranzman> response = template.exchange("/aranzman/id/" + highestId, HttpMethod.PUT, entity, Aranzman.class);
		int statusCode = response.getStatusCode().value();
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Aranzman);
		assertEquals(aranzman.isPlaceno(), response.getBody().isPlaceno());
		assertEquals(aranzman.getUkupnaCena(), response.getBody().getUkupnaCena());
	}
	
	@Test
	@Order(8)
	void testDeleteAranzman() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/aranzman/id/" + highestId, HttpMethod.DELETE, null, String.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("Resource with ID: " + highestId + " has been successfully deleted"));
	}

}

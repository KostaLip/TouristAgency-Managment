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

import rva.models.Destinacija;

@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DestinacijaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Destinacija>> response = template.exchange("/destinacija", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Destinacija>> () {});
		ArrayList<Destinacija> list = (ArrayList<Destinacija>) response.getBody();
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
	void testGetAllDestinacija() {
		ResponseEntity<List<Destinacija>> response = template.exchange("/destinacija", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Destinacija>> () {});
		
		int statusCode = response.getStatusCode().value();
		List<Destinacija> destinacije = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!destinacije.isEmpty());
		
	}
	
	@Test
	@Order(2)
	void testGetDestinacijaById() {
		int id = 1;
		
		ResponseEntity<Destinacija> response = template.exchange("/destinacija/" + id, HttpMethod.GET, null,
				Destinacija.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	void testGetDestinacijaByMesto() {
		String mesto = "Gacko";
		
		ResponseEntity<List<Destinacija>> response = template.exchange("/destinacija/mesto/" + mesto, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Destinacija>>() {});
		int statusCode = response.getStatusCode().value();
		List<Destinacija> destinacije = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(destinacije.get(0));
		for(Destinacija destinacija : destinacije) {
			assertEquals(destinacija.getMesto(), mesto);
		}
		
	}
	
	@Test
	@Order(4)
	void testCreateDestinacija() {
		Destinacija destinacija = new Destinacija();
		destinacija.setMesto("Himalaji");
		destinacija.setDrzava("Kina");
		
		HttpEntity<Destinacija> entity = new HttpEntity<Destinacija>(destinacija);
		createHighestId();
		ResponseEntity<Destinacija> response = template.exchange("/destinacija", HttpMethod.POST, entity, Destinacija.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/destinacija/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(destinacija.getMesto(), response.getBody().getMesto());
		assertEquals(destinacija.getDrzava(), response.getBody().getDrzava());
	}
	
	@Test
	@Order(5)
	void testUpdateDestinacija() {
		Destinacija destinacija = new Destinacija();
		destinacija.setMesto("Himalaji");
		destinacija.setDrzava("Kina");
		
		HttpEntity<Destinacija> entity = new HttpEntity<Destinacija>(destinacija);
		getHighestId();
		ResponseEntity<Destinacija> response = template.exchange("/destinacija/id/" + highestId, HttpMethod.PUT, entity, Destinacija.class);
		int statusCode = response.getStatusCode().value();
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Destinacija);
		assertEquals(destinacija.getMesto(), response.getBody().getMesto());
		assertEquals(destinacija.getDrzava(), response.getBody().getDrzava());
	}
	
	@Test
	@Order(6)
	void testDeleteDestinacija() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/destinacija/id/" + highestId, HttpMethod.DELETE, null, String.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("Resource with ID: " + highestId + " has been successfully deleted"));
	}

}

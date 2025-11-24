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

import rva.models.TuristickaAgencija;

@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TuristickaAgencijaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<TuristickaAgencija>> response = template.exchange("/turisticka_agencija", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TuristickaAgencija>> () {});
		ArrayList<TuristickaAgencija> list = (ArrayList<TuristickaAgencija>) response.getBody();
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
	void testGetAllTuristickaAgencija() {
		ResponseEntity<List<TuristickaAgencija>> response = template.exchange("/turisticka_agencija", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TuristickaAgencija>> () {});
		
		int statusCode = response.getStatusCode().value();
		List<TuristickaAgencija> turistickeAgencije = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!turistickeAgencije.isEmpty());
		
	}
	
	@Test
	@Order(2)
	void testGetTuristickaAgencijaById() {
		int id = 1;
		
		ResponseEntity<TuristickaAgencija> response = template.exchange("/turisticka_agencija/" + id, HttpMethod.GET, null,
				TuristickaAgencija.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	void testGetTuristickaAgencijaByNaziv() {
		String naziv = "Mars Turisticka Agencija";
		
		ResponseEntity<List<TuristickaAgencija>> response = template.exchange("/turisticka_agencija/naziv/" + naziv, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TuristickaAgencija>>() {});
		int statusCode = response.getStatusCode().value();
		List<TuristickaAgencija> turistickeAgencije = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(turistickeAgencije.get(0));
		for(TuristickaAgencija turistickaAgencija : turistickeAgencije) {
			assertEquals(turistickaAgencija.getNaziv(), naziv);
		}
		
	}
	
	@Test
	@Order(4)
	void testCreateTuristickaAgencija() {
		TuristickaAgencija turistickaAgencija = new TuristickaAgencija();
		turistickaAgencija.setNaziv("Naziv Saturn");
		turistickaAgencija.setAdresa("Doza Djerdja 16");
		
		HttpEntity<TuristickaAgencija> entity = new HttpEntity<TuristickaAgencija>(turistickaAgencija);
		createHighestId();
		ResponseEntity<TuristickaAgencija> response = template.exchange("/turisticka_agencija", HttpMethod.POST, entity, TuristickaAgencija.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/turisticka_agencija/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(turistickaAgencija.getNaziv(), response.getBody().getNaziv());
		assertEquals(turistickaAgencija.getAdresa(), response.getBody().getAdresa());
	}
	
	@Test
	@Order(5)
	void testUpdateTuristickaAgencija() {
		TuristickaAgencija turistickaAgencija = new TuristickaAgencija();
		turistickaAgencija.setNaziv("Naziv Saturn");
		turistickaAgencija.setAdresa("Doza Djerdja 16");
		
		HttpEntity<TuristickaAgencija> entity = new HttpEntity<TuristickaAgencija>(turistickaAgencija);
		getHighestId();
		ResponseEntity<TuristickaAgencija> response = template.exchange("/turisticka_agencija/id/" + highestId, HttpMethod.PUT, entity, TuristickaAgencija.class);
		int statusCode = response.getStatusCode().value();
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof TuristickaAgencija);
		assertEquals(turistickaAgencija.getNaziv(), response.getBody().getNaziv());
		assertEquals(turistickaAgencija.getAdresa(), response.getBody().getAdresa());
	}
	
	@Test
	@Order(6)
	void testDeleteTuristickaAgencija() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/turisticka_agencija/id/" + highestId, HttpMethod.DELETE, null, String.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("Resource with ID: " + highestId + " has been successfully deleted"));
	}

}

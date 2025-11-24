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

import rva.models.Hotel;

@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HotelControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Hotel>> response = template.exchange("/hotel", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Hotel>> () {});
		ArrayList<Hotel> list = (ArrayList<Hotel>) response.getBody();
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
	void testGetAllHotel() {
		ResponseEntity<List<Hotel>> response = template.exchange("/hotel", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Hotel>> () {});
		
		int statusCode = response.getStatusCode().value();
		List<Hotel> hoteli = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!hoteli.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetHotelById() {
		int id = 1;
		
		ResponseEntity<Hotel> response = template.exchange("/hotel/" + id, HttpMethod.GET, null,
				Hotel.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	}
	
	@Test
	@Order(3)
	void testGetHotelByBrojZvezdica() {
		int brojZvezdica = 5;
		
		ResponseEntity<List<Hotel>> response = template.exchange("/hotel/broj_zvezdica/" + brojZvezdica, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Hotel>>() {});
		int statusCode = response.getStatusCode().value();
		List<Hotel> hoteli = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(hoteli.get(0));
		for(Hotel hotel : hoteli) {
			assertTrue(hotel.getBrojZvezdica() < brojZvezdica);
		}
	}
	
	@Test
	@Order(4)
	void testGetHotelByDestinacija() {
		long destinacijaId = 1;
		ResponseEntity<List<Hotel>> response = template.exchange("/hotel/destinacija/" + destinacijaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Hotel>>(){});
		int statusCode = response.getStatusCode().value();
		List<Hotel> hoteli =  response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(hoteli.get(0));
		for(Hotel hotel: hoteli) {
			assertTrue(hotel.getDestinacija().getId() == 1);
		}
	}
	
	@Test
	@Order(5)
	void testCreateHotel() {
		Hotel hotel = new Hotel();
		hotel.setBrojZvezdica(6);
		hotel.setOpis("Najbolji hotel");
		hotel.setNaziv("Hotel Merkur");
		
		HttpEntity<Hotel> entity = new HttpEntity<Hotel>(hotel);
		createHighestId();
		ResponseEntity<Hotel> response = template.exchange("/hotel", HttpMethod.POST, entity, Hotel.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/hotel/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(hotel.getBrojZvezdica(), response.getBody().getBrojZvezdica());
		assertEquals(hotel.getNaziv(), response.getBody().getNaziv());
		assertEquals(hotel.getOpis(), response.getBody().getOpis());
	}
	
	@Test
	@Order(6)
	void testUpdateHotel() {
		Hotel hotel = new Hotel();
		hotel.setBrojZvezdica(6);
		hotel.setOpis("Najbolji hotel");
		hotel.setNaziv("Merkur hotel");
		
		HttpEntity<Hotel> entity = new HttpEntity<Hotel>(hotel);
		getHighestId();
		ResponseEntity<Hotel> response = template.exchange("/hotel/id/" + highestId, HttpMethod.PUT, entity, Hotel.class);
		int statusCode = response.getStatusCode().value();
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Hotel);
		assertEquals(hotel.getNaziv(), response.getBody().getNaziv());
		assertEquals(hotel.getOpis(), response.getBody().getOpis());
		assertEquals(hotel.getBrojZvezdica(), response.getBody().getBrojZvezdica());
	}
	
	@Test
	@Order(7)
	void testDeleteHotel() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/hotel/id/" + highestId, HttpMethod.DELETE, null, String.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("Resource with ID: " + highestId + " has been successfully deleted"));
	}

}

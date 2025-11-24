package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.Destinacija;
import rva.models.Hotel;

@Service
public interface HotelService extends CrudService<Hotel> {
	List<Hotel> getHotelByBrojZvezdica(int brojZvezdica);
	List<Hotel> findByForeignKey(Destinacija destinacija);
}

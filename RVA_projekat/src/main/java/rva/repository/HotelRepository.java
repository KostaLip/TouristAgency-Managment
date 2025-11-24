package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Destinacija;
import rva.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	List<Hotel> findByBrojZvezdicaLessThanOrderByBrojZvezdicaAsc(int brojZvezdica);
	List<Hotel> findByDestinacija(Destinacija destinacija);
}

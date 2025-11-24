package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Aranzman;
import rva.models.Hotel;
import rva.models.TuristickaAgencija;

@Repository
public interface AranzmanRepository extends JpaRepository<Aranzman, Integer> {
	List<Aranzman> findByPlacenoEquals(boolean placeno);
	List<Aranzman> findByHotel(Hotel hotel);
	List<Aranzman> findByAgencija(TuristickaAgencija agencija);
}

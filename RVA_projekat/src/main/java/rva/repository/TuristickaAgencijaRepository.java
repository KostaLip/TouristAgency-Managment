package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.TuristickaAgencija;

@Repository
public interface TuristickaAgencijaRepository extends JpaRepository<TuristickaAgencija, Integer> {
	List<TuristickaAgencija> findByNazivContainingIgnoreCase(String naziv);
}

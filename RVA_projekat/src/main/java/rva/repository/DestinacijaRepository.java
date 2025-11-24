package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Destinacija;

@Repository
public interface DestinacijaRepository extends JpaRepository<Destinacija, Integer> {
	List<Destinacija> findByMestoContainingIgnoreCase(String mesto);
}

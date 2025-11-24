package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.Destinacija;

@Service
public interface DestinacijaService extends CrudService<Destinacija> {
	List<Destinacija> getDestinacijaByMesto(String mesto);
}

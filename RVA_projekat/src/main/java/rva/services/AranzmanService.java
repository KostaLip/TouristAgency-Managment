package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.Aranzman;
import rva.models.Hotel;
import rva.models.TuristickaAgencija;

@Service
public interface AranzmanService extends CrudService<Aranzman> {
	List<Aranzman> getAranzmanByPlaceno(boolean placeno);
	List<Aranzman> findByForeignKey(Hotel hotel);
	List<Aranzman> findByForeignKey(TuristickaAgencija agencija);
}

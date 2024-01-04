package demo.laptopstore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.laptopstore.dto.LaptopDTO;
import demo.laptopstore.exception.ResourceNotFoundException;

@Service
public interface LaptopService {
	List<LaptopDTO> findAll();

	List<LaptopDTO> findAllByName(String name);

	List<LaptopDTO> findAllByBrand(String brand);

	List<LaptopDTO> findAllByPrice(Double price);

	ResponseEntity<LaptopDTO> findLaptopById(Long id) throws ResourceNotFoundException;

	ResponseEntity<LaptopDTO> addLaptop(LaptopDTO newLaptop);

	ResponseEntity<String> deleteLaptopById(Long id) throws ResourceNotFoundException;

	ResponseEntity<LaptopDTO> updateLaptopById(Long id, LaptopDTO laptopDTO) throws ResourceNotFoundException;
}

package demo.laptopstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.laptopstore.dto.LaptopDTO;
import demo.laptopstore.entity.Laptop;
import demo.laptopstore.exception.ResourceNotFoundException;
import demo.laptopstore.repository.LaptopRepository;
import demo.laptopstore.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService{

	private final LaptopRepository laptopRepository;
	private final ModelMapper modelMapper;
	
	
	@Autowired
	public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper) {
		super();
		this.laptopRepository = laptopRepository;
		this.modelMapper = modelMapper;
	}

	private LaptopDTO extractedLaptopDTO(Laptop laptop) {
		return modelMapper.map(laptop, LaptopDTO.class);
	}

	private Laptop extractedLaptop(LaptopDTO laptopDTO) {
		return modelMapper.map(laptopDTO, Laptop.class);
	}

	@Override
	public List<LaptopDTO> findAll() {
		List<Laptop> laptops = laptopRepository.findAll();
		return laptops.stream().map(laptop -> extractedLaptopDTO(laptop)).collect(Collectors.toList());
	}

	@Override
	public List<LaptopDTO> findAllByName(String name) {
		List<Laptop> laptops = laptopRepository.findAllByName(name);
		return laptops.stream().map(laptop -> extractedLaptopDTO(laptop)).collect(Collectors.toList());
	}

	@Override
	public List<LaptopDTO> findAllByBrand(String brand) {
		List<Laptop> laptops = laptopRepository.findAllByBrand(brand);
		return laptops.stream().map(laptop -> extractedLaptopDTO(laptop)).collect(Collectors.toList());
	}

	@Override
	public List<LaptopDTO> findAllByPrice(Double price) {
		List<Laptop> laptops = laptopRepository.findAllByPrice(price);
		return laptops.stream().map(laptop -> extractedLaptopDTO(laptop)).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<LaptopDTO> findLaptopById(Long id)
			throws ResourceNotFoundException {
		Optional<Laptop> laptop = Optional
				.of(laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laptop Not found")));

		return ResponseEntity.ok(extractedLaptopDTO(laptop.get()));
	}

	@Override
	public ResponseEntity<LaptopDTO> addLaptop(LaptopDTO laptopDTO) {
		Laptop laptop = extractedLaptop(laptopDTO);
		Laptop savedLaptop = laptopRepository.save(laptop);
		return ResponseEntity.ok(extractedLaptopDTO(savedLaptop));
	}

	@Override
	public ResponseEntity<String> deleteLaptopById(Long id) throws ResourceNotFoundException {
		Optional<Laptop> laptop = Optional.ofNullable(
				laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laptop Not found")));

		laptopRepository.deleteById(id);
		return ResponseEntity.ok("Deleted Successfull!");
	}

	@Override
	public ResponseEntity<LaptopDTO> updateLaptopById(Long id, LaptopDTO laptopDTO) throws ResourceNotFoundException {
		Optional<Laptop> laptop = laptopRepository.findById(id);
		if (laptop == null)
			throw new ResourceNotFoundException("Laptop with this Id does not exist");

		Laptop existingLaptop = laptop.get();
		existingLaptop.setName(laptopDTO.getName());
		existingLaptop.setBrand(laptopDTO.getBrand());
		existingLaptop.setPrice(laptopDTO.getPrice());
		existingLaptop.setRam(laptopDTO.getRam());
		existingLaptop.setProcessor(laptopDTO.getProcessor());
		existingLaptop.setStorage(laptopDTO.getStorage());

		Laptop savedLaptop = laptopRepository.save(existingLaptop);
		return ResponseEntity.ok(extractedLaptopDTO(existingLaptop));
	}
}

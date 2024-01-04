package demo.laptopstore.controller;

import java.lang.annotation.Repeatable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.laptopstore.dto.LaptopDTO;
import demo.laptopstore.exception.ResourceNotFoundException;
import demo.laptopstore.service.LaptopService;

@CrossOrigin("*")
@RestController
@RequestMapping("laptops")
public class LaptopController {

	private final LaptopService laptopService;

	@Autowired
	public LaptopController(LaptopService laptopService) {
		this.laptopService = laptopService;
	}

	@GetMapping
	public List<LaptopDTO> findAll() {
		return laptopService.findAll();
	}

	@PostMapping
	public ResponseEntity<LaptopDTO> createLaptop(@RequestBody LaptopDTO laptopDTO) {
		return laptopService.addLaptop(laptopDTO);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteLaptopById(@PathVariable Long id) {
		try {
			laptopService.deleteLaptopById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<LaptopDTO> findById(@PathVariable Long id) {
		try {
			return laptopService.findLaptopById(id);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<LaptopDTO> updateLaptop(@PathVariable Long id, @RequestBody LaptopDTO laptopDTO) {
		try {
			return laptopService.updateLaptopById(id, laptopDTO);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("search")
	public ResponseEntity<List<LaptopDTO>> findByParameter(@RequestParam(required = false) String name,
			@RequestParam(required = false) Double price, @RequestParam(required = false) String brand) {

		if(name != null && price != null && brand != null) {
			return ResponseEntity.ok(laptopService.findAllByPriceNameBrand(name, price, brand));
		} else if(name != null && price != null) {
			return ResponseEntity.ok(laptopService.findAllByPriceName(name, price));
		} else if(name != null && brand != null) {
			return ResponseEntity.ok(laptopService.findAllByNameBrand(name, brand));
		} else if(price != null && brand != null) {
			return ResponseEntity.ok(laptopService.findAllByPriceBrand(price, brand));
		} else if (name != null) {
			// If only name is provided
			return ResponseEntity.ok(laptopService.findAllByName(name));
		} else if (price != null) {
			// If only price is provided
			return ResponseEntity.ok(laptopService.findAllByPrice(price));
		} else if (brand != null) {
			// If only brand is provided
			return ResponseEntity.ok(laptopService.findAllByBrand(brand));
		} else {
			// If no parameters are provided
			return ResponseEntity.ok(laptopService.findAll());
		}
	}

}

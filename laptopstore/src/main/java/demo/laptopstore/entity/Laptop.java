package demo.laptopstore.entity;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Transactional
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private String brand;

	@Column(nullable = false)
	private Integer storage;

	@Column(nullable = false)
	private Integer ram;

	@Column(nullable = false)
	private String processor;


	Laptop() {

	}

	public Laptop(String name, Double price, String brand, Integer storage, Integer ram, String processor) {
		super();
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.storage = storage;
		this.ram = ram;
		this.processor = processor;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Integer getStorage() {
		return storage;
	}


	public void setStorage(Integer storage) {
		this.storage = storage;
	}


	public Integer getRam() {
		return ram;
	}


	public void setRam(Integer ram) {
		this.ram = ram;
	}


	public String getProcessor() {
		return processor;
	}


	public void setProcessor(String processor) {
		this.processor = processor;
	}


	@Override
	public String toString() {
		return "Laptop [id=" + id + ", name=" + name + ", price=" + price + ", brand=" + brand + ", storage=" + storage
				+ ", ram=" + ram + ", processor=" + processor + "]";
	}


}

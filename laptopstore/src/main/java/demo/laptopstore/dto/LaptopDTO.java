package demo.laptopstore.dto;

public class LaptopDTO {
	private Long id;
	private String name;
	private Double price;
	private String brand;
	private Integer storage;
	private Integer ram;
	private String processor;

	public LaptopDTO() {

	}

	public LaptopDTO(String name, Double price, String brand, Integer storage, Integer ram, String processor) {
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
		return "LaptopDTO [id=" + id + ", name=" + name + ", price=" + price + ", brand=" + brand + ", storage="
				+ storage + ", ram=" + ram + ", processor=" + processor + "]";
	}

}

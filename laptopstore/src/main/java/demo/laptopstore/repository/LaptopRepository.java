package demo.laptopstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.laptopstore.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

	public List<Laptop> findAllByPrice(Double price);

	public List<Laptop> findAllByName(String name);

	public List<Laptop> findAllByBrand(String brand);


	/*
	 * Correct way to write Query
	 */
	@Query("select l from Laptop l where l.ram = :ram")
	List<Laptop> hello(@Param("ram") Integer ram);
}

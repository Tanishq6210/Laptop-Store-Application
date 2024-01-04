package demo.laptopstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.laptopstore.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

	@Query("SELECT l FROM Laptop l WHERE l.name = :name AND l.price = :price AND l.brand = :brand")
	public List<Laptop> findAllByPriceNameBrand(String name, Double price, String brand) ;

	@Query("SELECT l FROM Laptop l WHERE l.name = :name AND l.price = :price")
	public List<Laptop> findAllByPriceName(String name, Double price) ;

	@Query("SELECT l FROM Laptop l WHERE l.name = :name AND l.brand = :brand")
	public List<Laptop> findAllByNameBrand(String name, String brand) ;

	@Query("SELECT l FROM Laptop l WHERE l.price = :price AND l.brand = :brand")
	public List<Laptop> findAllByPriceBrand(Double price, String brand) ;

	public List<Laptop> findAllByPrice(Double price);

	public List<Laptop> findAllByName(String name);

	public List<Laptop> findAllByBrand(String brand);


	/*
	 * Correct way to write Query
	 */
	@Query("select l from Laptop l where l.ram = :ram")
	List<Laptop> hello(@Param("ram") Integer ram);
}

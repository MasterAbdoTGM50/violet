package jonamatoka.violet.data.repo;

import jonamatoka.violet.data.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> { }

package tr.com.turkcell.poc.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

import tr.com.turkcell.poc.domain.Product;

public interface ProductRepository extends MongoRepository<Product, UUID> {

    Optional<Product> findByPhoneNumber(String phoneNumber);

}

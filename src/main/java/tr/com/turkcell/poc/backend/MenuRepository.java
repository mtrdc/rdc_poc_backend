package tr.com.turkcell.poc.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

import tr.com.turkcell.poc.domain.Menu;

public interface MenuRepository extends MongoRepository<Menu, UUID> {

}

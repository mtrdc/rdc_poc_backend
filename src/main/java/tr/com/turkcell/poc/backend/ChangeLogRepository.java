package tr.com.turkcell.poc.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import tr.com.turkcell.poc.domain.ChangeLog;

public interface ChangeLogRepository extends MongoRepository<ChangeLog, Long> {

}

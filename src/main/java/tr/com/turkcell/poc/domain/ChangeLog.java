package tr.com.turkcell.poc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "changeLogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeLog {

    @Id
    private Long id;

    private String name;

    @CreatedDate
    private Instant createdAt;

}

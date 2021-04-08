package tr.com.turkcell.poc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    private UUID id;

    private String name;

    private int screenOrder;

}

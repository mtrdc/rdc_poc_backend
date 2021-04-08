package tr.com.turkcell.poc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private UUID id;

    @Indexed(unique = true)
    private String phoneNumber;

    private String userName;

    private LineType lineType;

    private LineStatus lineStatus;

    private PaymentType paymentType;

    private String shortNumber;

}

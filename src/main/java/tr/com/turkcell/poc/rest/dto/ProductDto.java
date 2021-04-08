package tr.com.turkcell.poc.rest.dto;

import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import tr.com.turkcell.poc.validation.ConstraintValues;
import tr.com.turkcell.poc.validation.constraints.PhoneNumber;
import tr.com.turkcell.poc.validation.constraints.ShortNumber;
import tr.com.turkcell.poc.validation.groups.Existing;
import tr.com.turkcell.poc.validation.groups.New;

@Data
public class ProductDto {

    @Null(groups = New.class)
    @NotNull(groups = Existing.class)
    private UUID id;

    @NotBlank
    @PhoneNumber
    private String phoneNumber;

    @NotBlank
    @Size(max = ConstraintValues.Product.USER_NAME_SIZE)
    private String userName;

    @NotNull
    private LineTypeDto lineType;

    @NotNull
    private LineStatusDto lineStatus;

    @NotNull
    private PaymentTypeDto paymentType;

    @NotBlank
    @ShortNumber
    private String shortNumber;

}

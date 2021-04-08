package tr.com.turkcell.poc.rest.dto;

import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import tr.com.turkcell.poc.validation.ConstraintValues;
import tr.com.turkcell.poc.validation.groups.Existing;
import tr.com.turkcell.poc.validation.groups.New;

@Data
public class MenuDto {

    @Null(groups = New.class)
    @NotNull(groups = Existing.class)
    private UUID id;

    @NotBlank
    @Size(max = ConstraintValues.Menu.NAME_SIZE)
    private String name;

    @NotNull
    private int screenOrder;

}

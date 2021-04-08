package tr.com.turkcell.poc.rest.mapper;

import org.mapstruct.Mapper;

import tr.com.turkcell.poc.domain.Menu;
import tr.com.turkcell.poc.rest.dto.MenuDto;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuDto toDto(Menu entity);

}

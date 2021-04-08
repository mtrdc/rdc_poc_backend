package tr.com.turkcell.poc.rest.mapper;

import org.mapstruct.Mapper;

import tr.com.turkcell.poc.domain.Product;
import tr.com.turkcell.poc.rest.dto.ProductDto;

@Mapper(componentModel = "spring")
public
interface ProductMapper {

    ProductDto toDto(Product entity);

}

package tr.com.turkcell.poc.rest;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tr.com.turkcell.poc.domain.Product;
import tr.com.turkcell.poc.rest.dto.ProductDto;
import tr.com.turkcell.poc.rest.mapper.ProductMapper;
import tr.com.turkcell.poc.service.ProductService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    private final ProductService productService = mock(ProductService.class);

    private final ProductMapper productMapper = mock(ProductMapper.class);

    private final ProductController productController = new ProductController(productService, productMapper);

    @Test
    public void getProductListShouldReturnProperResponse() {
        Pageable pageable = Pageable.unpaged();
        Product entity = new Product();
        Page<Product> entityPage = new PageImpl<>(Collections.singletonList(entity));
        ProductDto dto = new ProductDto();
        Page<ProductDto> expected = new PageImpl<>(Collections.singletonList(dto));

        when(productService.getProducts(pageable)).thenReturn(entityPage);
        when(productMapper.toDto(entity)).thenReturn(dto);

        ResponseEntity<Page<ProductDto>> response = productController.getProductList(pageable);
        assertThat("Actual should be equal to expected.", response.getBody(), equalTo(expected));
        verify(productService).getProducts(pageable);
        verify(productMapper).toDto(entity);
    }

    @Test
    public void updateProductInfoShouldReturnProperResponse() {
        List<String> phoneNumbers = Arrays.asList("123", "456");

        ResponseEntity<Void> response = productController.updateProductInfo(phoneNumbers);
        assertThat("Response should have no content.", response, equalTo(ResponseEntity.noContent().build()));
        verify(productService).updateProductInfo(phoneNumbers);
    }

}

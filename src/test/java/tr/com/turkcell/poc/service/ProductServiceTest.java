package tr.com.turkcell.poc.service;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import tr.com.turkcell.poc.backend.ProductRepository;
import tr.com.turkcell.poc.domain.Product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final ProductService productService = new ProductService(productRepository);

    @Test
    public void getProductsShouldReturnPageOfProduct() {
        Product product = createProduct("123", "456");
        Page<Product> expected = new PageImpl<>(Collections.singletonList(product));
        Pageable pageable = Pageable.unpaged();

        when(productRepository.findAll(pageable)).thenReturn(expected);

        Page<Product> actual = productService.getProducts(pageable);
        assertThat("Actual should be equal to expected.", actual, Matchers.equalTo(expected));
        verify(productRepository).findAll(pageable);
    }

    @Test
    public void updateProductInfoShouldUpdateShortNumbers() throws InterruptedException {
        String phoneNumber1 = "5321234567";
        String phoneNumber2 = "5321230000";
        String phoneNumber3 = "5321234568";
        String shortNumber1 = "1234";
        String shortNumber2 = "5678";
        List<String> phoneNumbers = Arrays.asList(phoneNumber1, phoneNumber3);
        Product product1 = createProduct(phoneNumber1, shortNumber1);
        Product product2 = createProduct(phoneNumber2, shortNumber2);

        when(productRepository.findByPhoneNumber(phoneNumber1)).thenReturn(Optional.of(product1));
        when(productRepository.findByPhoneNumber(phoneNumber2)).thenReturn(Optional.of(product2));

        productService.updateProductInfo(phoneNumbers);
        Thread.sleep(500L);
        assertThat("Short number 1 should be changed.", product1.getShortNumber(), not(equalTo(shortNumber1)));
        assertThat("Short number 2 should be the same.", product2.getShortNumber(), equalTo(shortNumber2));
        verify(productRepository).findByPhoneNumber(phoneNumber1);
        verify(productRepository).findByPhoneNumber(phoneNumber3);
        verify(productRepository, never()).findByPhoneNumber(phoneNumber2);
        verify(productRepository).save(product1);
        verify(productRepository, never()).save(product2);
    }

    private Product createProduct(String phoneNumber, String shortNumber) {
        return Product.builder()
            .id(UUID.randomUUID())
            .phoneNumber(phoneNumber)
            .shortNumber(shortNumber)
            .build();
    }

}

package tr.com.turkcell.poc.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import tr.com.turkcell.poc.rest.dto.ProductDto;
import tr.com.turkcell.poc.rest.mapper.ProductMapper;
import tr.com.turkcell.poc.service.ProductService;

@RestController
@RequiredArgsConstructor
@Slf4j
class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @RequestMapping(value = Endpoints.GET_PRODUCT_LIST, method = RequestMethod.GET)
    ResponseEntity<Page<ProductDto>> getProductList(Pageable pageable) {
        log.info("Get product list.");
        Page<ProductDto> products = productService.getProducts(pageable)
            .map(productMapper::toDto);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = Endpoints.UPDATE_PRODUCT_INFO, method = RequestMethod.PUT)
    ResponseEntity<Void> updateProductInfo(@RequestBody List<String> phoneNumbers) {
        log.info("Update product info of {} phone numbers.", phoneNumbers.size());
        productService.updateProductInfo(phoneNumbers);
        return ResponseEntity.noContent().build();
    }

}

package tr.com.turkcell.poc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tr.com.turkcell.poc.backend.ProductRepository;
import tr.com.turkcell.poc.domain.Product;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void updateProductInfo(List<String> phoneNumbers) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (String phoneNumber : phoneNumbers) {
            executorService.submit(() -> {
                productRepository.findByPhoneNumber(phoneNumber)
                    .ifPresent(existingProduct -> {
                        System.out.println("Existing product: " + existingProduct);
                        existingProduct.setShortNumber(String.valueOf(new SecureRandom().nextInt(8999) + 1000));
                        productRepository.save(existingProduct);
                    });
            });
        }
        executorService.shutdown();
    }

}

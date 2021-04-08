package tr.com.turkcell.poc.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import javax.annotation.PostConstruct;

import tr.com.turkcell.poc.domain.*;

@Component
@RequiredArgsConstructor
public class MongoChangeLogRunner implements CommandLineRunner {

    private final ChangeLogRepository changeLogRepository;

    private final MenuRepository menuRepository;

    private final ProductRepository productRepository;

    private final Map<Long, Function<Long, ChangeLog>> changeLogRunners = new HashMap<>();

    @PostConstruct
    public void initRunners() {
        changeLogRunners.put(1L, (id) -> this.initMenus(1L));
        changeLogRunners.put(2L, (id) -> this.initProducts(2L));
    }

    @Override
    public void run(String... args) {
        changeLogRunners.keySet().stream()
            .filter(id -> !changeLogRepository.existsById(id))
            .forEach(id -> {
                ChangeLog changeLog = changeLogRunners.get(id).apply(id);
                changeLogRepository.insert(changeLog);
            });
    }

    private ChangeLog initMenus(Long id) {
        int screenOrder = 0;
        menuRepository.insert(createMenu("Fatura İşlemleri", screenOrder++));
        menuRepository.insert(createMenu("Tarife İşlemleri", screenOrder++));
        menuRepository.insert(createMenu("Paket İşlemleri", screenOrder++));
        menuRepository.insert(createMenu("Hat İşlemleri", screenOrder++));
        menuRepository.insert(createMenu("Ayarlar", screenOrder++));
        menuRepository.insert(createMenu("Ürün ve Servisler", screenOrder++));
        menuRepository.insert(createMenu("Faturasız Hat İşlemleri", screenOrder++));
        menuRepository.insert(createMenu("Yetkili ve Kullanıcı İşlemleri", screenOrder++));
        menuRepository.insert(createMenu("Raporlama Merkezi", screenOrder++));
        menuRepository.insert(createMenu("İştecep Kampanyası", screenOrder++));
        return new ChangeLog(id, "initMenus", Instant.now());
    }

    private Menu createMenu(String name, int screenOrder) {
        return new Menu(UUID.randomUUID(), name, screenOrder);
    }

    private ChangeLog initProducts(Long id) {
        SecureRandom secureRandom = new SecureRandom();
        List<String> userNames = Arrays.asList("John Doe", "Jane Fonda", "Max Mustermann", "Melanie Musterfrau");
        for (int i = 0; i < 1000; i++) {
            Product product = Product.builder()
                .id(UUID.randomUUID())
                .phoneNumber("532" + (secureRandom.nextInt(8999999) + 1000000))
                .userName(userNames.get(i % userNames.size()))
                .lineType(LineType.values()[i % LineType.values().length])
                .lineStatus(LineStatus.values()[i % LineStatus.values().length])
                .paymentType(PaymentType.values()[i % PaymentType.values().length])
                .shortNumber(String.valueOf(secureRandom.nextInt(8999) + 1000))
                .build();
            productRepository.insert(product);
        }
        return new ChangeLog(id, "initProducts", Instant.now());
    }

}

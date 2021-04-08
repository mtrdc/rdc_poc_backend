package tr.com.turkcell.poc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tr.com.turkcell.poc.backend.MenuRepository;
import tr.com.turkcell.poc.domain.Menu;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Page<Menu> getMenus(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

}

package tr.com.turkcell.poc.service;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.UUID;

import tr.com.turkcell.poc.backend.MenuRepository;
import tr.com.turkcell.poc.domain.Menu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class MenuServiceTest {

    private final MenuRepository menuRepository = mock(MenuRepository.class);

    private final MenuService menuService = new MenuService(menuRepository);

    @Test
    public void getMenusShouldReturnPageOfMenu() {
        Page<Menu> expected = new PageImpl<>(Collections.singletonList(new Menu(UUID.randomUUID(), "Menu", 0)));
        Pageable pageable = Pageable.unpaged();

        when(menuRepository.findAll(pageable)).thenReturn(expected);

        Page<Menu> actual = menuService.getMenus(pageable);
        assertThat("Actual should be equal to expected.", actual, Matchers.equalTo(expected));
        verify(menuRepository).findAll(pageable);
    }

}

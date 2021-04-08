package tr.com.turkcell.poc.rest;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import tr.com.turkcell.poc.domain.Menu;
import tr.com.turkcell.poc.rest.dto.MenuDto;
import tr.com.turkcell.poc.rest.mapper.MenuMapper;
import tr.com.turkcell.poc.service.MenuService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class MenuControllerTest {

    private final MenuService menuService = mock(MenuService.class);

    private final MenuMapper menuMapper = mock(MenuMapper.class);

    private final MenuController menuController = new MenuController(menuService, menuMapper);

    @Test
    public void getMenuListShouldReturnProperResponse() {
        Pageable pageable = Pageable.unpaged();
        Menu entity = new Menu();
        Page<Menu> entityPage = new PageImpl<>(Collections.singletonList(entity));
        MenuDto dto = new MenuDto();
        Page<MenuDto> expected = new PageImpl<>(Collections.singletonList(dto));

        when(menuService.getMenus(pageable)).thenReturn(entityPage);
        when(menuMapper.toDto(entity)).thenReturn(dto);

        ResponseEntity<Page<MenuDto>> response = menuController.getMenuList(pageable);
        assertThat("Actual should be equal to expected.", response.getBody(), equalTo(expected));
        verify(menuService).getMenus(pageable);
        verify(menuMapper).toDto(entity);
    }

}

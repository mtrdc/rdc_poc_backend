package tr.com.turkcell.poc.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tr.com.turkcell.poc.rest.dto.MenuDto;
import tr.com.turkcell.poc.rest.mapper.MenuMapper;
import tr.com.turkcell.poc.service.MenuService;

@RestController
@RequiredArgsConstructor
@Slf4j
class MenuController {

    private final MenuService menuService;

    private final MenuMapper menuMapper;

    @RequestMapping(value = Endpoints.GET_MENU_LIST, method = RequestMethod.GET)
    ResponseEntity<Page<MenuDto>> getMenuList(@SortDefault(sort = "screenOrder") Pageable pageable) {
        log.info("Get menu list.");
        Page<MenuDto> menus = menuService.getMenus(pageable)
            .map(menuMapper::toDto);
        return ResponseEntity.ok(menus);
    }

}

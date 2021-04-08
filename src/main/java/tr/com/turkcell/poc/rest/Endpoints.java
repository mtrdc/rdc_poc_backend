package tr.com.turkcell.poc.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Endpoints {

    static final String BASE_URL = "/api/v1";

    static final String GET_MENU_LIST = BASE_URL + "/getMenuList";

    static final String GET_PRODUCT_LIST = BASE_URL + "/getProductList";

    static final String UPDATE_PRODUCT_INFO = BASE_URL + "/updateProductInfo";

}

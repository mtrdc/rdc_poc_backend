package tr.com.turkcell.poc.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstraintValues {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Menu {

        public static final int NAME_SIZE = 128;

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Product {

        public static final int USER_NAME_SIZE = 64;

    }

}

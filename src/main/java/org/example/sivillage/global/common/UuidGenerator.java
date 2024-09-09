package org.example.sivillage.global.common;

import java.util.UUID;

public class UuidGenerator {
    public static String createProductCode() {
        return "PT-" + UUID.randomUUID().toString();
    }

    public static String generateCategoryCode() {
        return "CT-" + UUID.randomUUID().toString().substring(0,8);
    }
}

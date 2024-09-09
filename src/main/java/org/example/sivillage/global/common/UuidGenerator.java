package org.example.sivillage.global.common;

import java.util.UUID;

public class UuidGenerator {
    public static String createProductCode() {
        return "PT-" + UUID.randomUUID().toString();
    }
}

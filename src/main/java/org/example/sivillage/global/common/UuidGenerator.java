package org.example.sivillage.global.common;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UuidGenerator {
    public static String generateProductCode() {
        return "PT-" + UUID.randomUUID().toString();
    }

    public static String generateCategoryCode() {
        return "CT-" + UUID.randomUUID().toString().substring(0,8);
    }

    public static String generateMemberUuid() {
        return UUID.randomUUID().toString();
    }
}

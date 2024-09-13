package org.example.sivillage.global.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public SimpleModule customEnumModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Size.class, new EnumDeserializer<>(Size.class));
        module.addDeserializer(ShoeSize.class, new EnumDeserializer<>(ShoeSize.class));
        return module;
    }
}
package org.icslab.sibadev;

import lombok.extern.slf4j.Slf4j;
import org.icslab.sibadev.common.config.security.oauth2.jwt.AppProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@MapperScan(basePackages="org.icslab.sibadev.mappers")
public class SibadevApplication {

    public static final String APP_LOCATIONS = "spring.config.location=" +
            "classpath:application.yml";
    //"/app/config/lua-skill-server/production-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(SibadevApplication.class)
                .properties(APP_LOCATIONS)
                .run(args);
    }

}

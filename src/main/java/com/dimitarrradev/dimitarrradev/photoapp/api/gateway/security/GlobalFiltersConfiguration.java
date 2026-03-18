package com.dimitarrradev.dimitarrradev.photoapp.api.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalFiltersConfiguration {

    private final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

    @Order(1)
    @Bean
    public GlobalFilter secondPreFilter() {

        return (exchange, chain) -> {
            logger.info("Second pre filter executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Second post filter executed");
            }));
        };
    }

    @Order(2)
    @Bean
    public GlobalFilter thirdPreFilter() {

        return (exchange, chain) -> {
            logger.info("Third pre filter executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Third post filter executed");
            }));
        };
    }

}

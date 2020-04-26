package io.sofastack.stockmng.beans;

import io.sofastack.dynamic.model.CacheContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjiang on 2020/4/26.
 */

@Configuration
public class BeansConf {

    @Bean
    public CacheContainer cacheContainer() {
        CacheContainer cacheContainer = new CacheContainer();
        return cacheContainer;
    }
}

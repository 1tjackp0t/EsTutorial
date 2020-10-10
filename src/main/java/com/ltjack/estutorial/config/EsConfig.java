package com.ltjack.estutorial.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @description: es配置类
 * @author: ltjack
 * @createTime: 2020-10-09 16:08
 */
@Configuration
public class EsConfig {

    @Value("${es.host}")
    public String host;
    @Value("${es.port}")
    public String port;
//   @Value("${es.scheme}")
//    public String scheme;

    @Bean
    public RestHighLevelClient esClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(host + ":" + port)
                .build();
        return RestClients.create(configuration).rest();
    }

}
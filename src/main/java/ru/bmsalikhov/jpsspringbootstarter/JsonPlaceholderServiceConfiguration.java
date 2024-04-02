package ru.bmsalikhov.jpsspringbootstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import ru.bmsalikhov.jpsspringbootstarter.todo.JpsTodoClient;

@AutoConfiguration
@EnableConfigurationProperties(JsonPlaceholderServiceProperties.class)
public class JsonPlaceholderServiceConfiguration {
    private final JsonPlaceholderServiceProperties jpsProperties;

    public JsonPlaceholderServiceConfiguration(JsonPlaceholderServiceProperties jpsProperties) {
        this.jpsProperties = jpsProperties;
    }

    @Bean("jsonPlaceholderRestClient")
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(jpsProperties.baseUrl())
                .build();
    }

    @Bean
    JpsTodoClient jpsTodoClient(RestClient restClient) {
        return new JpsTodoClient(restClient);
    }

}

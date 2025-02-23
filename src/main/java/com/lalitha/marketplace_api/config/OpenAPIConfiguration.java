package com.lalitha.marketplace_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server()
                .url("http://localhost:8080")
                .description("Development Server");

        Contact contact = new Contact()
                .name("Lalitha")
                .email("lalitha@gmail.com");

        Info info = new Info()
                .title("Market Place API")
                .version("1.0")
                .description("MarketPlace API")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}

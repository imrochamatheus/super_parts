package br.com.imrochamatheus.super_parts.config;

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
    public OpenAPI configureOpenApi () {
        Server server = new Server()
                .url("http://localhost:8080")
                .description("Development server");

        Contact contact = new Contact()
                .name("Matheus Rocha")
                .email("im.rochamatheus@gmail.com");

        Info infos = new Info()
                .title("Super parts API")
                .version("1.0")
                .description("Api to expose endpoints to manipulate cars and parts")
                .contact(contact);

        return new OpenAPI().info(infos).servers(List.of(server));
    }
}

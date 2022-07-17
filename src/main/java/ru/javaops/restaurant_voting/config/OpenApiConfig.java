package ru.javaops.restaurant_voting.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//https://sabljakovich.medium.com/adding-basic-auth-authorization-option-to-openapi-swagger-documentation-java-spring-95abbede27e9
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0",
                description = "<b>Restaurant voting system for deciding where to have lunch</b><br>" +
                        "<a href='https://github.com/JavaOPs/topjava/blob/master/graduation.md'> TopJava</a> course graduation" +
                        "<p>" +
                        "<b>Credentials:</b><br>" +
                        "<b>Admin</b>: <span>admin@gmail.com:admin</span><br>" +
                        "<b>User</b>: <span>user@yandex.ru:password</span><br>" +
                        "</p>",
                contact = @Contact(url = "https://github.com/RadSham", name = "Раджаб Шамсулвараев", email = "radsham9@gmail.com")
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }
}
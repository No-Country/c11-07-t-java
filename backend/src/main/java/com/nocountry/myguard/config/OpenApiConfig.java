package com.nocountry.myguard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "My Guard",
                        email = "contacto@contacto.com",
                        url = "https://myguard.com"
                ),
                description = "Se agrega una descripcion de nuestra app",
                title = "OpenApi specificacion - My guard",
                version = "1.0",
                termsOfService = "Terms of Service"

        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://myguard.com"
                ),

        },//Este metodo nos sirve para que todos los endPoint tenga seguridad
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)//Este esquema de seguridad nos sirve también para agregárselo a las clases que queramos con la annotation @SecurityRequirement(name = "bearerAuth")
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}

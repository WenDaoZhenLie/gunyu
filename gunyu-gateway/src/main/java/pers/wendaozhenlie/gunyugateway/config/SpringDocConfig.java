package pers.wendaozhenlie.gunyugateway.config;

import org.springdoc.core.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
@EnableScheduling
public class SpringDocConfig {
    @Value("${spring.application.name}")
    private String selfServiceName;

    private static final String SWAGGER_API_URI = "/v3/api-docs";

    private final SwaggerUiConfigProperties swaggerUiConfigProperties;

    private final RouteDefinitionLocator locator;


    public SpringDocConfig(SwaggerUiConfigProperties swaggerUiConfigProperties, RouteDefinitionLocator locator) {
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
        this.locator = locator;
    }

    @Scheduled(fixedDelay = 5)
    public void apis() {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
        locator.getRouteDefinitions().distinct().filter(routeDefinition ->
                routeDefinition.getUri().getHost() != null && routeDefinition.getUri().getHost().equalsIgnoreCase(selfServiceName)
        ).subscribe(routeDefinition -> {
            String serviceName = routeDefinition.getUri().getHost().toLowerCase(Locale.ROOT);
            AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(serviceName, serviceName + SWAGGER_API_URI, "");
            urls.add(swaggerUrl);
        });
        swaggerUiConfigProperties.setUrls(urls);
    }
}

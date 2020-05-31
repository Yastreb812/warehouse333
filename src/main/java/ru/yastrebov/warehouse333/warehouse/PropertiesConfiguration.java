package ru.yastrebov.warehouse333.warehouse;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/settings.properties")
public class PropertiesConfiguration {
}

package at.fhtw.paperless.paperless_ocr_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "at.fhtw.paperless.paperless_ocr_service.repository.jpa"
)
public class JpaConfig {
}
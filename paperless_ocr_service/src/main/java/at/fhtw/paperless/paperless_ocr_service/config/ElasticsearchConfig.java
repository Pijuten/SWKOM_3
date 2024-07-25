package at.fhtw.paperless.paperless_ocr_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "at.fhtw.paperless.paperless_ocr_service.repository.elasticsearch"
)
public class ElasticsearchConfig {
}
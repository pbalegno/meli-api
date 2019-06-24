package com.meli.solr.api.config;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StopWatch;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.apidoc.customizer.JHipsterSwaggerCustomizer;
import io.github.jhipster.config.apidoc.customizer.SwaggerCustomizer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

	private final JHipsterProperties.Swagger properties;
	
    static final String STARTING_MESSAGE = "Starting Swagger";
    static final String STARTED_MESSAGE = "Started Swagger in {} ms";
    
    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

	public SwaggerConfiguration(JHipsterProperties jHipsterProperties) {
		this.properties = jHipsterProperties.getSwagger();
	}
	

    /**
     * JHipster Swagger Customizer
     *
     * @return the Swagger Customizer of JHipster
     */
    @Bean
    public JHipsterSwaggerCustomizer jHipsterSwaggerCustomizer() {
        return new JHipsterSwaggerCustomizer(properties);
    }

	@Bean
	@ConditionalOnMissingBean(name = "swaggerSpringfoxApiDocket")
	public Docket swaggerSpringfoxApiDocket(List<SwaggerCustomizer> swaggerCustomizers,
			ObjectProvider<AlternateTypeRule[]> alternateTypeRules) {
		log.debug(STARTING_MESSAGE);
		StopWatch watch = new StopWatch();
		watch.start();

		Docket docket = createDocket();

		// Apply all SwaggerCustomizers orderly.
		swaggerCustomizers.forEach(customizer -> customizer.customize(docket));

		// Add all AlternateTypeRules if available in spring bean factory.
		// Also you can add your rules in a customizer bean above.
		Optional.ofNullable(alternateTypeRules.getIfAvailable()).ifPresent(docket::alternateTypeRules);

		watch.stop();
		log.debug(STARTED_MESSAGE, watch.getTotalTimeMillis());
		return docket;
	}

	protected Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2);
	}

}

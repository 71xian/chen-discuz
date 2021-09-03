package com.aoexe.discuz.system.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
						.name("token")
						.build()))
				.securityContexts(Collections.singletonList(SecurityContext.builder()
						.securityReferences(Collections.singletonList(SecurityReference.builder()
								.scopes(new AuthorizationScope[0])
								.reference("token")
								.build()))
						.operationSelector(o -> o.requestMappingPattern().matches("/.*")).build()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("DiscuzQ Doc")
				.description("DiscuzQ 文档")
				.termsOfServiceUrl("https://www.yxcling.cn")
				.contact(new Contact("chenyuxian", "https://www.yxcling.cn", ""))
				.version("1.0").build();
	}
}

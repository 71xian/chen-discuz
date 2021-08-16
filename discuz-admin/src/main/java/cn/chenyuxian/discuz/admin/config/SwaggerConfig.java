package cn.chenyuxian.discuz.admin.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.regex("^(?!auth).*$"))
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
	}
	
	private List<SecurityScheme> securitySchemes(){
		List<SecurityScheme> schemes = new ArrayList<>(1);
		Collections.addAll(schemes, new ApiKey("Authorization", "Authorization", "header"));
		return schemes;
	}
	
	List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> references = new ArrayList<>(1);
		Collections.addAll(references, new SecurityReference("Authorization", authorizationScopes));
		return references;
	}
	
	private List<SecurityContext> securityContexts(){
		List<SecurityContext> securityContexts = new ArrayList<>();
		Collections.addAll(securityContexts,
				SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$"))
				.build());
		return securityContexts;
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("DiscuzQ Admin接口文档")
				.description("个人学习使用")
				.version("1.0")
				.build();
	}
}

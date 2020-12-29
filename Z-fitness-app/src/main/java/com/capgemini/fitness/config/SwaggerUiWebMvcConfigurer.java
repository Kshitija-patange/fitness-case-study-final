package com.capgemini.fitness.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * This is Global Cor Web config class
 * //http://localhost:8082/springfox/swagger-ui/index.html
 */

@Component
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
	private final String baseUrl;

	public SwaggerUiWebMvcConfigurer(
			@Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
		registry.
		addResourceHandler(baseUrl + "/swagger-ui/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
		.resourceChain(false);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(baseUrl + "/swagger-ui/")
		.setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/fitness/")
		.allowedOrigins("http://editor.swagger.io");
		registry
		.addMapping("/v2/fitness-docs.*")
		.allowedOrigins("http://editor.swagger.io");
		registry
		.addMapping("/fitness/")
		.allowedOrigins("*");
		registry
		.addMapping("fitness/login")
		.allowedOrigins("http://editor.swagger.io");
		registry
		.addMapping("/v2/fitness-docs.*")
		.allowedOrigins("http://editor.swagger.io");
		registry
		.addMapping("/fitness/login")
		.allowedOrigins("*");
	}
}
package com.winterchen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 API
 * Created by winter on 2017/11/21.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.winterchen.web"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用 Swagger2构建RESTful APIS")
                .description("更多Spring Boot相关文章请关注：http://winterchen.com")
                .termsOfServiceUrl("http://winterchen.com")
                .contact("winterchen")
                .version("1.0")
                .build();
    }

}

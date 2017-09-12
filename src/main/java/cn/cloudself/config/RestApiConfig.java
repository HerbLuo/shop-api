package cn.cloudself.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 * <p>
 * change logs:
 * 2017/8/10 HerbLuo 首次创建
 */
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"cn.cloudself.controller"})
@Configuration
public class RestApiConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("shop-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.cloudself.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("apis of Shop")
                .termsOfServiceUrl("http://www.cloudself.cn/shop/")
                .contact(new Contact("herbluo", "http://www.cloudself.cn/", "i@closx.com"))
                .version("1.0")
                .build();
    }

}
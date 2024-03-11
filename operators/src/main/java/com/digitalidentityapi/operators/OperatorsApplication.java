package com.digitalidentityapi.operators;

import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OperatorsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OperatorsApplication.class);
        RabbitPublishMessage rabbitPublishMessage1 = context.getBean(RabbitPublishMessage.class);
        rabbitPublishMessage1.sendMessageToQueue("signDocuments", "{\n" +
                "  \"idCitizen\": 1234567890,\n" +
                "  \"UrlDocument\": \"https://<bucket-name>.s3.amazonaws.com/bae728c7-a7a3-4942-b9b5-3ca0…-b91126bb3d8f.image.jpg?AWSAccessKeyId=<AWS_ACCESS_KEY>&Expires=145671\",\n" +
                "  \"documentTitle\": \"Diploma Grado\"\n" +
                "}");
        //  rabbitGetMessage rabbitGetMessage1 = context.getBean(rabbitGetMessage.class);
        context.close();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
            }
        };
    }

}

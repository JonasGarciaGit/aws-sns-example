package com.jonas.springboot.aws.sns.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSSNSConfig {

    public static final String SECRET_KEY = "r17itstOGyqqhvW0ZMeped4C77RS/e8lhVli4hzr";
    public static final String ACCESS_KEY = "AKIARJ6PDT2BGQP6FT66";

    @Bean
    public AmazonSNSClient getSnsClient(){
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.SA_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(ACCESS_KEY,
                                SECRET_KEY))
                ).build();
    }


}

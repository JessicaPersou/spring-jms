package com.persou.springjms.configs;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class JmsClient {

    private static final String AWS_ENDPOINT = "http://localhost:4566";
    private static final String AWS_REGION = "us-east-1";
    private static final String AWS_ACCESS_KEY = "test";
    private static final String AWS_SECRET_KEY = "test";

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
            .region(Region.of(AWS_REGION))
            .credentialsProvider(() -> AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY))
            .endpointOverride(URI.create(AWS_ENDPOINT))
            .build();
    }
}
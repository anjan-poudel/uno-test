package com.uno.homeloans.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.uno.homeloans.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint:}")
    /*left empty as default*/
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {

        AmazonDynamoDBClientBuilder amazonDynamoDBClientBuilder
        = AmazonDynamoDBClient.builder().withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                .withRegion(Region.getRegion(Regions.AP_SOUTHEAST_2).getName());

        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDBClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                    amazonDynamoDBEndpoint, Region.getRegion(Regions.AP_SOUTHEAST_2).getName()
            ));
        }

        return amazonDynamoDBClientBuilder.build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }
}

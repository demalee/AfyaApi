package com.spring.afya.location.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
@EnableMongoRepositories(basePackages = "com.spring.afya.location.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("127.0.0.1")
	private String contactPoint;

	@Value("27017")
	private int port;
	
	@Value("locationdata")
	private String database;

	@Override
    protected String getDatabaseName() {
        return database;
    }
  
    @Override
    public MongoClient mongoClient() {
    	String connectionstring = "mongodb://" + contactPoint + ":" + port;
		MongoClient mongoClient = MongoClients.create(connectionstring);
		return mongoClient;
    }
  
    @Override
    protected String getMappingBasePackage() {
        return "com.spring.afya.location";
    }
  
}

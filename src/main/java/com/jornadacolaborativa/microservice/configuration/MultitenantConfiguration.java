package com.jornadacolaborativa.microservice.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MultitenantConfiguration {

    @Autowired
    private DataSourceProperties properties;

    @Value("classpath:tenants/*")
    private Resource[] resources;

    private static ArrayList<String> arrayTenantId = new ArrayList<String>(); 

    /**
     * Defines the data source for the application
     * 
     * @return
     * @throws IOException
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() throws IOException {
        
        Map<Object,Object> resolvedDataSources = new HashMap<>();        

        for (final Resource propertyFile : resources) {        
            Properties tenantProperties = new Properties();            
            HikariDataSource dataSource = new HikariDataSource();

            try {                
                
                log.info("Tenant file: " + propertyFile.getFilename());
                tenantProperties.load(propertyFile.getInputStream());                

                String tenantId = tenantProperties.getProperty("name").toUpperCase();
                arrayTenantId.add(tenantId);

                dataSource.setDriverClassName(properties.getDriverClassName());
                dataSource.setJdbcUrl(tenantProperties.getProperty("datasource.url"));
                dataSource.setUsername(tenantProperties.getProperty("datasource.username"));
                dataSource.setPassword(tenantProperties.getProperty("datasource.password"));
                dataSource.setMaximumPoolSize(2);

                if(properties.getType() != null) {
                    // dataSourceBuilder.type(properties.getType());                    
                }
                
                resolvedDataSources.put(tenantId.toUpperCase(), dataSource);
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
        }        

        // Create the final multi-tenant source.
        // It needs a default database to connect to.
        // Make sure that the default database is actually an empty tenant database.
        // Don't use that for a regular tenant if you want things to be safe!
        MultitenantDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    /**
     * Creates the default data source for the application
     * @return
     */
    private DataSource defaultDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setMaximumPoolSize(2);

        if(properties.getType() != null) {
            // dataSourceBuilder.type(properties.getType());            
        }
        return dataSource;
    }

    public static ArrayList<String> getArrayTenantId() {
        return arrayTenantId;        
    }

    public static boolean isValidTenantId(String tenantId) {        
        return arrayTenantId.contains(tenantId.toUpperCase());
    }
}

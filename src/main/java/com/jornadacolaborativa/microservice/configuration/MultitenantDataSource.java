package com.jornadacolaborativa.microservice.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultitenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        Object currentTenant = TenantContext.getCurrentTenant();
        log.info("determineCurrentLookupKey: " + currentTenant);
        return currentTenant;
    }
}


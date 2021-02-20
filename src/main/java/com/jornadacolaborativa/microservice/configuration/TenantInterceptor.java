package com.jornadacolaborativa.microservice.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jornadacolaborativa.microservice.exception.TenantException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception
    {   
        String tenantId;

        log.info("TenantInterceptor preHandle called");

        if (requestServlet.getHeader("X-TenantID") != null) {
            tenantId = requestServlet.getHeader("X-TenantID").toUpperCase();
            if (MultitenantConfiguration.isValidTenantId(tenantId)) {
                log.info("TenantId: " + tenantId);
                TenantContext.setCurrentTenant(tenantId);
            }
            else {        
                log.error("Invalid TenantId at arrayTenantId: " + tenantId.toUpperCase());
                throw new TenantException("Invalid TenantId at arrayTenantId: " + tenantId.toUpperCase());
            }
        }
        else {
            log.error("Invalid TenantId: null.");            
            throw new TenantException("Invalid TenantId: null.");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {        
        log.info("TenantInterceptor postHandle called"); 
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception
    {        
        log.info("TenantInterceptor afterCompletion called"); 
    }
}
package com.prolog.time.management;

import com.prolog.framework.authority.core.annotation.EnablePrologEmptySecurityServer;
import com.prolog.framework.authority.core.annotation.EnablePrologResourceServer;
import com.prolog.framework.authority.core.annotation.EnablePrologWebTokenSecurityServer;
import com.prolog.framework.microservice.annotation.EnablePrologFeignConfiguration;
import com.prolog.framework.microservice.annotation.EnablePrologService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
@EnablePrologService(loadBalanced=true)
@EnableTransactionManagement
@EnableFeignClients
@EnableCircuitBreaker
@EnablePrologWebTokenSecurityServer
@EnablePrologFeignConfiguration
//@EnablePrologEmptySecurityServer
@MapperScan("com.prolog.time.management.mapper")
public class ManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }

}

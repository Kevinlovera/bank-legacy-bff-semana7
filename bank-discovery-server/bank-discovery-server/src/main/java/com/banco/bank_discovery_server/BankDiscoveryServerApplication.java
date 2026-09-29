package com.banco.bank_discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BankDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDiscoveryServerApplication.class, args);
	}

}


package com.contestpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableJpaRepositories("com.contestpoint.repository")
@EntityScan("com.contestpoint.model.*")
public class ContestPointApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContestPointApplication.class, args);
	}
}

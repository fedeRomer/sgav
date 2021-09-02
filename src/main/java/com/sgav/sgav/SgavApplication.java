package com.sgav.sgav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan(basePackageClasses = {SgavApplication.class} )
//@EnableJpaRepositories
//@ComponentScan({"com.sgav.sgav.repository"})
public class SgavApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgavApplication.class, args);
	}

}

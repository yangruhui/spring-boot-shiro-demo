package com.twilight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.twilight.orm.base.BaseRepositoryFactoryBean;

@EnableJpaRepositories(basePackages = {"com.twilight"},
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

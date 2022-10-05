package com.pj.untapped;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.pj.untapped.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
public class UntappedBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(UntappedBackEndApplication.class, args);
	}
}

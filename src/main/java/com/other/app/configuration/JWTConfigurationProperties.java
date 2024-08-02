package com.other.app.configuration;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "app.jwt")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JWTConfigurationProperties {

	private String secret;
	private Duration accessTokenLifetime;
}

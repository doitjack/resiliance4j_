package io.github.robwin;


import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.monitoring.health.CircuitBreakerHealthIndicator;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableSpringBootMetricsCollector
@EnablePrometheusEndpoint
@EnableConfigurationProperties
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public HealthIndicator backendA(CircuitBreakerRegistry circuitBreakerRegistry){
		return new CircuitBreakerHealthIndicator(circuitBreakerRegistry.circuitBreaker("backendA"));
	}

	@Bean
	public HealthIndicator backendB(CircuitBreakerRegistry circuitBreakerRegistry){
		return new CircuitBreakerHealthIndicator(circuitBreakerRegistry.circuitBreaker("backendB"));
	}
}

// https://spring.io/guides/gs/spring-cloud-loadbalancer/
@Configuration
@LoadBalancerClient(name = "list", configuration=ListConfiguration.class)
public class WebClientConfig {
  
  @LoadBalanced
  @Bean
  WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }
}

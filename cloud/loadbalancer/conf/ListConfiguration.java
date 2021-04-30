// https://spring.io/guides/gs/spring-cloud-loadbalancer/
@Configuration
public class ListConfiguration {
  
  @Bean
  @Primary
  ServiceInstanceListSupplier serviceInstanceListSupplier() {
    return new ListServiceInstanceListSupplier("list");
  }
}

class ListServiceInstanceListSupplier implements ServiceInstanceListSupplier {
  
  private final String serviceId;
  
  public ListServiceInstanceListSupplier(String serviceId) {
    this.serviceId = serviceId;
  }
  
  public Flux<List<ServiceInstance>> get() {
    return Flux.jus(Arrays.
            asList(new DefaultServiceInstance(serviceId + 1, serviceId, "localhost", 8081, false),
                   new DefaultServiceInstance(serviceId + 2, serviceId, "localhost", 8082, false)));                   
  }
  
  public String getServiceId() {
    return serviceId;
  }
}

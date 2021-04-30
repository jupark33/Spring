// https://spring.io/guides/gs/spring-cloud-loadbalancer/

@RestController
@RequestMapping(value = "/v1")
public class ListController {
  
  private final WebClient.Builder loadBalancedWebClientBuilder;
  private final ReactorLoadBalancerExchangeFilterFunction lbFuntion;
  
  public ListController(WebClient.Builder webClientBuilder, ReactorLoadBalanceExchangeFilterFunction lbFunction) {
    this.loadBalancedWebClientBuilder = webClientBuilder;
    this.lbFunction = lbFunction;
  }
  
  @RequestMapping(value = "/list")
  public Mono<String> list(@RequestParam(value = "name") String name) {
    // ok
//    return loadBalancedWebClientBuilder
//            .filter(lbFunction)
//            .build().get().uri("http://list/svr")
//            .retrieve().bodyToMono(String.class)
//            .map(response -> String.format("%s", response));
    
    // ok
    return loadBalancedWebClientBuilder.baseUrl("http://list")
            .filter(lbFunction)
            .build().get()
            .uri(uriBuilder -> uriBuilder
                  .path("/svr")
                  .queryParam("name", name)
                  .build())
            .retrieve().bodyToMono(String.class)
            .map(response -> String.format("%s", response));
  }
}

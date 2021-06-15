# Apache Zookeeper는 10.1.1.100 PC 설치하고 2181 포트로 실행
# Client LoadBalancer 프로그램, client로 부터 http request 를 받으면
# Zookeeper 에 등록된 서비스로 부하를 분산하여 client 의 요청에 응답한다.

# application.yaml
'''
server:
  port:8080
  
logging:
  level:
    org.apache.zookeeper.CLientCnxn: WARN
'''

# ZkConsumerApplication.java
'''
@SpringBootApplication
@EnableDiscoveryClient
public class ZkConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZkConsumerApplication.class, args);
  }
}
'''

# HelloWorldClient.java
'''
@Configuration
@EnableFiegnClients
@EnableDiscoveryClient
public class HelloWorldClient {
  @Autowired
  private TheClient theClient;
  
  @FeignClient(name = "HelloWorld")
  interface TheClient {
    
    @RequestMapping(path = "/helloWorld", method = RequestMethod.GET)
    @ResponseBody
    String helloWorld();
  }
  
  public String HelloWorld() {
    return theClient.helloWorld();
  }
}
'''

# GreetingController.java
'''
@RestController
public class GreetingController {
  
  @Autowired
  private HelloWorldClient helloWorldClient;
  
  @GetMapping("/get-greeting")
  public String greeting() {
    return helloWorldClient.HelloWorld();
  }
}
'''

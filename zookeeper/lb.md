# Apache Zookeeper는 10.1.1.100 PC 설치하고 2181 포트로 실행
# Client LoadBalancer 프로그램, client로 부터 http request 를 받으면
# Zookeeper 에 등록된 서비스로 부하를 분산하여 client 의 요청에 응답한다.

# application.yaml
```
server:
  port:8080
  
logging:
  level:
    org.apache.zookeeper.CLientCnxn: WARN
```

# ZkConsumerApplication.java
```
@SpringBootApplication
@EnableDiscoveryClient
public class ZkConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZkConsumerApplication.class, args);
  }
}
```

# HelloWorldClient.java
```
@Configuration
@EnableFiegnClients
@EnableDiscoveryClient
public class HelloWorldClient {
  @Autowired
  private TheClient theClient;
  
  /**
   * name 이 "HelloWorld" 인 Service로 요청을 분산한다.
   * Service Registry 에 부하 분산 서버를 등록할 때 "HelloWorld"로 등록 해야 한다.
   */ 
  @FeignClient(name = "HelloWorld")
  interface TheClient {
    
    @RequestMapping(path = "/helloWorld", method = RequestMethod.GET)
    @ResponseBody
    String helloWorld();
    
    @RequestMapping(path = "/list", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String list(@RequestBody List list);
  }
  
  public String HelloWorld() {
    return theClient.helloWorld();
  }
}
```

# GreetingController.java
```
@RestController
public class GreetingController {
  
  @Autowired
  private HelloWorldClient helloWorldClient;
  
  @GetMapping("/get-greeting")
  public String greeting() {
    return helloWorldClient.HelloWorld();
  }
  
  @PostMapping("/list")
  public String list(@RequestBody List list) {
    return helloWorldClient.list(list);
  }
}
```

# List
```
public class List {
  private String mac;
  
  public String getMac() {
    return mac;
  }
  
  public void setMac(String mac) {
    this.mac = mac;
  }
}
```

# Apache Zookeeper 프로그램은 10.1.1.100 PC에 설치하고 2181 포트로 실행
# 로드발란싱 대상 서버

# build.grade
'''
dependencies {
  implementation 'org.springframework.cloud:spring-cloud-starter-zookeeper-discovery'
}
'''

# Svr1Application
'''
@SpringBootApplication
@EnableDiscoveryClient
public class Svr1Application {
  public static void main(String[] args) {
    SpringApplication.run(Svr1Application.class, args);
  }
}
'''

# application.yaml
'''
server:
  port:8081
  
spring:
  application:
    name: HelloWorld
  cloud:
    zookeeper:
      discovery:
        enabled: true
      connect-string: 10.1.1.100:2181
      
logging:
  level:
    org.apache.zookeeper.ClientCnxn: WARN
    
# HelloController.java
'''
@RestController
public class HelloController {
  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "1. Hello World"
  }
}
'''
'''

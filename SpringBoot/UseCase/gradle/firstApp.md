Spring Initalzr 를 통한 Spring Boot Gradle 프로젝트 생성

jar 파일 빌드 성공


```
// build.grade 
plugins {
	id 'org.springframework.boot' version '2.4.3'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-actuator'
	implementation 'org.springframework.boot:spring-boot-starter-webflux'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'io.projectreactor:reactor-test'
}

test {
	useJUnitPlatform()
}

// jupark add 20210302 - start
bootJar {
	archiveBaseName = 'demo'
	archiveFileName = 'demo.jar'
	archiveVersion = "0.0.0"
}
// jupark add 20210302 - end
```

```
jar 파일 패키징 
$ ./gradlew bootjar

실행확인
$ java -jar demo.jar
```

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.3)

2021-03-02 12:12:12.197  INFO 21455 --- [           main] com.example.demo.DemoApplication         : Starting DemoApplication using Java 13.0.1 on sollusyeongaebal-ui-MacBookPro.local with PID 21455 (/Users/solrnd/Documents/proj_sts/demo/build/libs/demo.jar started by solrnd in /Users/solrnd/Documents/proj_sts/demo/build/libs)
2021-03-02 12:12:12.200  INFO 21455 --- [           main] com.example.demo.DemoApplication         : No active profile set, falling back to default profiles: default
2021-03-02 12:12:13.325  INFO 21455 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 2 endpoint(s) beneath base path '/actuator'
2021-03-02 12:12:13.617  INFO 21455 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port 8080
2021-03-02 12:12:13.629  INFO 21455 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 1.793 seconds (JVM running for 2.179)

```

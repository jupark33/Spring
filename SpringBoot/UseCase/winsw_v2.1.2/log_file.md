winsw-v2.1.2
SpringBoot 프로그램을 윈도우서비스에 등록하여 실행하기 위해서 사용.

서비스 운용중 winsw의 자체 로그 파일이 계속 쌓이므로 사람이 개입하여 삭제해줘야 함. 
xml 설정 파일의 log 태그를 이용하여 자체 로그 파일 쌓이지 않도록 설정 가능.

app.xml
```
<configuration>
  <id>app</id>
  <name>app Service</name>
  <description>app Service</description>
  
  <executable>java<executable>
  <arguments>-jar app.jar</arguments>
  
  <log mode="none"/>
</configuration>

```

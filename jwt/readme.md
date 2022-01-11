REST API Design - Best Practice  
https://docs.microsoft.com/ko-kr/azure/architecture/best-practices/api-design  

리소스를 중심으로 API 디자인 구성  
https://adventure-works.com/orders  // Good  
https://adventure-works.com/create-order  // Avoid  
    
https://adventure-works.com/customers  // 고객 컬렉션의 경로  
https://advanture-works.com/customers/5  // ID가 5인 고객의 경로  
 >> API 정의 https://advanture-works.com/customers/{id}  
https://advanture-works.com/customers/5/orders   // 고객 5에 대한 모든 주문을 나타냄  
    
예제
리소스                 POST      | GET           | PUT                | DELETE  
/customers          새 고객 만들기 | 모든 고객 검색     | 고객 대량 업데이트        | 모든 고객 제거   
/customers/1        오류        | 고객 1에 대한 세부  | 고객 1이 있는 경우       | 고객 1 제거   
                              | 정보 검색         | 고객 1의 세부 정보 업데이트 |  
/customers/1/orders 고객1에 대한  | 고객 1에 대한      | 고객 1의 주문 대량 업데이트 | 고객 1의 모든   
                    새 주문 만들기 | 모든 주문 검색     |                     | 주문 제거  
  
200 성공   
201 작성됨 : 성공적으로 요청되었으며 서버가 새 리소스를 작성했다.  
202 허용됨 : 서버가 요청을 접수했지만 아직 처리하지 않았다.  
203 신뢰할 수 없는 정보 : 서버가 요청을 성공적으로 처리했지만 다른 소스에서 수신된 정보를 제공하고 있다.  
204 콘텐츠 없음 : 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.  
205 콘텐츠 재설정 : 서버가 요청을 성공적으로 처리했지만 콘텐츨르 표시하지 않는다. 204 응답과 달리 이 응답은   
     요청자가 문서 보기를 재설정할 것을 요구한다 ( 예 : 새 입력을 위한 양식 비우기 )  
206 일부 콘텐츠 : 서버가 GET 요청의 일부만 성공적으로 처리했다.  
207 다중 상태   
208 이미 보고됨  
226 IM Used  
  
비동기 작업  
오래 걸리는 작업 경우에 , 요청 처리가 수락되었지만 아직 완료되지 않았음을 나타내는 HTTP 상태 코드 202(수락됨)를   
반환한다.   
클라이언트가 상태 엔드포인트를 폴링하여 상태를 모니터링할 수 있도록 비동기 요청의 상태를 반환하는 엔드포인트를   
표시해야 합니다. 202 응답의 Location 헤더에 상태 엔트포인트의 URI를 포함한다. 예를 들면 다음과 같다.  
    
HTTP/1.1 202 Accepted  
Location: /api/status/12345  
    
클라이언트가 이 엔드포인트에 GET 요청을 보내는 경우 응답에 요청의 현재 상태가 포함되어야 한다.  
필요에 따라 예상 완료 시간 또는 작업 취소 링크를 포함할 수 있다.  
    
HTTP/1.1 200 OK  
Content-Type: application/json  
    
{  
  "status": "In progress",  
  "link": { "rel":"cancel", "method":"delete", "href":"/api/status/12345"}  
}  
    
데이터 필더링 및 페이지 매기기   
https://adventure-works.com/orders?limit=25&offset=50   
    
URI 버전 관리  
웹 API를 수정하거나 리소스의 체계를 변경할 때마다 각 리소스의 URI에 버전 번호를 추가 합니다.    
앞에서는 기존 URI가 전과 같이 계속 작동하여 원래 체계를 준수하는 리소스를 반환해야 합니다.  
    
https://adventure-works.com/v2/customers/3   
    
    
JWT    
 JWT = Json Web Token     
  로그인이 필요한 REST Service 이용시 Client는 accesstoken을 같이 서버로 보낸다.  
  jwt 토큰 탈취 방어는?  https 아니면 방어 못하는거 아닌가?   
      
https://llshl.tistory.com/32    
  jwt 토큰 = 헤더+페이로드+시그니처 = 헤더+페이로드+비밀키   
[ Access Token 탈취와 비밀키를 알아 내지 못하게 하는 방안 ]    
Access 토큰의 유효기간은 매우 짧게 ( 30분 ~ 1시간 )  
Refresh 토큰의 유효기간은 매우 길게 ( 7일 ~ 30일 )     				
    
세션 대비 Refresh Token의 장점  
세션은 항상 인증 요청을 할 때마다 세션 ID를 세션 저장소에 있는 세션 ID에 비교를 해야 한다.  
예를 들어 1만 번의 요청을 한다고 헀을 때 I/O는 1만 번 작동한다.  
    
그러나 Refresh Token은 Refresh하는 그 순간 즉, 요청을 했는데 access token이 만료되었을 때만  
I/O 작업을 한다. 30분에 1만번의 요청을 했을 때 1만번은 access token으로 I/O 작업 없이   
요청을 주고 받고 30분이 지나면 딱 한번 Refresh 하기 위해서 요청한다.   
    
Spring JWT example  
https://sup2is.github.io/2020/03/05/spring-security-login-with-jwt.html  
    
[클라이언트 측면] REST 통신하다가, 서버로부터 accesstoken expired 를 리턴받으면, 서버측에 refreshtoken 요청  
[클라이언트 측면] refreshtoken을 서버에 요청하여, 결과로서 리턴된 accesstoken 을 이용하여 , 다음번 통신때부터 사용  
    
refresh token = access token을 재발급.   
로그아웃 = access token 과 refresh token 을 모두 만료시킨다.  
모바일에서 사용시 , 쿠키를 사용할 필요가 없다. request header 에 accesstoken을 넣어주면 된다.  
세션방식으로그인은 서버 확장시 세션클러스팅 등을 이용해야 하지만 , JWT는 refresh token을 DB에 저장하면 서버 확장이 쉽다.   

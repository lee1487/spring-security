# spring-security

## 스프링 시큐리티 주요 아키텍처 이해
```
스프링 시큐리티

DelegatingProxyChain
  Spring Bean은 Servlet Filter에 Injection이 불가능하다.
  그럼 스프링으로 만든 필터와 Servlet Filter을 어떻게 연결할까? 바로 DelegatingFilterProxy이다.
  DelegatingFilterProxy는 Servlet Filter이다. 요청을 받게 되면 Spring Bean에게 요청을 위임하게 된다.
  따라서 Spring 기술도 사용하면서 Filter역할로도 사용할 수 있게 된다.
  만약 스프링 시큐리티를 사용한다면 springSecurityFilterChain으로 생성된 빈을 ApplicationContext에서 찾아서 요청을 위임하게 된다.

FilterChainProxy
  springSecurityFilterChain으로 생성되는 필터 빈은 FilterChainProxy이다.
  즉, DelegatingFilterProxy에게 요청을 위임 받고 실제 보안 처리를 하는 필터이다.
  스프링 시큐리티가 기본적으로 생성하는 필터도 있고, 설정 클래스에서 사용자가 API 추가 시 생성되는 필터도 있다.
  필터들은 Chain으로 연결되어 있어서 사용자의 요청을 필터 순서대로 호출하여 전달하게 된다.
  물론 사용자 정의 필터를 생성해서 기존의 필터 전, 후로 추가할 수 있다.
  마지막 필터까지 인증 및 인가 예외가 발생하지 않으면 보안이 통과하게 된다. 즉, Dispatcher Servlet으로 넘어가게 된다.

  참조
  https://velog.io/@gmtmoney2357/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-FilterChainProxy-%EB%8B%A4%EC%A4%91-%EC%84%A4%EC%A0%95-%ED%81%B4%EB%9E%98%EC%8A%A4

Authentication
  - 당신이 누구인지 증명하는것
    - 사용자의 인증정보를 저장하는 토큰 개념으로 인증시 id와 passowrd를 가지고 인증 검증을 위해 전달되어 사용된다.
      인증후 최종 인증 결과(user 객체, 권한 정보)를 담고 SecurityContext에 저장되어
      전역적으로 참조가 가능하다.
    - Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  - 구조
    - principal: 사용자 아이디 또는 User 객체를 저장
    - credentials: 사용자 비밀번호
    - authorities: 인증된 사용자의 권한 목록
    - details: 인증 부가 정보
    - Authenticated: 인증 여부

SecurityContextPersistenceFilter
  - SecurityContext 객체의 생성, 저장, 조회

  - 익명 사용자
    - 새로운 SecurityContext 객체를 생성하여 SecurityContextHolder 에 저장
    - AnonymousAuthenticationFilter 에서 AnonymousAuthenticationToken 객체를 SecurityContext 에 저장
  - 인증 시
    - 새로운 SecurityContext 객체를 생성하여 SecurityContextHolder 에 저장
    - UsernamePasswordAuthenticationFilter 에서 인증 성공 후 SecurityContext에 UsernamePasswordAuthentication 객체를
      SecurityContext 에 저장
    - 인증이 최종 완료되면 Session에 SecurityContext를 저장
  - 인증 후
    - Session 에서 SecurityContext 꺼내어 SecurityContextHolder 에서 저장
    - SecurityContext 안에 Authentication 객체가 존재하면 계속 인증을 유지한다
  - 최종 응답 시 공통
    - SecurityContextHolder.clearContext()
```



# study-security-custom-login

## 프로젝트 설정
- 프로젝트 선택
    - Project : Gradle Project
    - Language : Java
    - Spring Boot : 2.6.x
- Project Metadata
    - Group : study.security
    - Artifact : custom-login
    - Package name : study.security.customlogin
    - Packaging : Jar
    - Java : 11  

## 내용
### Authentication 메커니즘
- 인증 (Authentication)
    - Authentication는 인증을 하기 위한 정보와 인증을 받기 위한 정보가 하나의 객체에 동시에 들어간다. 인증을 제공해줄 제공자(AuthenticationProvider) 가 어떤 인증에 대해서 허가를 내줄 것인지 판단하기 위해서 직접 인력된 인증을 내주는 방식이다.
        - Credentials : 인증을 받기 위해 필요한 정보, 비번 등(input)
        - Principal : 인증된 결과, 인증 대상(output)- Details : 기타 정보, 인증에 관연되 주변 정보들
        - Authorities : 권한 정보들
    - Authentication을 구현한 객체들은 일반적으로 Token(버스 토큰과 같은 통행권)이라는 이름의 객체로 구현된다. 그리서 Authentication의 구현체를 인증 토큰이라고 불러도 도니다.
    - Authentication 객체는 SecurityContextHolder를 통해 세션이 있건 없건 언제든 접근할 수 있도록 필터체인에서 보장해준다.
- 인증 제공자(AuthenticationProvider)
    - 인증 제공자는 기본적으로 Authentication 을 받아서 인증을 하고 인증된 결과를 다시 Authentication 객체로 전달한다.
    - 인증 제공자는 어떤 인증에 대해서 도장을 찍어줄지 인증 관리자에가 알려줘야 하기 때문에 support() 메소드를 제공한다.
    - 인증 대상과 방식이 다양할 수 있기 때문엔 인증 제공자도 여러개 올수 있다.
- 인증 관리자(AuthenticationManager)
    - 인증 제공자들을 관리하는 인터페이스가 AuthenticationManager이고, 이 인증 관리자를 구현한 객체가 ProviderManager이다.
    - ProviderManager 는 복수로 존재 가능하다
    - 개발자가 직접 AuthenticationManager를 정의해서 제공하지 않는다면, AuthenticationManager를 만드는 AuthenticationManagerFatoryBean에서 DaoAuthenticationProvider를 기본 인증제공자로 등록한 AuthenticationManager를 만든다.
    - DaoAuthenticationProvider 는 반드시 1개 이상의 UserDetailsService를 발견 할 수 있어야 한다. 만양 없으면 InMemoryUserDetailsManager에 username과 password가 등록되어 제공된다.

[[이전으로]](https://github.com/heechul90/study-security-login-basic) [[다음으로]](https://github.com/heechul90/study-security-basic-authentication)

[[복습하기//적용하기]](https://github.com/heechul90/heech-member-server)

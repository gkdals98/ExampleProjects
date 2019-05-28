# Spring Security
+ 예전에 많은 도움을 받았던 사이트 - https://www.devglan.com/tutorials/spring-security-tutorial
+ 아키텍쳐 - https://spring.io/guides/topicals/spring-security-architecture
+ 아키텍쳐 한글 참고자료 - https://sjh836.tistory.com/165
+ 우선 기존에 마구 따라해서 구현했던 기본 Structure를 복습한다.

## Spring Security 무작정 따라하기
### 서론
+ Spring Security 구축 과정을 단계화해 어디서 적용할 때든 그 순서를 되짚으며 할 수 있게 한다.

1. Spring Security의 기본적인 구축을 위해 필요한 부분들을 전부 찾기.
2. 찾은 부분들을 목적에 맞게 분리해 몇 개의 큰 블록으로 나누기.
3. 따라하기 쉽도록 블록들간의 구현 순서 정하기.

+ 1부터 진행해보자.

### Spring Security의 뼈대 되짚기
+ 우리가 Spring Security를 구축하기 위해 정확히 뭘 필요로 했었는가.
+ Security를 구성하며 생성했던 요소들을 되짚어보자.

1. **SecurityConfig**
2. **UserAccountService**
3. **UserDetailsService**
4. **UserAccountRepository**
5. **UserAccountEntity**
6. **login.html** - 이건 좀 외적인거지만 아무튼.

+ 여기서 security의 핵심만을 구현대상 명으로 정리하자면 아래와 같이 된다.

1. **WebSecurityConfigurerAdapter** (SecurityConfig) - 모든 요청에 대해 관여
2. **UserDetailsService** (UserAccountService) - Login 시도에 관여. 로그인 요청을 분석하고 개발자가 원하는 적절한 방법으로 계정을 찾거나 찾지 못했음을 알린다.
3. **UserDetails** (UserDetailsImpl) - 생성될 시 UserAccountEntity를 받는다. 로그인 시도에 대해 검증 절차를 구현한다.

+ 제공되는 기능이 많은 프레임워크일수록, 어디까지가 구현의 뼈대이고 어디부터가 추가구현인지의 구분이 중요하다. 필수 뼈대를 먼저 잡고 원하는 덧붙이는 식으로 구현을 시작한다면 작업이 한 결 수월해지기 때문.
+ Spring Security의 가장 기초적인 뼈대를 구현하는 과정은 아래와 같다.

### 따라해보기

+ Security도 Spring인 만큼, 규격에 따른 구현서를 작성한다는 느낌으로 구현하면 된다. Spring Security가 여기엔 ~를 적어주세요 하는 요구사항을 주면 프로그래머는 그에 맞게 구현하고 싶은 내용을 적어주면 된다. 이후엔 Security가 알아서 한다는 느낌으로 이해하면 된다.
+ 이번엔 oath2나 csrf 공격 방지 등이 없이 진짜 기본만 구현하는 법을 복습한다. 단 mysql + hibernate는 사용한다.
+ 구현 순서에 따른 설명으로 진행한다. 아키텍쳐등을 고려하지 않을 것이기에 우선 현상 위주의 설명으로 간다.

#### 1. Gradle Dependency
+ Spring Boot Project를 생성한다. Build툴은 Gradle을 선택한다.
+ 사용하고 있는 IDE에 따른 lombok구성을 한다. lombok 설치법은 IDE마다 달라 여기서 그 방법을 적긴 힘들다. 하지만 인터넷에서 ide 이름 + lombok으로 검색하면 상세히 나온 사이트가 아주 많다.
+ 프로젝트의 build.gradle에 아래 dependency를 추가한다. hibernate, security 구성과 추가적인 기본 구성요소들이다.
```
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-webflux'
	runtime'com.h2database:h2'
	compileOnly 'org.projectlombok:lombok'
```

#### 2. DB 생성 및 properties값 추가
+ 예제는 mysql은 local에 위치하며 db이름은 testdb로 만든 것을 전제로 진행한다. DB 자체에서 추가로 설정할 건 없다.
+ 아래 내용을 src/main/resources의 application.properties에 추가하면 되는데.....
+ 예제를 작성하는 과정에서 오류가 발생했다. SSL과 관련된 부분인데 SSL을 false로 설정해 임시로 오류를 해결했다. 이에 나중에 이 문제를 해결하려면 기록이 필요할 것 같아 오류 해결 과정 로그를 남긴다.
```
#--------------------------------------------------
# Database
#--------------------------------------------------
spring.datasource.platform=mysql
spring.datasource.url=jdbc:mysql://localhost/testdb
spring.datasource.username= #DB에 접속하기 위한 유저 ID
spring.datasource.password= #DB 접속용 유저 PW
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.sql-script-encoding=UTF-8
spring.datasource.initialization-mode=always
spring.datasource.data=classpath:/sql/init_system_info-stp.sql,classpath:/sql/init_user_info.sql
```
+ warning이 뜬다. ssl이 어쩌구....
+ 여기저기서 찾아보니 이렇게도 하는 것 같아 아래로 바꿨다.
```
###URL###
server.ssl.enabled=false

#--------------------------------------------------
# Database
#--------------------------------------------------
spring.datasource.platform=mysql
spring.datasource.url=jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull
spring.datasource.username= #DB에 접속하기 위한 유저 ID
spring.datasource.password= #DB 접속용 유저 PW
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.sql-script-encoding=UTF-8
spring.datasource.initialization-mode=always
spring.datasource.data=classpath:/sql/init_system_info-stp.sql,classpath:/sql/init_user_info.sql
```
+ 그래도 뜬다. 최종적으로 오류를 잡은 properties는 아래와 같다.
```
#--------------------------------------------------
# Database
#--------------------------------------------------
spring.datasource.platform=mysql
spring.datasource.url=jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false
spring.datasource.username= #DB에 접속하기 위한 유저 ID
spring.datasource.password= #DB에 접속하기 위한 유저 PW
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.sql-script-encoding=UTF-8
spring.datasource.initialization-mode=always
spring.datasource.data=classpath:/sql/init_system_info-stp.sql,classpath:/sql/init_user_info.sql
```
+ url의 설정에서 useSSL=false로 바꿨더니 된다. 이건.... 이건 나중에 좀 다시 봐야한다. Security 예제를 진행하는 동안은 이대로 두자.

#### 3. Controller와 로그인용 페이지, 목적지가 될 main페이지를 만들어준다.
+ 아래와 같은 간단한 Controller Class를 작성해준다.
```
@Controller
public class cmBasicappController {
    @GetMapping(value="/")
    public void main(HttpServletResponse response) throws IOException{
    	response.sendRedirect("/html/main/main.html");
    }
    @GetMapping(value="/login")
    public void login(HttpServletResponse response) throws IOException{
    	response.sendRedirect("/html/login/login.html");
    }
}
```

+ src/main/resources의 static 폴더 밑에 html 폴더를 만들고 그 안에 login 폴더를 만든 뒤 login.html을 그 안에 생성한다. 페이지는 아래정도로만 구현해도 무방하다.
```
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>rogue in</title>
</head>
<body>
	<div class="container">
		<div>Login</div>
		<form action="/logindata" method="post">
			<input name="j_username" id="InputID" type="text" placeholder="Enter ID">
			<input name="j_password" id="InputPW" type="password" placeholder="Enter PW">
			<input name="submit" type="submit" value="Login"/>
		</form>
	</div>
</body>
</html>
```
+ 여기서 input name으로 설정된 j_usermname, j_password는 시큐리티는 id, password를 구분하도록 지어준 필드명이다. backend에서 이름만 통일해준다면 어떤 이름이 되어도 상관없다.
+ submit을 누를 경우, 데이터는 post 메시지로 backend의 /logindata 경로로 가도록 작성했다. 이는 form 태그의 기능이기에 위 코드와 같이 간단하게만 구현해줘도 정상적으로 동작한다. 
+ static 폴더 안에 main 폴더를 만들고 main.html을 만들어준다. 이번 예제에선 main.html은 별다른 기능이 없어도 좋다.
```
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Hi finally you are here.</h1>
</body>
</html>
```

#### 4. SecurityConfig 구성
+ Spring Security에선 http인증과 form 인증을 제공한다. 이번 예제에선 form 인증을 구현한다.
+ 우선 Security의 기본 로그인 기능으로 출력되는 loginpage를 직접 구현한 loginpage로 대체할 예정이다.
+ 아래와 같은 SecurityConfig를 생성한다.
```
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String login_page = "/login";

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/script/**");
    }
    @Override
    public void configure(HttpSecurity http) throws Exception{
    }
}
```
+ @EnableWebSecurity는 '이 어노테이션이 달린 Class가 WebSecurityConfigurerAdapter 클래스를 상속하며 해당 클래스의 구현을 통해 보안 구성을 정의할 것'임을 알린다. 이 어노테이션을 구현 클래스에 추가하는것 만으로도 WebSecurityConfigurerAdapter가 인식되며 기본 로그인기능이 설정돼 Webapp이 잠긴다.
+ ```public void configure(WebSecurity web)``` : 전역 보안에 대한 설정을 할 수 있다. 주로 리소스에 관련된 것들이 많다. 내용에 대한 것은 아래와 같다.
    * ```web.ignoring().antMatchers()``` - 인자로 받은 web요청에 대해, antMatchers의 조건에 해당한다면 ignoring()을 수행한다. 즉, 요청을 무시한다. 현 내용상에는 resource에 해당하는 모든 경로를 차단하고 있다.
+ ```protected void configure(HttpSecurity http)``` : http 요청에 대한 보안을 구성할 수 있다. 핵심이 되는 부분이라 아래 이어지는 코드로 분리해 설명한다. 기본적인 접근 설정을 위해 configure(http)를 아래와 같이 수정해준다.
```
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable(); // csrf 공격에 대한 방지를 disable 시킨다. csrf 방지를 걸면 추가적인 csrf 설정들이 필요해지기에 이는 추후에 다룬다.
        http
        .authorizeRequests()  //ExpressionInterceptUrlRegistry 객체를 반환한다. 해당 객체를 통해 Security의 세션 설정을 할 수 있다.
        .antMatchers("/**").permitAll() //antMatchers로 접근 제한을 걸 경로를 선언한 뒤, 해당 경로의 접근 권한을 작성한다. 지금의 경우는 /**에 대해 permitAll, 모든 경로에 대해 누구나 접근 가능하다는 뜻이다.
        .and()
        .formLogin() //Security의 로그인 방식인 httpBasic과 form 방식 중 form을 통한 로그인을 적용한다.
        .loginPage(login_page) //로그인되지 않은 사용자에게 안내되는 로그인 페이지를 지정한다.
        .loginProcessingUrl("/logindata") //post로 로그인 데이터를 받을 url을 지정한다. html에서 post 메세지를 /logindata 경로로 주기로 했기 때문에 똑같이 /logindata로 적어준다.
        .usernameParameter("j_username") //위 html에서 지정해줬던 id필드의 이름. password도 마찬가지.
        .passwordParameter("j_password")
        .defaultSuccessUrl("/html/main.html") //로그인 성공시엔 main으로 리다이렉트 된다.
        .failureUrl(login_page) //예제에선 로그인 실패시 login 페이지로 돌아가도록 설정해놨다.
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl(login_page); //로그아웃 시에도 로그인 페이지로 돌아가도록 설정.
    }
```

+ 해당 내용을 모두 적용하고 application을 동작시킨 뒤 localhost:8080으로 접속하면 아래와 같은 변화가 생긴다.
    * loginpage 설정이 적용되면서 시큐리티의 기본 제공 로그인 페이지가 더는 출력되지 않음.
    * 그러나 접근 설정이 모든 경로에 permitAll로 되어있기에 로그인 페이지 출력없이 루트경로인 메인페이지로 바로 가버림.
+ 로그인을 적용하기 위해서는 직접 만든 로그인 페이지를 디폴트 로그인페이지로 설정해야한다. 본격적인 로그인 적용 전에 우선은 로그인에 사용할 계정을 만들자.

#### 5. UserEntity를 만들어야 한다.
+ Role은 보통 별도의 enum table로 분리해서 관리하지만 지금은 예제다. 예제 특성상 사용했던 테이블은 나중에 지워야하고 지우기에는 Role을 같은 테이블에 두는 쪽이 편하다. 그러니 Role은 그냥 String 값으로 한다. Role을 별도 테이블로 관리하는 방법은 추후에 다룬다.
+ Hibernate를 통한 자동 Table 생성을 위해 아래 한 줄을 application.properties에 추가한다.
```
#--------------------------------------------------
# JPA
#--------------------------------------------------
spring.jpa.hibernate.ddl-auto=update
```
+ Table의 업데이트에 대한 내용이다. 자세한 설정은 ddl-auto로 검색해보면 나오며 Hibernate 문서에서 다룬다.
+ 아래의 엔티티를 추가한다.
```
@Data
@AllArgsConstructor
@Entity(name = "UserAccountTable")
public class cmUserAccountEntity {
	cmUserAccountEntity(){}
	@Id
	@Column(name = "UserId", nullable = false, length = 20)
	private String userid;

	@Column(name = "UserPw", nullable = false, length = 80)
	private String userpw;

	@Column(name = "RoleName", nullable = false, length = 80)
	private String rolename;
}
```
+ application을 실행해 Table이 DB상에 생성된 것을 확인한다.

#### 6. UserAccountRepository 구현
+ UserAccountRepository를 구현해 프로그렘이 UserEntity에 접근 가능하도록 만들어야한다.
+ 이 과정은 User정보를 Hibernate로 쓸 예정이기에 필요하다. User Data를 어디서 받아올지, User Data는 구체적으로 어떤 형식인지에 대한 정의는 전적으로 개발자가 해야한다.
+ 아래의 간단한 Repository를 구현하자.Repository는 Hibernate에서 제공하는 프레임워크이기에 Security를 공부하는 동안은 아 그냥 이렇게 적으면 hibernate가 알아서 기능구현을 해주겠구나 하고 넘기면 된다.
```
public interface cmUserAccountRepository extends CrudRepository<cmUserAccountEntity, String>{
	Long countByUserid(String userid);
}
```
+ ID값을 entity의 id로 지정해줬기에 id를 검색할 때 CrudRepository에서 제공하는 findById로 해도 된다. 따라서 findBy를 추가로 정의할 필요는 없다.
+ countByUserId는 security가 id 중복검사를 할 때 필요하다.

#### 7. UserDetails.
+ https://to-dy.tistory.com/86 + 이번에 참조한 블로그.

+ UserDetails는 사용자의 계정 정보에 시큐리티가 필요로 하는 기능을 추가해 보관하는 VO 클래스이다.
+ 아래와 같은 기능들을 가진다. 해당 기능들을 원하는대로 구현해 계정의 유효여부를 검사할 수 있다. 구현할 필요가 없는 부분은 내용 없이 true 리턴해주면 된다.
 getAuthorities(), getPassword(), getUsername(), isAccountNotExpired(), isAccountNonLocked(), isCredentialNonExpired(), isEnabled()
+ 우리는 UserDetails 클래스가 생성자에서 UserAccountEntity를 받고, 해당 Entity의 정보를 통해 로그인이 유효한지 판별하도록 구현할 것이다.
+ 이를 위해 아래와 같은 UserDetails를 생성하였다.
```
public class cmUserDetailsImpl implements UserDetails{

    private cmUserAccountEntity account;

    public cmUserDetailsImpl(final cmUserAccountEntity account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();
        String _role = null;

        if(account != null) {
            _role = account.getRolename();
            _grntdAuths.add(new SimpleGrantedAuthority(_role));
        }
        return _grntdAuths;
    }

    @Override
    public String getPassword() {
        if(this.account == null) {
            return null;
        }
        return account.getUserpw();
    }

    @Override
    public String getUsername() {
        if(this.account == null) {
            return null;
        }
        return account.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```
+ UserAccountEntity상에 계정 정보를 담을 거니까 getPassword, getUsername등은 당연히 account에서 가져온 정보를 사용한다.
+ getAuthorities는 해당 계정의 Role을 반환해주는 부분이다. 그 계정이 가진 role들(복수일 수도 있음)을 Set<GrantedAuthority> 형태로 반환해주면 된다.
+ 계정 상태에 대한 부분들 중 필수구현인 부분들은 그냥 전부 true를 리턴했다. 우리 계정은 당장은 검증할 상태가 없다.

#### 8. UserDetailsService의 구현.
+ UserDetailsServie 객체는 로그인 시도가 있을시 실행되는 loadUserByUsernaem 메서드를 가지고있는 서비스 클래스이다. AuthencationProvider은 UserDetailsService의 loadUserByUsername을 실행해 계정 정보를 UserDetails 타입으로 리턴받는다.
+ 예제에서 UserDetailsService는 아래와 같이 구현해준다.
```
#### @Service("userDetailService")
public class cmUserAccountService implements UserDetailsService{
    @Autowired
    cmUserAccountRepository repository; //위에서 작성했던 Repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new cmUserDetailsImpl(repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username))); //repository상에서 entity를 검색하고 해당 id가 없다면 Optional 기능을 통해 에러를 던진다.
    }
}
```
+ 예제에서 loadUserByUsername은 id를 통해 user를 찾고, 이를 통해 AuthencationProvider가 필요로하는 UserDetails 형태로 가공한 뒤 return해준다. 유효한 로그인의 검사는 상기 UserDetails에 구현한 절차를 거쳐 이루어진다. 즉, 현재 조건에선 id와 pw가 일치하면 무조건 유효한 로그인이다.

※ **Optional** - http://www.daleseo.com/java8-optional-after/
Optional이란 존재할 수도 있지만 안 할 수도 있는 객체를 감싸기위한 래퍼클래스이다.
우선은 저렇게 감싸고 넘길 수 있게 Hibernate의 repository는 optional로 객체를 반환한다는 사실만을 유념하고 넘어가자.

#### 9. SecurityConfig로 돌아가서 login 이외의 페이지를 잠그고 초기 계정 생성을 해준다.
+ SecurityConfig의 http configure를 아래와같이 수정해준다. login에 필요한 부분을 제외한 경로를 ADMIN 권한으로 돌리는 과정이다.
```
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http
		.authorizeRequests()
		.antMatchers("/html/login/**", "/login/**", "/css/**").permitAll()    //수정된 부분1
		.antMatchers("/**").hasAuthority("ADMIN")                            //수정된 부분2
		.and()
		.formLogin()
		.loginPage(login_page)
		.usernameParameter("j_username")
		.passwordParameter("j_password")
		.defaultSuccessUrl("/")
		.failureUrl(login_page)
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl(login_page);
	}
```
+ 이제 localhost:8080으로 접속하면 ADMIN 권한이 없기에 main대신 login 페이지로 리다이렉션된다.
+ 정식 절차상 아이디는 회원가입 페이지를 통해 생성되어야겠지만 예제에선 DB에 ID를 바로 때려박을 것이다.
+ src/main/resources 밑에 sql이란 이름의 pkg를 만들고 안에 init_user_info.sql을 만들어준다. file생성으로 생성하면 된다.
+ 해당 파일 안에 아래의 내용을 작성해준다.
```
INSERT IGNORE INTO UserAccountTable (UserId, UserPW, RoleName) VALUES ('testid', '$2a$04$q4Z3BA8ev5ej544ExKQimO.ohxBWi7X89rVML12qY0ZlOTLjTG672', 'ADMIN');
```
+ 여기서 password 값은 BCrypt로 인코딩된 값이다. 원값은 임의 비밀번호인 'pass1234'이다. BCrypt 인코딩을 위해선 https://www.devglan.com/online-tools/bcrypt-hash-generator 링크를 사용한다. 추가적인 지식으로 스프링 제공 Encoder의 기본 BCrypt Rounds는 4이다.
+ 해당 sql이 실행될 수 있도록 application.properties상에 아래의 내용을 추가해준다.
```
# Database로 주석을 단 부분에 아래 한 줄 추가
#--------------------------------------------------
# Database
#--------------------------------------------------
spring.datasource.data=classpath:/sql/init_user_info.sql

# JPA로 주석을 단 부분에 아래 내용 추가.
#--------------------------------------------------
# JPA
#--------------------------------------------------
spring.jpa.database=${spring.datasource.platform}
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
+ Application을 실행시키면 Usertable이 생긴다. 다만 아직 Bcrypt 암호화를 적용하진 않았기에 로그인은 되지 않는다.

#### 10. BCrupt 암호화 적용.
+ SecurityConfig 상에 아래의 내용들을 추가해준다.
```
    @Autowired
    private cmUserAccountService userAccountService;

    //Bean으로 등록된 BCrypt를 받아온다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //초기화 과정에서 userAccountService에 인코더를 BCrypt로 설정.
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountService).passwordEncoder(passwordEncoder());
    }

    // DaoAuthenticationProvider를 설정한다. 직접 정의한 UserDetailsService와 함께 BCrypt Encoder를 넘겨준다.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userAccountService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
```
+ 여기까지 마치면 localhost:8080에 로그인한 뒤, testid / pass1234를 통해 로그인을 시도하면 정상적으로 로그인이 이루어진다.

**우선 여기까지, Spring Security 무작정 따라하기 예제를 마친다.**


## Session
+ 스프링의 공식 세션 가이드 - https://docs.spring.io/spring-session/docs/current/reference/html5/



## Architecture
+ 아래쪽은 추후 참고할 링크들
https://spring.io/guides/topicals/spring-security-architecture
https://m.blog.naver.com/kimnx9006/220638156019
https://www.slideshare.net/madvirus/ss-36809454

+ authenticationManager

+ AuthencationProvider

> **_NOTE:_**  포멧저장용


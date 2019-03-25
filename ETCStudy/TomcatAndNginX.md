# Tomcat

## 톰캣이란?
+ 서블릿 컨테이너 웹 애플리케이션 서버.
+ 웹 서버와 연동하여 실행할 수 있는 자바 환경을 제공한다.

## Architecture
+ Tomcat의 핵심 Architecture는 5개의 수직구조로 형성된 객체들 + 커넥터이다.
+ 각각에 대해 톰캣 홈페이지에선 이렇게 설명하고 있다.
    * **Server** : 컨테이너 전체, 즉 톰캣 그 자체를 의미한다. 서버 인터페이스를 제공한다. 하나의 독자적 JVM instance이다.
    * **Service** : 하나의 엔진을 통해 여러 커넥터에 제공되는 컴포넌트이다.
    * **Engine** : 특정 서비스에 대한 요청진행용 파이프라인을 말한다. 복수 커넥터의 요청을 모두 수신하며 적절한 커넥터에 응답을 다시 전달합니다.
    * **Host** : Tomcat서버의 네트워크상의 이름을 의미한다. 엔진에는 여러 호스트가 포함될 수 있으며 Host 요소는 네트워크 별칭을 지원한다.
    * **Context** : 웹 어플리케이션을 의미한다. 호스트는 여러개의 컨텍스트를 포함할 수 있으며 각각에 유니크한 path를 부여할 수 있다.
    * **Connector** : 클라이언트와의 통신을 담당하는 거대한 전세환을 의미한다. 톰캣 하나에 여러 커넥터가 존재할 수 있으며 커넥터 인터페이스를 구현한다. Service에 연결되는 것으로 표현된다.

+ Engine으론 Catalina, Connector로는 HTTP용으론 Coyote Connector, Apache와의 연동을 통한 AJP용으론 JK2 Connector가 주로 쓰인다.

+ server.xml 내의 설정 테그들도 아래 순서로 정의되어 있다.
```
<Server port=“8005” shutdown=“SHUTDOWN”>
<Service name="Catalina">
<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"></Connector> //Service 내에서 Connector들을 정의한다.
<Engine name="Catalina" defaultHost="localhost">
<Host>
<Context></Context>
</Host>
</Engine>
</Service>
</Server>
```

+ 좀 더 심층분석 해보자 - https://daitso.kbhub.co.kr/61110/

### Server
+ Server의 설정 가능한 속성들은 아래와 같다. - https://tomcat.apache.org/tomcat-8.0-doc/config/server.html
    * className : Server의 구현으로 사용할 Java 클래스 이름. 이 클래스는 org.apache.catalina.Server interface를 구현해야한다. 별도 클래스를 지정하지 않는다면 디폴트인 org.apache.catalina.core.StandardServer가 지정된다.
    * address : 서버가 shutdonw 커맨드의 입력 여부를 리스닝할 TCP/IP 주소이다. 디폴트는 localhost이다.
    * port : Shutdown 명령어를 받을 TCP/IP 포트이다. 기본은 8005로 되어있다. Tomcat을 표준 셸 스크립트를 사용해 실행시키는 경우엔 절대 건드려선 안되지만 Apache Connons Deamon을 사용해 실행시키는 경우엔 -1로 설정해 비활성화 시키는 것이 권장된다.
    * shutdown : 위 설정값들을 통해 어떤 string을 받았을 때 이를 shutdown 커멘드로 인식할 것인가.
    * 자식 Element들 - <Service>, <GlobalNamingResources>가 있다.
    * GlobalNamingResources는 대표적으로 아래와 같은 것들이 있다.

### Service
+ Service의 설정 가능한 속성들은 아래와 같다. - https://tomcat.apache.org/tomcat-8.0-doc/config/service.html
    * className : Service의 구현으로 사용할 Java 클래스 이름. 이 클래스는 org.apache.catalina.Service interface를 구현해야한다. 별도 클래스를 지정하지 않는다면 디폴트인 org.apache.catalina.core.StandardService가 지정된다.
    * name : Service의 이름이 무엇으로 표시될 지 설정한다. log메시지 또한 해당 이름으로 남게 된다. 서버 내 각 서비스의 이름은 모두 고유해야 한다.
    * 자식 Elenemt들 - 복수개의 <Connector>, 단 하나의 <Engine>, 경우에 따라 <Executor>


### Executor
+ 참고 링크 - https://tomcat.apache.org/tomcat-8.0-doc/config/executor.html , https://jang8584.tistory.com/14
+ Executor
    * 톰캣의 구성요소들이 공유가능한 Thread Pool을 나타낸다. 톰캣에선 커넥터마다 개별로 Thread Pool이 생성되기에 다른 구성 요소간에 Thread Pool을 공유할 수 있게 해준다.
    * **ThreadPool이란**.
        + 기존 Tomcat은 클라이언트측의 요청이 각각 별도의 쓰레드에 의해 실행되었고 이를 위해 요청마다 쓰레드를 생성하는 과정에서 많은 부하를 일으켰다. 이를 해결하기 위해 3.2버전부터 도입된 것이 Thread Pool이다. Thread Pool 도입 후, 각 요청으로 생성된 Thread는 요청 처리 후에 재사용될 수 있도록 관리되며 open상태로 유지된다. 관리 중이던 Thread는 다른 요청이 들오면 해당 요청을 처리하고 요청 처리 이후엔 다시 관리 대상이 되며 또 다른 요청을 기다린다. Admin은 server.xml의 Thread Pool 설정을 통해 Thread 수의 상한선, idle상태의 Thread에 대한 최대 Thread 개수 및 기동시 생성될 최소한의 Thread 수 등을 설정할 수 있다.

    * 타 항목과 같이 name, className속성을 그대로 가지며 기타 속성 및 default값은 아래와 같다.
        + threadPriority : executor 내의 thread의 thread 우선 순위, 디폴트는 Thread.NORM_PRIORITY (5). 
        + daemon : thread가 daemon 스레드여야 하는가 아닌가, 디폴트는 true.
        + namePrefix : executor에 의해 생긴 thread에 붙을 이름 접두사. namePrefix + threadNumber로 이름이 붙는다.
        + maxThreads : 최대 thread 수. 디폴트는 tomcat 8 기준 200이다.
        + minSpareThreads : open상태로 유지될 Thread의 최소 개수이다. tomcat 8 기준 디폴트는 25.
        + maxIdleTime : 활성 thread 수가 minSpareThreads보다 작거나 같지 않으면 활성 스레드를 종료시키는데, 이 때 종료되기 전까지의 밀리 초 수. 디폴트는 6000
        + maxQueueSize : Queue에 대기 가능한 작업의 최대 수. 디폴트는 Integer.MAX_VALUE
        + prestartminSpareThreads : Executor를 시작할 때 minSpareThreads를 실행시킬 것인가. 디폴트는 false.
        + threadRenewalDelay : Listener를 좀... 좀 알고 와야 다시 적을 수 있어요.... [보류]
    * Tomcat의 Thread Pool 수에 대한 확인 예제는 https://sarc.io/index.php/tomcat/1042-tomcat-thread-pool 링크를 읽고 다시 정리하자. [보류]
    * 추가로 알아봐야할 건 왜 namePrefix값이 존재하는것인가, 복수개의 service가 존재할 수 있기 때문이겠지만 한 service 내에 executor가 여러개 존재할 수 있을지도 모른다. 당장 검색해본 바로는 역시 service가 복수개여서로 보이지만.

### Connector
+ 여기서부터는 좀 녹록치 않다. 고로 당장은 핵심만 짚고 동작만 보고 세세한건 필요할 때 API를 참조하자.
+ Connector 해부학 - https://www.c2b2.co.uk/middleware-blog/tuning-tomcat-connectors.php




# NginX
> 정말 좋은 블로그를 찾은 것 같다. - https://sarc.io/index.php/nginx?start=60
> 다만 좀 나중에 읽자. 일단 스텝 바이 스텝이라는거지.


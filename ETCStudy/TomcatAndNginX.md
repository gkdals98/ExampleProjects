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
    * 자식 Element들 - ```<Service>```, ```<GlobalNamingResources>```가 있다.
    * GlobalNamingResources는 대표적으로 아래와 같은 것들이 있다. [보류]

### Service
+ Service의 설정 가능한 속성들은 아래와 같다. - https://tomcat.apache.org/tomcat-8.0-doc/config/service.html
    * className : Service의 구현으로 사용할 Java 클래스 이름. 이 클래스는 org.apache.catalina.Service interface를 구현해야한다. 별도 클래스를 지정하지 않는다면 디폴트인 org.apache.catalina.core.StandardService가 지정된다.
    * name : Service의 이름이 무엇으로 표시될 지 설정한다. log메시지 또한 해당 이름으로 남게 된다. 서버 내 각 서비스의 이름은 모두 고유해야 한다.
    * 자식 Elenemt들 - 복수개의 ```<Connector>```, 단 하나의 ```<Engine>```, 경우에 따라 ```<Executor>```


### Executor
+ 참고 링크 - https://tomcat.apache.org/tomcat-8.0-doc/config/executor.html , https://jang8584.tistory.com/14
+ 톰캣의 구성요소들이 **공유가능한** Thread Pool을 관리한다. 톰캣에선 커넥터마다 각각 Thread Pool이 생성되나 server.xml에 Executor를 설정해주고 Connector에서 사용할 Executor를 지정해주면 해당 Connector는 Executor를 통해 Thread Pool을 생성하게 된다.
+ 기본적으론 주석처리 되어있다.

+ **ThreadPool이란**.
+ 기존 Tomcat은 클라이언트측의 요청이 각각 별도의 쓰레드에 의해 실행되었고 이를 위해 요청마다 쓰레드를 생성하는 과정에서 많은 부하를 일으켰다. 이를 해결하기 위해 3.2버전부터 도입된 것이 Thread Pool이다. Thread Pool 도입 후, 각 요청으로 생성된 Thread는 요청 처리 후에 재사용될 수 있도록 관리되며 open상태로 유지된다. 관리 중이던 Thread는 다른 요청이 들오면 해당 요청을 처리하고 요청 처리 이후엔 다시 관리 대상이 되며 또 다른 요청을 기다린다. Admin은 server.xml의 Thread Pool 설정을 통해 Thread 수의 상한선, idle상태의 Thread에 대한 최대 Thread 개수 및 기동시 생성될 최소한의 Thread 수 등을 설정할 수 있다.

+ executor는 name, className속성을 가지며 executor만의 속성 및 각 속성의 디폴트값은 아래와 같다.
    * threadPriority : executor 내의 thread의 thread 우선 순위, 디폴트는 Thread.NORM_PRIORITY (5). 
    * daemon : thread가 daemon 스레드여야 하는가 아닌가, 디폴트는 true.
    * namePrefix : executor에 의해 생긴 thread에 붙을 이름 접두사. namePrefix + threadNumber로 이름이 붙는다.
    * maxThreads : 최대 thread 수. 디폴트는 tomcat 8 기준 200이다.
    * minSpareThreads : open상태로 유지될 Thread의 최소 개수이다. tomcat 8 기준 디폴트는 25.
    * maxIdleTime : 활성 thread 수가 minSpareThreads보다 작거나 같지 않으면 활성 스레드를 종료시키는데, 이 때 종료되기 전까지의 밀리 초 수. 디폴트는 6000
    * maxQueueSize : Queue에 대기 가능한 작업의 최대 수. 디폴트는 Integer.MAX_VALUE
    * prestartminSpareThreads : executor를 시작할 때 minSpareThreads를 실행시킬 것인가. 디폴트는 false.
    * threadRenewalDelay : Listener를 좀... 좀 알고 와야 이걸 알아먹을 수 있어요.... [보류]
 + Tomcat의 Thread Pool 수에 대한 확인 예제는 https://sarc.io/index.php/tomcat/1042-tomcat-thread-pool 링크를 읽고 다시 정리하자. [보류]

### Connector
+ 여기서부터는 좀 녹록치 않다. 고로 당장은 핵심과 동작만 보고 세세한건 필요할 때 API를 참조하자.
+ Connector 해부학 - https://www.c2b2.co.uk/middleware-blog/tuning-tomcat-connectors.php

+ Connector는 HTTP, AJP, DB 연결 등 커넥터에 지정된 프로토콜을 통해 요청을 받고, 받은 요청을 Engine으로 넘기거나 처리된 결과값을 리턴하는 역할을 한다.
+ AJP 커넥터는 주로 웹 서버와의 연동을 위해 쓰인다.
+ 아래부터는 Connector의 핵심 구성요소들을 설명하며 함께 설명하는 속성값은 전부 ```<Connector>``` 태그의 속성값으로 지정할 수 있다.
+ Private Executor 
    * Connector는 executor 속성에 지정된 값이 없다면 자체적으로 private executor를 사용한다. 
    * private executor 설정을 server.xml의 Connector 태그 내에서 할 수 있기 때문에 executor에서 지정가능했던 속성들은 대부분 그대로 Connector에서도 지정가능하다. 만약 별도 executer가 지정되었다면 Connector 내의 Thread 관련 설정들은 전부 무시된다.
+ Acceptor
    * acceptor는 연결을 허용하는데 사용되는 스레드이다.
    * acceptor는 Connector 내의 acceptorThreadCount 값 설정을 통해 디폴트가 1개이며 지속되지 않는 커넥션이 많이 있을 경우, 이 수를 늘릴 수도 있지만 보통의 경우엔 성능문제로 권장되지 않는다.
    * acceptCount를 통해 연결 요청 큐의 최대 대기열 길이를 조정가능하다.
+ 기타 핵심 옵션들
    * **port** : 커넥터가 요청을 기다리는 TCP 포트 번호. 0이 지정되면 임의 포트가 지정되는 기능도 있지만 임베디드 및 테스트 응용 프로그램에서나 사용하는 옵션이다. 당연히 지정해줘야 한다.
    * **protocol** : 들어오는 트래픽을 처리할 프로토콜을 설정한다. 이는 HTTP인지, AJP에 따라 종류가 갈린다.
    * **connectionTimeout** : 연결 요청이 허용된 후 커넥터가 요청을 받기위해 대기하는 시간에 제한을 건다. -1을 지정할 시 무한이 되며 기본은 60000(60초)이다.
    * **SSLEnabled** : True로 지정할 경우 커넥터에 SSL 핸드 셰이크, 암호화, 암호 해독이 설정된다. 보다 자세하게 설정하기 위해 Connector  안에 자식 element로 SSLHostConfig를 설정해줘야한다.
+ 각 커넥터 종류별 핵심 설정 요소는 아파치의 API를 참조하도록 한다.
+ 총 몇 개의 요청을 처리할 것인가, 평균 요청 수와 최대 요청 수는 얼마인가, 얼마나 많은 instance가 요청 처리에 사용이 되며 각 instance가 요청 처리 시에 필요한 리소스는 무엇인가, 요청을 암호화 해야하는가가 Connector를 설정할 때 고려되는 가장 중요한 요건들이다.

+ 결국 Tomcat 자체의 성능에 가장 지대한 영향을 끼치는 것은 이 Executor와 Connector의 설정이 되겠다.

### Engine

### Host

### Context



# NginX
> 정말 좋은 블로그를 찾은 것 같다. - https://sarc.io/index.php/nginx?start=60
> 다만 좀 나중에 읽자. 일단 스텝 바이 스텝이라는거지.


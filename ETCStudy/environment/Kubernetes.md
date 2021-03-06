### 시작하기 앞서
+ ↓IBM에 좋은 자료들이 있다. 진짜 쿠버네티스 이름 다섯글자만 아는 지금은 우선 해당 자료들 기반으로 정리하고 개념잡는 것 부터 시작하자고. 
+ https://developer.ibm.com/kr/cloud/container/2019/03/05/kubernetes_practice/
+ 쿠버네티스 환경의 배포에 대한 이야기. 이 이야기 나오게 된 것 자체가 배포문제때문이라... - https://bcho.tistory.com/1266

### 쿠버네티스란? 
+ 복수개의 도커를 관리하기 위한 시스템.
+ 마이크로 서비스화를 전재로 한다. 운영되는 서버 어플리케이션을 기능별로 나누어 변경 및 조합 가능하도록 한다. 추가로 쿠버네티스에서는 복수개 노드에 어플리케이션을 분산시켜 각 노드에 걸리는 부하를 줄이는 것까지 포함인듯.
+ 파드(Pod, 팟)라는 컨테이너 관리 단위를 가지고 복수개의 컨테이너를 관리한다. 
+ 파드는 최소 구성요소로 한 개 이상의 컨테이너, 볼륨 자원, 네트워크 자원(IP, Port), 컨테이너 옵션을 가진다. 컨테이너 뿐만 아니라 컨테이너 자체의 환경까지 통제하는 셈.
+ 정확히는 컨테이너 오케스트레이션이란 작업으로 아래의 네 가지 기능을 수행한다.
    * 스케쥴링 : 컨테이너를 가장 여유로운 노드에 배치한다.
    * 클러스터링 : 여러개의 노드를 묶어 하나처럼 사용한다. 즉 모든 컨테이너가 내부 통신을 할 수 있다.
    * 서비스 디스커버리 : 컨테이너가 자동으로 배치되기 때문에 어디에 배치되었는지 검색하는 임무를 수행한다.
    * 로깅 및 모니터링 : 로그를 관리하고 보여준다.
+ IBM에서 말하는 장점은 아래와 같다.
    * 무중단 서비스. 서비스 고장시 운영중이던 파드를 복제해 기동시키는 방식으로 서비스의 중단을 방지한다.
    * 이식성. 쿠버네티스의 경우 컨테이너 자체의 환경도 통제하다보니 AWS 등의 클라우드에서 사용할 때 플랫폼간의 호환문제를 방지한다.
    * 자원 관리. 쿠버네티스는 파드에 사용할 자원들을 사전에 지정해 필요한 만큼의 자원만 할당한다. 뭔가 자원 관리에 관해 VM과의 차이도 있는 모양인데 그건 잘 모르겠다.
    * 확장성. 자원 사용률에 따라 파드의 수를 늘리는 식으로 자원 사용률을 조정한다. 파드가 CPU, 메모리 등의 자원을 과하게 소비중이라면 파드의 수를 늘리는 식으로(아마 클러스터링 필요) 이용률을 감소시킨다.
+ 즉, 쿠버네티스는 복수 노드와 도커를 활용해 거대한 어플리캐이션을 운영하기 위한 시스템이다. 시스템 메니지먼트라고 할 수 있다.


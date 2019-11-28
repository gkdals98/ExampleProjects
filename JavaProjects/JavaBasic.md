## Java
+ 정확히는 Java에 대해서 잘 몰랐던 부분, 몰랐던 유용한 기능들에 대한 링크를 저장하자.

#### Stream API
+ 람다와 함께 나온 Array 객체를 순환하며 사용할 수 있는 람다 API. 사용법은 아래 문서들을 참조해보자.
+ https://jdm.kr/blog/181
+ https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/

#### ThreadLocal
+ 한 Thread 내에서만 읽고 쓰기 가능한 변수를 생성한다. 즉, Thread의 지역 변수.
+ https://parkcheolu.tistory.com/17

#### Static의 생성 시기에 대해
+ Static은 해당 클래스를 호출하기 전까지 초기화되지 않는다. 즉, 해당 Static을 포함하는 클래스를 최초로 호출할 때 초기화된다.

#### Optional
+ 존재할 수도 있지만 안 할수도 있는 객체의 null 채크를 보다 안전하게 수행하기 위함.
+ https://www.daleseo.com/java8-optional-after/
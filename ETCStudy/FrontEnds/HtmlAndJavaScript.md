### 시작하기 앞서
+ html, javascript 관련 읽어봐야할 글 + 간단 요약.

### 1. 작동 원리
+ https://engineering.huiseoul.com/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%9E%91%EB%8F%99%ED%95%98%EB%8A%94%EA%B0%80-%EC%97%94%EC%A7%84-%EB%9F%B0%ED%83%80%EC%9E%84-%EC%BD%9C%EC%8A%A4%ED%83%9D-%EA%B0%9C%EA%B4%80-ea47917c8442
+ 특징을 쭉 적어보자.
+ 크롬의 경우 V8 엔진 사용.
+ 단일 쓰레드 사용.
+ 콜백 큐 이용.
+ V8 컴파일러란? - https://engineering.huiseoul.com/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%9E%91%EB%8F%99%ED%95%98%EB%8A%94%EA%B0%80-v8-%EC%97%94%EC%A7%84%EC%9D%98-%EB%82%B4%EB%B6%80-%EC%B5%9C%EC%A0%81%ED%99%94%EB%90%9C-%EC%BD%94%EB%93%9C%EB%A5%BC-%EC%9E%91%EC%84%B1%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%8B%A4%EC%84%AF-%EA%B0%80%EC%A7%80-%ED%8C%81-6c6f9832c1d9

### 2. 폰트
+ https://wit.nts-corp.com/2017/02/13/4258

### 3. Promise
+ https://developers.google.com/web/fundamentals/primers/promises
+ 넌 뭔데 갑자기 나타나 나의 앞길을 막는가 하니 이미 유명한 개념이더라. 좀 읽어보고 정리하자.
+ 아래로 쭉 요약하자면 
1. "1. 단일스레드" "2. 비동기" 환경인 자바스크립트에선 이벤트 발생 시 순차적 처리를 하기 위해 Promise라는 객체를 지원한다는것. 
2. promise는 이벤트에 성공했을 시 호출할 resolve와 실패할 시 호출할 reject를 선언할 수 있다는 것.
3. 이 과정에서 resolve를 표현하는 방법이 then이라는 것.
4. then은 다시 promise를 리턴하며 아래와 같은 형식으로 하나의 이벤트 발생에 대한 순차적 async를 수행할 수 있다는 점
5. catch는 rejects를 표현한다.
````
ayncThing1().then(function() {return asyncThing2();}).then(function(){return asyncThing3()})

```` 
+ 추가로 아래와 같은 Promise.all 이벤트도 지원한다. 복수의 promise를 넘겨 해당 promise들이 전부 완료되면 수행될 메서드를 지정할 수 있다.
```
Promise.all([promise1, promise2]).then(function (values) {
	console.log("모두 완료됨", values);
});
```
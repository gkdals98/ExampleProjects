# SCSS
+ https://poiemaweb.com/sass-basics - 가이드
+ https://www.google.com/search?q=rgb+picker&oq=rgb+p&aqs=chrome.1.69i57j0l5.3919j0j7&sourceid=chrome&ie=UTF-8 - RGB Picker
+ SCSS 문법을 공부하며 잊어버리기 쉬운 문법이나 Tip을 정리할 계획이다. 하는김에 CSS 쪽도 조금 복습하자.

### 1. CSS 기본 선택자
+ **기본 선택자**
    * ```*``` : 페이지 내 모든 컴포넌트
    * ```div``` : 태그 선택자. 예제는 div태그로 작성된 모든 컴포넌트를 선택한다.
    * ```#app``` : id 선택자. 예제는 app이란 id를 가진 컴포넌트를 선택한다.
    * ```img[href*="facebook"]``` : 속성 선택자 특정 값지정. 예제는 img태그이면서 href로 링크가 걸린 컴포넌트 중 href 값에 "facebook"이 포함되는 컴포넌트를 선택한다.
+ **연관성 선택자**
    * ```img[href]``` : 속성 선택자. 예제는 img 태그이면서 href로 링크가 걸린 모든 컴포넌트를 선택한다.
    * ```img[href^="www"]``` : 속성 선택자 시작 값지정. 예제는 img태그이면서 href로 링크가 걸린 컴포넌트 중 href 값이 "www"로 시작하는 컴포넌트를 선택한다.
    * ```img[href$="com""]``` : 속성 선택자 끝 값지정. 예제는 img태그이면서 href로 링크가 걸린 컴포넌트 중 href 값이 "www"로 끝나는 컴포넌트를 선택한다.
    * ```.btn-main-theme``` : class 선택자. 예제는 btn-main-theme class를 포함하는 컴포넌트를 선택한다.
    * ```li a``` : 하위 선택자. 예제는 li 내에 있는 모든 h1 태그를 선택한다.
    * ```li > a``` : 자식 선택자. 예제는 li의 자식 h1태그, 즉 li 바로 안에 있는 h1태그만을 선택한다. 두 단계 이상 안으로 들어가는 경우부턴 자식이 아니다.
    * 

### 2. CSS 간단한 속성들
+ **정렬 속성**
    * **margin-top, margin-left, margin-right, margin-bottom** : 컴포넌트와 상위 컴포넌트의 간격
        + **auto** : block component에 margin값으로 auto를 주면 자동으로 남는 여백 절반을 margin으로 두며 중앙 정렬이 된다.
    * **padding-top, padding-left, padding-right, padding-bottom** : 
+ **크기 속성**
+ **색상, 효과**


### 3. 좋은 템플릿들
+ http://rwdb.kr/freesource/ - 온갖 무료 리소스들이 있는 사이트. 
+ http://rwdb.kr/button_10/ - 위 사이트 들어갈 때 본 정말 마음에 드는 버튼 모음.
+ http://rwdb.kr/fonts/ - 위 사이트의 무료 폰트 모음인데 개중에는 상업 이용도 가능한 것들도 있다.
+ https://www.bestcssbuttongenerator.com/#/1 - 버튼 CSS 생성하는건데.... 대충 어떤 느낌으로 제작하는지 감 잡기에 좋을 것 같은데.
+ https://codepen.io/collection/ABNwxq/5/ - 백그라운드 이펙트 모음.
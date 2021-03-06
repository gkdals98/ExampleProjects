# SCSS
```
npm install sass-loader node-sass --save-dev
```
+ Vue loader의 경우, 버그가 있어 sass-loader@7.3.1 버전 이후의 sass-loader와 호환이 되지 않는다. 추후 지원을 기다려야함.
+ https://poiemaweb.com/sass-basics - 가이드
+ https://www.google.com/search?q=rgb+picker&oq=rgb+p&aqs=chrome.1.69i57j0l5.3919j0j7&sourceid=chrome&ie=UTF-8 - RGB Picker
+ SCSS 문법을 공부하며 잊어버리기 쉬운 문법이나 Tip을 정리할 계획이다. 하는김에 CSS 쪽도 조금 복습하자.
+ 간단한 SCSS 문법 사용에 익숙해지면 1단계 통과. mixin을 어느정도까지 마음대로 주무르면 2단계 통과. 그걸로 어떻게하면 css파일들을 깔끔하게 정리할 수 있을지 보이면 3단계 통과.

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
    * https://poiemaweb.com/css3-flexbox : **flex 레이아웃에 대한 설명**
+ **크기 속성**
+ **색상, 효과**
    * **border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px** : 모서리를 둥글게 만드는 방법.
    
### 3. CSS 애니메이션
+ 우선 아래 링크로 대체
+ https://developer.mozilla.org/ko/docs/Web/CSS/CSS_Transitions/Using_CSS_transitions

### 3. SCSS 문법
+ import, extend, mixin 관련 - http://megaton111.cafe24.com/2017/01/13/sass-%EB%AC%B8%EB%B2%95-%EB%B6%88%EB%9F%AC%EC%98%A4%EA%B8%B0import-%EC%83%81%EC%86%8Dextend-%EB%AF%B9%EC%8A%A4%EC%9D%B8mixin/
+ **주석**
```
//주석 1. 컴파일 됨.
/*주석 2. CSS로 컴파일 하는 순간 사라짐.*/
```
+ **Mixins** : 믹스인. 특정 CSS 효과를 재사용하고 싶을 때 템플릿화하는 방법을 의미한다. SCSS의 믹스인 정의와 사용은 아래와 같다.
```
//정의할 때
@mixin main-style { 
    //코드
}

//사용할 때
div #app{
    @include main-style
}
```
+ **Mixins Methods** : 믹스인으로 정의된 Method. 인자를 전달받아 적용할 수 있으며 기본값 설정도 가능하다.
```
//정의할 때
@mixin main-style( $width : 120px, $height ) { 
    //코드
}

//사용할 때
div #app{
    @include main-style( );
}
```
+ 외부 SCSS : 외부 파일에 file_name.scss와 같은 식으로 scss를 정의해놓은 경우 이를 import해서 사용할 수 있다.
 ```
 //외부 scss를 불러오는 경우
 @import "../common/frame_var.scss";
 
 //이후 마치 파일 내에 이미 정의된 값처럼 사용할 수 있다.
 #frame{
   width: $c-width;
 }
 ```
### 4. 좋은 템플릿들
+ http://rwdb.kr/freesource/ - 온갖 무료 리소스들이 있는 사이트. 
+ http://rwdb.kr/button_10/ - 위 사이트 들어갈 때 본 정말 마음에 드는 버튼 모음.
+ http://rwdb.kr/fonts/ - 위 사이트의 무료 폰트 모음인데 개중에는 상업 이용도 가능한 것들도 있다.
+ https://www.bestcssbuttongenerator.com/#/1 - 버튼 CSS 생성하는건데.... 대충 어떤 느낌으로 제작하는지 감 잡기에 좋을 것 같은데.
+ https://codepen.io/collection/ABNwxq/5/ - 백그라운드 이펙트 모음.
+ https://www.google.com/search?q=sass+theme+mixin&oq=sass+theme+mixin&aqs=chrome..69i57j33.8543j0j7&sourceid=chrome&ie=UTF-8 - 테마를 지정하는 법에 대해
+ https://codepen.io/jason-kinney/pen/AqbCi - Button에 테마 적용하기 예제. mixin이랑 덜 친할 때 보기 좋은 예제이기도 한 듯. 
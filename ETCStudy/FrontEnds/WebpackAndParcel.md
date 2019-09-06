# 시작하기 앞서
+ 최종목표는 Parcel로 Frontend를 번들링하는 것이다. 그러나 Parcel의 자료들을 보면 그 자체로는 감이 잘 안오는 설명이 대부분이다. 즉... 지가 아무리 좋아봐야 스터디 자료를 알아먹을 수 없어서 학습할 수가 없다. 이 난해한 스터디 자료를 분석하기 위해선 기존에 존재하던 번들러 중에 가장 대중적이고 많이 사용되며 이해하기 쉬운 자료가 썩어나는 놈을 먼저 배울 필요가 있다. 그래서 웹팩이다.

## 번들러란?
+ 브라우저는 대충 아래와 같은 과정을 통해 랜더링을 한다.

1. HTML 마크업을 처리하고 DOM 트리 빌드
2. CSS 마크업을 처리하고 CSSOM 트리 빌드
3. DOM 및 CSSOM이 결합되어 렌더링 트리 형성
4. 레이아웃을 실행하여 각 노드의 기하학적 형태 계산
5. 개별 노드를 화면에 페인트

+ 여기서 DOM과 CSSOM이 생성되기 전까지, 브라우저는 모든 콘텐츠의 로딩을 중단한다. 그리고 자바스크립트는 DOM 및 CSSOM을 쿼리하고 수정하며 CSSOM이 준비될 때 까지 실행이 일시 중지된다. 정확한 원리는 모르겠지만 저 과정을 수행할 때 자바 스크립트가 여러 곳에 분산되어있다면 그 수행 속도가 굉장히 저하된다고 한다.... 이건 브라우저 동작 원리를 다시 읽어봐야겠네. 아무튼 요는 js를 하나로 묶어 내보내는게 웹페이지의 성능을 비약적으로 끌어올린다는거고 Bundler는 저마다 각자의 방법으로 분산된 js를 하나로 묶어주는 녀석이라는거지.

+ 추가적으로 지원되는 기능들은 Webpack기준 아래와 같다.
=> https://firejune.com/1798/%EC%B4%88%EB%B3%B4%EC%9E%90%EC%9A%A9+Webpack+%ED%8A%9C%ED%86%A0%EB%A6%AC%EC%96%BC+%ED%8C%8C%ED%8A%B81+-+Webpack+%EC%9E%85%EB%AC%B8

+ 요약하자면
1. 프론트 엔드 코드에 npm으로 받아온 pkg를 사용할 수 있다.
2. ES6/ ES7 자바스크립트 코드 작성 가능(Babel을 사용해서)
3. 코드를 압축, 또는 최적화 할 수 있음.
4. LESS/SCSS등 현 세대 스타일시트를 CSS로 변환해줘서 구형 브라우저에서도 인식되게 해줌.
5. HMR(Hot modul replacement)을 사용할 수 있음. => 코드를 저장할 때 마다 Page Refresh가 자동으로 이루어짐.
6. 자바스크립트로 모든 유형의 파일을 포함할 수 있음.

# Webpack
## 1. 웹팩의 뼈대

+ WebPack 관련 링크 몇 가지
* http://blog.jeonghwan.net/js/2017/05/15/webpack.html - 개념
* https://meetup.toast.com/posts/153 - 구성하는 법
* https://haviyj.tistory.com/17 - 뼈대 프로젝트 예시 2. 조금 더 복잡한 예시를 다루고 있어서 추가.

+ javascript를 모듈단위로 구성하기 위한 컴파일러이다.
+ 모듈단위 소스를 묶어 하나의 소스로 내보내준다.
+ webpack cli를 이용해 모듈단위로 구성한 코드를 빌드한다.

+ 웹팩의 핵심 구성요소는 아래와 같다.
    * 엔트리 - 모듈이 많아질 수록 증가하는 모듈간 의존성에서 의존성 그래프의 시작점. 웹팩은 엔트리를 통해 필요한 모듈을 로딩하고 하나의 파일로 묶는다.
    * 아웃풋 - 번들된 결과물 js가 어디에 위치할지를 의미한다. html 파일 내에는 src 링크로 이렇게 생성된 output을 넣어준다.
    * 로더 - 웹팩은 Javascript밖에 모른다. 비 자바스크립트를 javascript로 변경하기 위한 구성요소가 로더인데, 문제는 이 인코딩이 상당히 종류가 다양하다는거고, 그래서 전부 외부툴이라는거다. 아래에서 곧 다시 언급할 babel이 대표적인 로더이다. 예제느낌으로 설명하자면 ES6에서 ES5로 변환(더 낮은 수준의 브라우저에도 인식)을 하는 로더이다. npm으로 설치. 여기서 윈도우에서도 npm이 된다는 걸 처음 알았지.... css-loader조차 따로 있다.....
    * 플러그인 - 최종적으로 번들된 결과물을 처리. 번들된 결과물을 난독화 하거나 특정 텍스트를 추출한다.
        + UglifyJsPlugin은 번들링 결과물을 인코딩해 함부로 분석할 수 없게 만든다.
        + extract-text-webpack-plugin은 SASS등이 매우 커질 경우, 따로 번들링하기 위한 플러그인이다.


+ 처음 보는거니까 우선 아래와 같은 구성의 간략한 예제를 무조건 옳다고 생각하고 살펴보자.
    * html
    * config.js
    * entry js
    * util js 

+ html 파일.
```
 <body>
  <script src="./dist/bundle.js"></script>
 </body>
```

+ webpack.config.js은 아래와 같은 형태를 가진다.
```
 module.exports = {
   entry : {
     main : './src/main.js' // 엔트리로 쓸 js를 지정한다.
   }
   output : {
     filename : 'bundle.js',  //번들링된 산출물 이름.
     path : './dist'  //어디다 놓을지
   }
    module: {   //번들링을 진행하며 실행될 것들. 
      rules: [{
        test: /\.js$/,      //test에 자바스크립트 확장자를 갖는 파일을 정규 표현식으로 지정.
        exclude: 'node_modules',  //webpack이 필요로 하는 패키지를 받는 폴더. 번들링 대상에서 제외.
        use: {
          loader: 'babel-loader',   //사용할 모듈들의 이름.
          options: {
            presets: ['env']
          }
        }
      }]
    }
    plugins: [
      new webpack.optimize.UglifyJsPlugin(),
    ]
 }
```

+ 엔트리가 될 main.js는 아래와 같이 작성했다.
```
 import Utils from './Utils'
 Util.log('Hello webpack')
```

+ 엔트리와 함께 합쳐서 내보내고픈 외부 모듈을 적는 Utils.js.
```
 export default class Utils {
  static log(msg) {console.log(msg)}
 }
```

+ 위와 같은 총 3 + n의 구성요소를 통해 번들링된 최종 페이지를 만든다.
+ 추후 학습하고 덧붙이는 내용으로는, export에 대한 부분이 약간 마음에 걸린다. webpack4에도 저런게 필요한가?


> **_NOTE:_** 잘 모르던 시절에 분석한 그래서 우리 환경(Spring Boot)에 Webpack 적용이 가능한가?
1. 방법에 대해.
+ http://justincalleja.com/2016/04/17/serving-a-webpack-bundle-in-spring-boot/
링크를 보면, 좀 까다로운 과정이 맞는 것 같긴 하다.
Spring boot와 webpack을 병행해서 사용하기 위한 방법을 소개하고 있다.

2. 실제 예제에 대해.
+ https://github.com/kingbbode/spring-vuejs/blob/master/README.md
이건 실제 프로젝트같은데. 보면 좀 괜찮지 않것어?

**학습한 뒤 내린 결론은 webpack은 runtime 혹은 빌드환경과는 완전 분리되어있으며 단순 개발 환경이기에 안될 이유가 없다. Webpack으로 묶은 js를 Spring의 원하는 경로에 갖다넣기만 하면 끝.**

## 2. Webpack을 우선 기본 사용법대로 써보기.
+ 처음이니까 그냥 무작정 따라해본다.
https://kdydesign.github.io/2017/11/04/webpack-tutorial/

```
C:\~>npm init -y
C:\~>webpack index.js bundle.js
C:\~>npm install webpack --save-dev
C:\~>.\node_modules\.bin\webpack -v
```
+ 버전이 정상출력된다면 다음으로 index.js, index.html을 만든다.
+ index.js
```
function component() {
  var element = document.createElement('div');
  element.innerHTML = 'Hello Webpack!!';

  return element;
}

document.body.appendChild(component());
```
+ index.html
```
<!-- ... -->
<body>
  <script type="text/javascript" src="bundle.js"></script>
</body>
<!-- ... -->
```
+ 그리고 bundling을 수행하려 한 순간, 오류가 발생했다.

```
C:\~>.\node_modules\.bin\webpack index.js bundle.js
Hash: 477d3f48dfc60f9ef4f5
Version: webpack 4.29.3
Time: 518ms
Built at: 2019-03-14 15:41:12
 1 asset
Entrypoint main = ./dist/app.bundle.js
[0] multi ./index.js bundle.js 40 bytes {0} [built]
[1] ./index.js 184 bytes {0} [built]

WARNING in configuration
The 'mode' option has not been set, webpack will fallback to 'production' for this value. Set 'mode' option to 'development' or 'production' to enable defaults for each environment.
You can also set it to 'none' to disable any default behavior. Learn more: https://webpack.js.org/concepts/mode/

ERROR in multi ./index.js bundle.js
Module not found: Error: Can't resolve 'bundle.js' in 'C:\~'
 @ multi ./index.js bundle.js main[1]
```

+ 그에 대한 솔루션은 아래의 경로에서 찾았다.
https://stackoverflow.com/questions/49389677/module-not-found-error-cant-resolve-bundle-js-in-users-jonathankuhl-docum

+ 최초 bundle.js로 묶을 때엔 -o 옵션을 결과물에 붙여줘야했던 것. 즉,

1. webpack index.js -o bundle.js
+ 위와 같이 index가 될 js와 -o 옵션, bundle.js라는 아웃풋의 이름 총 세 가지가 bundler의 명령어에 필요한 기본 옵션이다.

2. webpack --mode=development index.js -o bundle.js 
+ 이건.... 이게 dev-server와 관련된 옵션으로 보이는데 지식이 없어 아직은 뭐라 못하겠다. 모르겠다. 우선 찾아보고 다시 수정하자.

## 3. 로더가 뭔지 감잡아보기 with Barbel
+ Webpack이 제공하는 다양한 Loader들 중 가장 만만한 놈이 Barbel이다. 
+ Barbel은 ES6, ES7 문법의 로더인데 읽어본 자료들은 아래와 같다.

+ 예제 1 - https://www.valentinog.com/blog/react-webpack-babel/ : react, babel으로 구성되는 웹페이지 번들링하기.
+ 예제 1 - https://gompro.postype.com/post/1699968 
+ 예제 2 - https://kdydesign.github.io/2017/11/04/webpack-tutorial/

+ 종합하자면 Loader라는 놈들은 각자 담당하는 언어를 webpack이 알아먹도록 번역해주는 통역기들이다. 사용자는 config에 나 이런 통역기 쓸 거임! 이란 느낌으로 선언만 해주면(경우에 따라선 필요한 설정도 해주고) 그 다음부터는 크게 신경쓸 일이 없다는 것.
+ 어렵게 생각할 것 없이 내가 사용할 라이브러리의 Loader를 config에 추가해주기만 하면 내가 원하는 라이브러리를 쓸 수 있다. 그 정도로 생각하면 된다. 더 어려운 설정들은 굳이 지금 알 필요가 없다.

## 4. Webpack 4에서의 변화.
+ 정리하다보니 Webpack이 Parcel에게 자리를 뺏기지 않기위해 변화하고 있는 부분이 있다하여 우선 짚고 넘어간다.

=> https://meetup.toast.com/posts/153

블로그 내용을 발췌하자면,
```
몇 달 전에 웹팩 4가 나왔다. 
가장 큰 변화라고 할 수 있는 것은 개발 환경에 맞게 기본적으로 설정이 되어 있는
Development 모드와 프로덕션 환경에 맞게 설정이 되어 있는 Production 모드가 생긴 것이다. 
Parcel의 장점인 심플한 사용성을 수용한것으로 보인다. 
그리고 모드에 따라 적용되는 옵션이 달라졌다. 
변경된 내용을 조금 정리해봤다.

빌드 속도가 빨라졌다. 개발팀이 강한 자신감을 보인 부분이기도 하다. 
최대 98%까지 빨라질 수 있다고 한다. 
멀티코어를 사용하게 된 것도 아닌데 이 정도면 놀라울 정도의 최적화가 이뤄진것 같다.
webpack 코어와 webpack-cli 가 분리 배포 된다.
모드가 생겨 일정한 규칙만 지키면 설정 파일이 없이도 빌드가 가능하게 되었다. 상단에 서술한 Production, 
Development 모드를 말한다. 0CJS라고 표현해서 뭔가 봤더니 Zero configuration Javascript란다.
CommonsChunkPlugin이 deprecated되고 SplitChunksPlugin으로 내장되었으며 
optimization.splitChunks라는 옵션이 생겼다.
특별한 작업없이 WebAssembly 파일(wasm)을 직접 import해서 사용할 수 있다. 
웹팩 4에서는 실험적인 수준이고 웹팩 5에서 안정적으로 지원한다고 한다.
```
즉.... 여기도 4 이전과 4 이후의 자료가 공존하면서 혼파망을 이루고 있다는 뜻.

> **_NOTE:_** 그에 따라 다시 짚고가야하는 의문 왜 Parcel인가?
```
=> http://blog.jakoblind.no/parcel-webpack/
웹팩도 4 되면서 우리 겁나 빨라짐! 우리 config도 필요없어짐(반쯤 구라지만)! 하고 있는데 왜 굳이 Parcel인가?

1. Parcel은 Webpack과 달리 html 문서 또한 index로 잡을 수 있다.
2. Parcel은 하기의 의존성에 대해 자동으로 환경을 구성하여 config 파일을 필요로 하지 않늕다.
 => CSS, SCSS, Imsages, Babel, PostCSS, PostHTML, TypeScript, ReasonML/BuckleScript
   반면 웹팩은 위 의존성에 대한 config를 설정해야하기 때문에 사실상 config가 필수이다.
3. 근데 webpack은 또 js html 등등의 구성요소들을 전부 한 곳에 뭉뚱그려 박아넣어서 결과물이 더 좋네?
4. 그리고 웹팩은 좀 더 고급진 설정이 된다고 한다. 어떤 고급진 설정들인지, 정말 쓸모 있는지는 파봐야 알겠지만.

아무튼.... 이건 아직까지는 왜 Parcel인가로 결론이 나질 않는다. 지금까지 늘 그래왔듯 한 쪽은 빠르고 한 쪽은 섬세하다.
다만 그 섬세함이 유의미한지를 학습해봐야 결론이 난다는거지. 그게 좋은 결론일 수도 있고.
결론적으론 웹팩의 학습도 의미가 있다.
```

## 5. Webpack dev server
배포용과 개발용의 차이 - http://webframeworks.kr/tutorials/translate/webpack-the-confusing-parts/

#### Dev server란?
+ 개발과정에서 webpage의 코드가 수정될 때 마다 실시간으로 결과를 확인할 수 있는 서버이다. 수정사항이 저장되는 순간 바로바로 로컬의 서버가 페이지를 갱신시켜 결과를 보여준다.
+ webpack4에서 등장했다. Parcel에도 물론 비슷한게 있음.
+ 사용 방법은 우선 가이드에 따르면 webpack.config.js에서 아래 옵션을 주는 방법이 있다. 이 경우 node.js를 기반으로 개발용 서버가 돌아간다.
```
devServer: {
 inline: true,
 hot:true
 }
```
+ Spring boot에서 사용하기 - https://haviyj.tistory.com/25 - 아무리 좋아도 우리가 못쓰면 소용 없기에.
+ https://brightparagon.wordpress.com/2018/06/27/webpack-v4-development-configuration/

+ hot vs watch

+ 최종적으론 아래 셋을 갖추어야 한다는데.

1. 기본적인 webpackcli + watchmode : webpack을 실행시킬 cli, dev서버용 watch모드
2. webpack-dev-server : 말 그대로 dev server
3. webpack-dev-middleware : 헌데 이게 뭐하는 개념인질 모르겠음. 우선 이런게 있다고 적어만 두고 나중에 이해하면 수정해보자.


####  프로젝트 내의 dependencies를 업데이트하는 방법.
```
npm i -g npm-check-updates
npm-check-updates -u
npm install
```

## n. Tips 
+ 이해하고 넣은 부분은 아니다만 예제를 따라하다보니 아래 라이브러리는 유용할 것 같아 기록한다. 이제 이걸 여기 적어놨다는 사실만 잘 기억해내면 된다.
```
<script>
  // 모바일웹에서 터치 딜레이를 없애주는 라이브러리
  async("https://cdnjs.cloudflare.com/ajax/libs/fastclick/1.0.6/fastclick.min.js", function(){
    FastClick.attach(document.body);
  })
</script>
```


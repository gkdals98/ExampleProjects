### 시작하기 앞서 - Vue 프로젝트 구성에 참조한 레퍼런스
+ https://medium.freecodecamp.org/getting-started-with-vue-single-file-components-using-webpack-2ae078058688
+ 의존성은 아래와 같다. (package.json)
+ 개발 편의를 위해 Atom의 File - Settings - Install에 들어가 language-vue를 검색해 설치한다.

### 1. Vue의 도구들
+ 종류가 많아서 프로젝트 구성할 때 일일히 기억해서 갖다 쓰는거도 일이다. 뭐뭐가 있었는지, 그 중 지금 필요한게 뭔지만 참고할 예정이기에 간략한 개요만을 적는다.
#### Vue Cli
+ https://cli.vuejs.org/guide/
+ Vue 프로젝트 구성도구. Webpack을 사용하는 Vue 프로젝트를 구성하기 위해선 반드시 설치해야한다.
+ 당연하지만 npm 라이브러리이기에 nodejs의 설치가 필수이다. nodejs가 설치되어있다면 cmd를 열어 아래와 같이 입력해 설치한다.
```
C:\Users\~>npm install -g @vue/cli-service-global

C:\Users\~>npm install -g @vue/cli-init
```
+ 다음, vueclitest라는 이름의 임시 폴더를 만들어 Vue Cli로 프로젝트를 생성해보자.
```
C:\Users\~>mkdir vueclitest

C:\Users\~o>cd vueclitest

C:\Users\~\vueclitest>vue init webpack my-project

? Project name my-project
? Project description A Vue.js project
? Author ChoHangMin <chm0326@contela.com>
? Vue build standalone

# 기본 프로젝트 구성을 보고싶은 관계로 우선 전부 No 한다.

? Install vue-router? No                #Vue router 사용 여부.
? Use ESLint to lint your code? No      #ESLint. 코드 유지보수성 향상을 위한 문법 채크 툴 Linter의 일종.
? Pick an ESLint preset Standard
? Set up unit tests No                  #https://kr.vuejs.org/v2/guide/unit-testing.html - 유닛 단위(vue에선 싱글 파일 컴포넌트) 테스트 작성.
? Pick a test runner karma              #Yes를 선택했다면 여러 도구들을 안내해주는데 공식 문서에 karma를 가이드하고있으니 karma and mocha를 선택하자. 지금은 NO다.
? Setup e2e tests with Nightwatch? No   #전체 시스템 동작 테스트. 시나리오, 기능, 통합적인 기타 등등을 테스트한다.
? Should we run `npm install` for you after the project has been created? (recommended) npm         #init은 npm을 선택해줌.

# 잠시 기다리면 vue 프로젝트 구성에 필요한 파일들이 전부 준비된다.

   vue-cli · Generated "my-project".


# Installing project dependencies ...
# ========================

npm WARN deprecated browserslist@2.11.3: Browserslist 2 could fail on reading Browserslist >3.0 config used in other tools.
npm WARN deprecated bfj-node4@5.3.1: Switch to the `bfj` package for fixes and new features!
npm WARN deprecated browserslist@1.7.7: Browserslist 2 could fail on reading Browserslist >3.0 config used in other tools.

> uglifyjs-webpack-plugin@0.4.6 postinstall C:\Users\hmcho\vueclitest\my-project\node_modules\webpack\node_modules\uglifyjs-webpack-plugin
> node lib/post_install.js

npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN ajv-keywords@3.4.0 requires a peer of ajv@^6.9.1 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.9 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.9: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

added 1096 packages from 643 contributors and audited 11501 packages in 34.885s
found 12 vulnerabilities (7 moderate, 5 high)
  run `npm audit fix` to fix them, or `npm audit` for details

# Project initialization finished!
# ========================

To get started:

  cd my-project
  npm run dev

Documentation can be found at https://vuejs-templates.github.io/webpack
```
+ 프로젝트 구성이 완료되는걸 봤다면 이제 Webpack Dev Server를 기동시켜보자.
```
C:\Users\~\vueclitest>cd my-project

C:\Users\~\vueclitest\my-project>npm run dev

> my-project@1.0.0 dev C:\Users\~\vueclitest\my-project
> webpack-dev-server --inline --progress --config build/webpack.dev.conf.js

 12% building modules 22/29 modules 7 active ...cho\vueclitest\my-project\src\App.vue{ parser: "babylon" } is deprecated; we now treat it as { parser: "babel" }.
 95% emitting

 DONE  Compiled successfully in 4240ms                                                                          17:11:24

 I  Your application is running here: http://localhost:8080
```
+ 위와 같은 구성이 완료되면 http://localhost:8080을 통해 개발중인 페이지에 접근할 수 있다.
+ 핫리로드가 적용된다. vue파일의 변경사항을 저장하면 저장하는대로 페이지가 바로 리로드된다.
+ 개발모드 종료는 nodejs 실행중인 cmd에서 ctrl + c로. 종료하시겠습니까? 라는 문구가 나오면 Y를 입력하면 된다.
+ 추가로 우리가 쓸만한 프로젝트 초기 구성 명령은 아래와 같다.
    * vue init webpack-simple : dev서버를 돌릴 수 있는 최소한의 webpack 프로젝트
    * vue init simple : vue 최소 기능만 들어간 html 파일 1개 생성
+ 아래는 cli로 프로젝트 생성하면 나오는 가이드.
``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
```
+ **근데**.... 커밋은 어디까지 해야하는거지....? 우선 최소한의 파일만 커밋해볼까 아니면.... 아니면 node_modules 폴더 빼고 진짜 다 커밋해야하나... 내 생각엔 다 하는게 맞는듯...
+ build는 프로젝트 dir에서 아래와 같이 수행.
```
npm run build
```
#### Vuex
+ https://vuex.vuejs.org/kr/
+ Vue용 상태관리 패턴 라이브러리.
+ 원리는 React의 Flux와 비슷한 거니 Flux 관련 글로 링크를 대체한다. - http://webframeworks.kr/tutorials/react/flux/
+ Flux에선 MVC를 대체하는 아키텍쳐를 제시하는데 이 아키텍쳐는 데이터 흐름을 단방향으로 제한해 예기치 못한 오작동을 막으려는 취지로 구성되었다. 여기서 문제란 대규모의 데이터를 다룰 때 MVC의 모델이 늘어나면 동시에 갱신되어야 할 데이터들이 많아지고(예시로 새 글 데이터 & 안 읽은 글 데이터) 이로 인해 데이터의 흐름이 꼬이게 되는 현상이다.
+ 데이터의 흐름을 관리하는 디스패처, 전달되는 데이터인 액션, 디스패처를 통해 갱신되는 상태(이 패턴에선 데이터는 상태를 의미한다)를 담고있는 스토어가 주요 포인트이다.
+ promise(https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Using_promises)를 필요로 한다. 대상 브라우저가 promise가 없다면 es6-promise 라이브러리를 import해 쓴다.
```
npm install vuex --save
npm install es6-promise --save      #promise가 지원되지 않는 브라우저도 대상에 들어간다면.
```
```
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
```
#### Vue loader
+ https://vue-loader-v14.vuejs.org/kr/
+ webpack에서 .vue 파일을 컴파일하는 전처리기(loader)이다.
+ vue-cli로 프로젝트를 구성하면 자연스럽게 프로젝트 안에 포함되어있다. Webpack Dev Server상에서도 react hot loader처럼 vue 파일의 수정 사항을 바로바로 반영시켜주기에 개발 환경이 크게 개선된다. 사실상 우리가 지금 이 짓 하고있는 이유이기도 하다. - https://vue-loader-v14.vuejs.org/kr/features/hot-reload.html
#### Vue Router
+ https://router.vuejs.org/kr/
+ 예제 - https://jsfiddle.net/yyx990803/xgrjzsup/
+ 싱글페이지 웹페이지를 작성하기 위함. 즉 한 페이지 내에서 탭으로 A도 보고 B도 보고 하기 위함이다. 느려지지만 써먹을 곳이 많다.
+ vue cli로 초기설치 가능한 항목 중 하나지만 쌩 npm으로 프로젝트를 구성했다면 아래와 같이 가져올 수 있다.
```
npm install vue-router
```
+ js상에선 아래 구문으로 호출한다.
```
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
```
+ 그리고 찾아보면 개발용 빌드에 관련된 안내가 따로있다... 최신 빌드환경 쓰고싶으면 git에서 코드를 직접 받아와서 빌드하라고 적혀있는데 무엇을 암시하는걸까. Vue Cli 구성에서 제공하는 Router로 넣어도 Dev server 잘 동작하는 것 같던데.... 하다가 언젠가 막히면 알게되겠지.
```
git clone https://github.com/vuejs/vue-router.git node_modules/vue-router
cd node_modules/vue-router
npm install
npm run build
```
#### Vuetify
+ 비유하자면 vue 용 부트스트랩인데... 내가 쓸 일은 없을 것 같긴 한데...
#### awesome-vue

### 2. Vue PKG의 구성
+ Vue Cli를 통해 구성된 프로젝트의 각 폴더는 **살펴본 바로는** 아래와 같다.... 의외로 관련 자료가 없다.
    * root 디렉토리 : project root에는 개발동안 사용할 index.html과 npm 의존성이 기록된 package.json 및 기타 설정 파일들이 있다. 즉, 직접 손댈 파일은 없다.
    * build : 빌드에 관련된 config들이 보관된 폴더. build.js로 빌드를 수행하며 webpack.~.conf들은 각 환경에 따른 빌드 설정들인데 build.js는 초기엔 webpack.prod.conf를 참조하고 있다. 이 부분을 수정해 환경에 맞는 conf를 불러올 수 있다.
    * config : node.js와 관련된 설정들. 개발환경에서 만질 건 index.js에서 설정 가능한 localhost의 port 설정 정도...
    * node_modules : npm 라이브러리들이 모여있는 폴더. pkg.json에 적힌 놈들이 실제로 있는 곳으로 빌드시엔 반드시 exclude 시키고 빌드해야하는데 vue cli가 어련히 알아서 잘 했을테니 건드릴 부분은 없다 봐도 무방하다.
    * src : 실제 개발이 이루어지는 장소. 들어가보면 entry가 될 main.js와 root 컴포넌트인 App.vue, 그리고 리소스를 넣는 assets 폴더와 Hello World 코드가 들어가있는 HelloWorld.vue가 있다.
    * static : 이건 어디다 쓰는 폴더일까... gitkeep 파일 하나가 전부다. 
+ 요약하자면 개발을 위해서는 src 폴더를, 빌드 및 기타 설정을 위해서는 build 폴더와 config 폴더를 확인하면 된다.
+ 구조상 npm install ~ --save-dev 로 설치하면
+ 

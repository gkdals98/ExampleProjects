### 시작하기 앞서 - Vue 프로젝트 구성에 참조한 레퍼런스
+ https://medium.freecodecamp.org/getting-started-with-vue-single-file-components-using-webpack-2ae078058688
+ 의존성은 아래와 같다. (package.json)
```
{
    "dependencies": {
        "vue": "^2.5.16"
    },
    "devDependencies": {
        "babel-core": "^6.26.0",
        "babel-loader": "^7.1.4",
        "babel-preset-env": "^1.6.1",
        "babel-preset-stage-2": "^6.24.1",
        "css-loader": "^0.28.11",
        "vue-loader": "^14.2.2",
        "vue-style-loader": "^4.1.0",
        "vue-template-compiler": "^2.5.16",
        "webpack": "^4.11.1",
        "webpack-cli": "^3.0.2",
    }
}
```
### Vue의 도구들
+ 종류가 많아서 프로젝트 구성할 때 일일히 기억해서 갖다 쓰는거도 일이다. 뭐뭐가 있었는지, 그 중 지금 필요한게 뭔지만 참고할 예정이기에 간략한 개요만을 적는다.
#### Vue Cli
+ https://cli.vuejs.org/guide/
+ Vue 프로젝트 구성도구. 가장 먼저 설치해야한다.
+ 어케 쓰는 물건인가. nodejs를 설치했다는 전제하에 cmd에서 아래 명령행을 따라친다.
```
C:\Users\hmcho>npm install -g @vue/cli-service-global

C:\Users\hmcho>mkdir vueclitest

C:\Users\hmcho>cd vueclitest

C:\Users\hmcho\vueclitest>npm install -g @vue/cli-init

C:\Users\hmcho\vueclitest>vue init webpack my-project

? Project name my-project
? Project description A Vue.js project
? Author ChoHangMin <chm0326@contela.com>
? Vue build standalone
? Install vue-router? Yes
? Use ESLint to lint your code? No    #우선은 모르겠어서 전부 No로 처리했다. 나중에 살펴보자.
? Set up unit tests No
? Setup e2e tests with Nightwatch? No
? Should we run `npm install` for you after the project has been created? (recommended) npm

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



C:\Users\hmcho\vueclitest>cd my-project

C:\Users\hmcho\vueclitest\my-project>npm run dev

> my-project@1.0.0 dev C:\Users\hmcho\vueclitest\my-project
> webpack-dev-server --inline --progress --config build/webpack.dev.conf.js

 12% building modules 22/29 modules 7 active ...cho\vueclitest\my-project\src\App.vue{ parser: "babylon" } is deprecated; we now treat it as { parser: "babel" }.
 95% emitting

 DONE  Compiled successfully in 4240ms                                                                          17:11:24

 I  Your application is running here: http://localhost:8080
```
+ 위와 같은 구성이 완료되면 http://localhost:8080을 통해 개발중인 페이지에 접근할 수 있다.
+ 핫리로드가 바로 적용된다. vue파일의 변경사항을 저장하면 저장하는대로 페이지가 바로 리로드된다.
+ 개발모드 종료는 ctrl + c로. 종료하시겠습니까?라는 문구가 나오면 Y를 입력하면 된다.
#### Vuex
+ https://vuex.vuejs.org/kr/
+ Vue용 상태관리 패턴 라이브러리.
+ React의 Flux와 비슷한 거니 Flux 관련 글로 링크를 대체한다. - http://webframeworks.kr/tutorials/react/flux/
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
+ webpack에서 .vue 파일을 컴파일하는 전처리기이다.
+ vue-cli와 함께라면 핫 리로드를 제공한다. dev 서버와 함께라면 react hot loader처럼 vue 파일의 수정 사항을 바로바로 확인할 수 있어 개발 환경이 크게 개선된다. 사실상 우리가 지금 이 짓 하고있는 이유이기도 하다. - https://vue-loader-v14.vuejs.org/kr/features/hot-reload.html
```
npm install -g vue-cli
vue init webpack-simple hello-vue
cd hello-vue
npm install
npm run dev # 시작할 준비가 되었습니다!
```
#### Vue Router
+ https://router.vuejs.org/kr/
+ 예제 - https://jsfiddle.net/yyx990803/xgrjzsup/
+ 싱글페이지 웹페이지를 작성하기 위함. 즉 한 페이지 내에서 탭으로 A도 보고 B도 보고 하기 위함이다. 써먹을 곳이 많다.
```
npm install vue-router
```
```
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
```
+ 개발용 빌드에 관련된 안내가 따로있다... 최신 빌드환경 쓰고싶으면 git에서 코드를 직접 받아와서 빌드하라는데 무엇을 암시하는걸까. Vue Cli 구성에서 제공하는 Router 넣어도 Dev server 잘 동작하는 것 같던데.... 하다가 언젠가 막히면 알게되겠지.
```
git clone https://github.com/vuejs/vue-router.git node_modules/vue-router
cd node_modules/vue-router
npm install
npm run build
```


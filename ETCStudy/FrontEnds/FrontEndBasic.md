##Front End 개발의 상식들
+ 여기선 특별한 어떤 주제를 흐름에 따라 다룬다기보다는, front end의 환경 구축에 필요했던 것들 및 frontend의 상식을 메모한다.

## 찾아볼 키워드
+ TypeScript. 심플하게 요약하자면 변수 타입 정의가 가능한 Javascript. 그리고... 클래스랑 인터페이스를 제공하네? 이건 좀 탐난다. 뭔들 개요에선 안그렇겠냐만...

### 1. Webpack dev server 사용을 위한 npm pkg 생성
+ Node.js 환경으로 뭔가를 할 생각은 없지만 webpack의 dev-server를 써서 프론트엔드 개발환경을 구축하려면 결국 npm을 맞닥드릴 수 밖에 없다. 편한것도 사실이고 안다고 손해도 없으니 간단하게 정리하자.
+ npm이란? Node.js의 오픈 소스 라이브러리 생태계이다.
+ 이하 npm으로 pkg 디렉토리를 초기화 하는 방법에 대해서 서술한다. (nodejs는 그냥 홈페이지에서 받아서 설치하면 된다)
+ **추가로 pkg 구성에 대해, 어디까지가 Node.js 환경에 국한된 이야기고 어디까지가 번들링된 html을 생성하기 위한 부분인지를 명확히 구분짓지 않으면 나중에 분명 햇갈린다. 그러니 한 부분 한 부분의 이유를 잘 짚으며 넘어가자.**
#### [1] npm init
+ npm으로 초기화할 디렉터리로 이동, npm init을 통해 package.json을 생성한다. 
```
C:\~~~~>npm init
This utility will walk you through creating a package.json file.
It only covers the most common items, and tries to guess sensible defaults.

See `npm help json` for definitive documentation on these fields
and exactly what they do.

Use `npm install <pkg>` afterwards to install a package and
save it as a dependency in the package.json file.

//이하 다양한 정보를 요구한다. 괄호가 된 항목은 엔터를 치면 해당 내용이 반영된다.

Press ^C at any time to quit.
package name: (simpleroguelike)
version: (1.0.0)
description: Simple Roguelike Game
entry point: (index.js)
test command:
git repository: https://github.com/gkdals98/ExampleProjects.git
keywords:
author:
license: (ISC)
About to write to C:\~~~~\package.json:

{
  "name": "simpleroguelike",
  "version": "1.0.0",
  "description": "Simple Roguelike Game",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "repository": {
    "type": "git",
    "url": "git+https://github.com/gkdals98/ExampleProjects.git"
  },
  "author": "",
  "license": "ISC",
  "bugs": {
    "url": "https://github.com/gkdals98/ExampleProjects/issues"
  },
  "homepage": "https://github.com/gkdals98/ExampleProjects#readme"
}


Is this ok? (yes)

C:\~~~~>
```

#### [2] npm install
+ npm install을 통해서는 npm 생태계 내의 각 모듈을 가져올 수 있다.
+ 옵션들은 아래와 같다
    * --global(또는 -g)을 사용하면 해당 모듈이 전역으로 설치된다. 즉 시스템 어디서든 해당 모듈을 실행할 수 있게 된다. 
    * --save를 사용하면 package.json의 dependencies 항목에 설치한 패키지의 이름과 버전이 반영된다.
    * --save-dev를 사용하면 package.json의 devDependencies 항목에 설치한 패키지의 이름과 버전을 반영된다.
+ npm install에 아무런 값을 주지 않고 사용하면 해당 pkg에 있는 package.json을 읽어들여 명시된 의존성들을 설치한다. 만약 git에서 pkg를 내려받아 환경을 구축하려거든 다운받은 pkg 경로에서 npm install을 수행하면 된다.

#### [3] npm uninstall
+ npm uninstall 또한 npm install과 같은 옵션을 가진다. 그래서 설명할 건 위에서 다 한 셈인데 그냥 눈에 잘 띄라고 항목 하나 줘 봤어...

### 2. npm modules
+ 많이 쓸, 혹은 일반적으로 많이 쓰인다 하는 모듈들의 이름을 기록하자. 간단하게 적고갈 수 있는 모듈은 사용법 내지는 설명도 적고 일단 npm install 키워드 위주로 적는다.
+ Mocha - JavaScript 단위 테스트 프레임 워크.
#### sass-loader + node-sass
    * sass용 loader, node에서 sass를 돌리기 위한 툴
    * 설치 및 use 방법
```
npm install sass-loader node-sass --save-dev
```
```
module.exports = {
    ...
    module: {
        rules: [{
            test: /\.scss$/,
            use: [
                "style-loader", // creates style nodes from JS strings
                "css-loader", // translates CSS into CommonJS
                "sass-loader" // compiles Sass to CSS, using Node Sass by default
            ]
        }]
    }
};
```
```
//vue single file component에서 사용 
<style lang="scss">
```

우선 참고할 블로그들
https://velopert.com/3613 - 튜토리얼
https://d2.naver.com/helloworld/9297403 - 적용 가이드 from Naver


### 1. 개요

Vue... 만족도도 높고 Vue가 대단하다고 써본 사람들이 말하지만 현실은 리엑트가 대기업이다. 고로 리엑트도 Vue만큼 쓸 줄 알아야 한다. 보아하니 Vue에게 대체당할만큼 만만한 라이브러리가 아닐 뿐더러 Vue에게서 입지를 훌륭히 방어해내고 있을만큼 뛰어난 라이브러리로 보이니 리엑트도 배워보자.

+ 들어가기에 앞서 개념정리가 필요해보이는 외부 라이브러리의 이름들만 쭉 적어보자. 서드파티 라이브러리들이 정리가 안되는게 리엑트의 단점 아닐까. 그래도 뷰에 관련된건 리엑트가 전부 다 하고 나머지 라이브러리는 추가적인 기능제공을 위한 것들이라고 한다. 그럼 우린 리엑트를 배우는 동안은 뷰만 생각하면 된다.
    * React Native
    * React-router
    * Next.js
    * After.js
    * Redux
    * MobX
    * fr(e)actal


### 2. 공식문서상 리엑트 장점?
https://www.zerocho.com/category/React/post/5774fc91785a21150007807e
+ 컴포넌트를 잘게잘게 쪼개서 퍼즐조각 만들듯이 만들고 만든 컴포넌트를 붙여넣는 방식으로 개발하는게 리엑트이다.
+ 가상의 DOM을 활용한다. 브라우저의 DOM 갱신을 바로바로 수행하는게 아니라 가상 DOM과 비교해 바뀐 부분만 수정하는 식으로 브라우저의 부담을 줄였다. 
+ React로 개발할 때의 철학은 아래와 같다.
    * 모든 컴포넌트는 독립적으로 기능할 것.
    * 모든 컴포넌트는 재사용 가능할 것.
+ 랜더링할 컴포넌트의 구조를 작성하기 위해 JSX 문법을 사용할 수 있다.

#### 장점 1. Virtual DOM
+ 동작 원리에 대한 글 - https://velopert.com/3236
+ 랜더링 되지 않는 가상의 DOM이 페이지의 변화를 계산해 최종적으로 연산 완료된 DOM 상태만을 실제 DOM에 반영한다. 즉, 쓸 데없는 갱신을 방지해 프로세스를 효율적으로 동작하도록 한다.
+ Virtual DOM은 비슷하게 수동으로 랜더링 최적화를 지정할 수 있는 DOM fragment에 비해선 느리지만, 사용할 경우 DOM의 랜더링을 생략해도 되는 부분들을 죄다 채크하고 조작하는 작업을 피할 수 있다.
+ 즉 Virtual DOM을 사용하면 성능도 적당히 잡고 개발의 편의성도 잡을 수 있게 된다.

#### 장점 2. JSX 
+ React를 보면 아래와 같은 요상한 문법을 볼 수 있다.
```
function getGreeting(user) {
  if (user) {
    return <h1>Hello, {formatName(user)}!</h1>;
  }
  return <h1>Hello, Stranger.</h1>;
}
```
+ 이게 대체 뭘까? 아무 의심없이 쓰고있었지만 다시 생각해보면 정말 이상하다. ""조차 없이 html 비스무리한 문법을 Javascript에 바로 때려박고있다. 이게 JSX다. html에 대한 기초 지식과 JSX의 문법들을 숙지하면 Javascript상에서 예제와 같은 Xml 모양의 객체를 오브젝트마냥 던지고 받고 할 수 있게 된다. 추가로 {this.props.value} 이런식으로 작성하는게 XSS(Cross-site-scripting) 공격같은 거도 막는다 칸다. Vue의 Template과 비교해보면 일장일단인 듯.
+ 정의하자면. Javascript XML의 약어. Javascript 구문 내에 html과 유사하게 정의된 구문을 작성할 수 있게 해주는 확장형 구문. 쉽게말해 createElement('h1') 어쩌구 할 필요가 없이 ```<h1>Hello</h1>```를 Javascript상에 바로 작성할 수 있다.
+ 컴파일이 빠르고 알아보기 쉽다.

### 3. 공식문서의 틱텍토 예제
+ 공식 예제 - https://codepen.io/gaearon/pen/oWWQNa
+ 순서대로 따라하며 리엑트를 가볍게 흝어보자.

#### [1] 시작할 때 뼈대
+ Square는 아무것도 하지 않고 Button만을 랜더링함.
+ Board는 9개의 Square 를 랜더링함.

#### [2] 최초 선언에서 보이는 문법들
+ **React Component의 정의** : React.Component를 extends 하는 class로 컴포넌트 선언을 한다.
```class Componnent_Name extends React.Component {}```

+ **render()** : render는 ReactDOM이 컴포넌트를 어떤 형태로 DOM상에 구현할 지를 전달하는 부분이다.
```
render(){
  //여기에 로직을 넣을 수도 있다!
  //아래 status 부분에서 설명하는 const를 걸쳐가는 값의 적용 또한 여기서 하면 된다.
  return(
    <div>Hi {this.props.name}</div>
  );
}
```
+ **constructor(props)** : constructor는 해당 컴포넌트가 초기화될 시 수행될 동작을 정의한다.
```
constructor(props){
  super(props) //super에 props를 넘겨줘야 react 객체가 props와 함께 초기화된다. 
}
```
+ **state** : props는 상위에서 넘겨받는 인자라면 state는 자체적으로 수정 가능한 요소이다.
```
this.state.value
```
와 같은 식으로 참조가 가능하며
```
this.setState({value: 35});
```
와 같은 식으로 수정할 수 있다.
```
    this.setState({
      squares: squares,
      xIsNext: !this.state.xIsNext,
    });
```
와 같이 오브젝트 타입으로 넘기면 동시에 복수의 state 수정도 가능하다.

※State를 직접 수정하기보다는 const로 임의 value를 만든 뒤 setState로 해당 const 값을 넘겨주는 것이 권장된다.
  이는 구현상의 편의 문제뿐만 아니라 React의 최적화와도 관련된 문제라고 하니 지킬건 지키자.
  기타 장점은 아래와 같다.
  => 되돌리기 기능 등을 구현하기 쉬워짐
  => 직전 상태와 비교해서 값이 바뀌었다면 변화한 것으로 생각할 수 있기에 변화의 감지가 쉬워짐.
      이게 리엑트의 코어 동작과 연관된다는 그 부분같네.
  => shouldComponentUpdate(). 최적화와 직결되는 부분이다.
      https://reactjs.org/docs/optimizing-performance.html#examples
      익숙해 진 뒤에 예제를 꼭 보자. 우선순위 1도 성능, 2도 성능, 3도 성능이다.

+ **props** : 위에 constructor에서 받아온다 언급한 그 props이다. 아래와 같은 방법으로 컴포넌트에 props값을 넘긴다.
```
          <Board 
            squares={current.squares}		//이 이름과 value대로 props에 들어온다.
            onClick={(i) => this.handleClick(i)}/>
```
이를 받는 쪽은 아래와 같이 상위에서 넘겨준 props값을 사용할 수 있다.
```
  renderSquare(i) {
    return(
      <Square 
        value={this.props.squares[i]}            //props로 넘겨받은 value
        onClick={() => this.props.onClick(i)}  //props로 넘겨받은 function
      />
    );
  }
  ```
 정말 당연한거지만 잊을까봐 적자면 위 컴포넌트는 render()가 아님에도 JSX를 사용하고 있다. 즉, 이를 받아서 render상에서 rendering 하는 것 또한 가능하다. 몇 겹으로 겹쳐서 하면 가독성이 떨어지지 않을까 싶긴 한데...
 
 **value의 컴포넌트상에서의 표현 방법** : 가령 this.props.name = kim이는 props값이 있다면 아래와 같이 JSX 내에서 그 값을 참조할 수 있다.
```
render(){
  return(
    <div>{this.props.name}</div>
  );
}
```
method의 return값을 참조하고 싶은 경우에도 마찬가지이다.
```
  render() {
    return (
      <div>
        <div className="board-row">
          {this.renderSquare(0)}
        </div>
       </div>
    )
  }
```
위와 같이 그 어떤 값이라도 참조 가능한 값이기만 하다면 JSX 내에서 머스터치 기호로 읽어들일 수 있다.

**list의 표현**
```
    const moves = history.map((step, move) => {
      const desc = move ?
            'Go to move #' + move :
            'Go to game start';
      return(
        <li key={move}>
          <button onClick={() => this.jumpTo(move)}>{desc}</button>
        </li>
      );
    });
```
위의 것을
```
  <div>{moves}</div>
```
이렇게 해주면 안정적으로 li가 들어가는데.... step이 1단위로 증가겠고... move가 value인가...
이건 다른걸 좀 구현해봐야 알겠네...

=> https://velopert.com/957
     요오런 가이드가 있다.

**ReactDOM Rendering**
```
ReactDOM.render(
  element,
  document.getElementById('root')
);
```
+ 위와 같은 문법이 있다. JavaScript상에 React로 생긴 가상의 돔을 붙여넣기 하는 ReactDOM의 문법이다. 최종적으로 구현될 React Component는 ReactDOM을 타고 html의 구현지점으로 나가야한다.


#### [3] 최종 완성
```
class Square extends React.Component {
  render() {
    return (
      <button 
        className="square" 
        onClick={() => this.props.onClick()}>
        {this.props.value}
      </button>
    );
  }
}

class Board extends React.Component {
  renderSquare(i) {
    return(
      <Square 
        value={this.props.squares[i]}
        onClick={() => this.props.onClick(i)}
      />
    );
  }

  render() {
    return (
      <div>
        <div className="board-row">
          {this.renderSquare(0)}
          {this.renderSquare(1)}
          {this.renderSquare(2)}
        </div>
        <div className="board-row">
          {this.renderSquare(3)}
          {this.renderSquare(4)}
          {this.renderSquare(5)}
        </div>
        <div className="board-row">
          {this.renderSquare(6)}
          {this.renderSquare(7)}
          {this.renderSquare(8)}
        </div>
      </div>
    );
  }
}

class Game extends React.Component {
  handleClick(i){
    const history = this.state.history.slice(0, this.state.stepNumber + 1);
    const current = history[history.length - 1];
    const squares = current.squares.slice();
    if(calculateWinner(squares) || squares[i]){
      return;
    }
    squares[i] = this.state.xIsNext? 'X':'O';
    this.setState({
      history: history.concat([{
        squares: squares,
      }]),
      stepNumber: history.length,
      xIsNext: !this.state.xIsNext,
    });
  }
  
  jumpTo(step){
    this.setState({
      stepNumber: step,
      xIsNext:(step%2) ===0,
    });
  }
  
  constructor(props){
    super(props);
    this.state={
      history:[{
        squares:Array(9).fill(null),
      }],
      stepNumber: 0,
      xIsNext:true,
    }
  }
  render() {
    const history = this.state.history;
    const current = history[this.state.stepNumber];
    const winner = calculateWinner(current.squares);
    
    const moves = history.map((step, move) => {
      const desc = move ?
            'Go to move #' + move :
            'Go to game start';
      return(
        <li key={move}>
          <button onClick={() => this.jumpTo(move)}>{desc}</button>
        </li>
      );
    });
    
    let status;
    if (winner){
      status = 'Winner: ' + winner;
    }else {
      status = 'Next player: ' + (this.state.xIsNext? 'X' : 'O');
    }
    
    return (
      <div className="game">
        <div className="game-board">
          <Board 
            squares={current.squares}
            onClick={(i) => this.handleClick(i)}/>
        </div>
        <div className="game-info">
          <div>{status}</div>
          <ol>{moves}</ol>
        </div>
      </div>
    );
  }
}

// ========================================

ReactDOM.render(
  <Game />,
  document.getElementById('root')
);

function calculateWinner(squares){
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
  ];
  for (let i = 0; i < lines.length; i++){
    const [a, b, c] = lines[i];
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]){
      return squares[a];
    }
  }
  return null;
}
```

### 4.  React LifeCycle
+ 참고한 블로그 - https://velopert.com/3631

#### 생성
1. **constructor(props){ super(props); }** - 생성자.
2. **componentWillMount() {}** - 마운트 되기 직전 호출되는 수명주기. 이제는 필요없어졌기에 업데이트가 없어 UNSAFE로 분류된다.
3. **componentDidMount() {}** - 마운트 직후 호출되는 수명주기. 컴포넌트가 화면에 나타나는 순간.

#### 업데이트
4. **componentWillReceiveProps(nextProps) {}** - 컴포넌트가 새로운 props를 받게 될 때 호출. 현재는 UNSAFE 상태인 수명주기다.
5. **static getDerivedStateFromProps() {}** - props로 받아온 값을 state로 동기화하는 작업을 해줘야 하는 경우 사용.
6. **shouldComponentUpdate() { return true; }** - Virtual Dom상에선 부모 컴포넌트가 업데이트되면 자식컴포넌트들도 전부 함께 업데이트 된다. 그러나 업데이트가 불필요한 컴포넌트들은 분명히 존재하고, 그 업데이트를 막아 최적화를 시키기 위해 존재하는 함수가 이 함수이다. false를 리턴하면 리랜더링을 수행하지 않으며 if등으로 조건을 주어 리랜더링이 필요하지 않은 조건에선 리랜더링을 수행하지 않게 할 수 있다.
7. **componentWillUpdate() {}** - 위의 shouldComponentUpdate에서 true가 반환되면 호출된다. 현재 UNSAFE 상태이다. 기존엔 이 수명주기에 애니메이션 초기화, 이벤트 리스너 제거 등을 수행했다. getSnapshotBeforeUpdate로 대체가능하다.
8. **getSnapshotBeforeUpdate(prevProps, prevState) {}** - DOM의 업데이트 직전에 발생하는 생명주기. 실제 발생 타이밍은 아래와 같다. componentDidUpdate와 땔 수 없는 관계이기에 사용법은 9. componentDidUpdate에서 서술한다.
    + **render() -> getSnapshotBeforeUpdate(prevProps, prevState) -> 실제 DOM에 변화 발생 -> componentDidUpdate**
9. **componentDidUpdate** : DOM 업데이트 이후에 발생하는 수명주기. getSnapshotBeforeUpdate에서 값을 받아올 수 있는 snapshot 인자를 세 번째 요소로 받는다. 아래는 코드 예시이다.
```
getSnapshotBeforeUpdate(prevProps, prevState){
    //가상 DOM의 State와 prevProps의 State를 비교해 다르다면, 즉 변화가 있다면 동작하도록 설정.
    if (prevProps.array !== this.state.array) {
        return {       //return값은 object로 하는 편이 낫다.
            aaa, bbb
        };
    }
}

componentDidUpdate(prevProps, prevState, snapshot){
    if(snapshot){
        alert( snapshot.aaa )
    }
}
```

#### 제거
10. **componentWillUnmount() {}** : 등록했던 이벤트의 제거, 사용하던 setTimeout에 대한 clear Timeout. 사용했던 외부라이브러리 dispose

#### 추가적인 디버깅
11. **componentDidCatch(error, info) {}** : 에러가 있을 경우 잡아내기 위한 API. 사용법은 아래와 같다.

### 5. 리엑트 개발 환경 준비
####  Webpack으로 React 개발환경 만들기
+ create-react-app이란 npm 모듈이 있지만 vue cli에 비해 실용성이 없다. 아래 링크의 방법으로 수동으로 프로젝트를 생성하자.
+ https://medium.com/@benjaminwoojang/webpack-4%EC%99%80-babel-7%EC%9C%BC%EB%A1%9C-react%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%85%8B%EC%97%85%ED%95%98%EA%B8%B0-340e00d2760b
```
>> npm install react react-dom
```
+ ReactDOM이란? - React로 만들어진 DOM과 가상 DOM을 실제 DOM에 랜더링하기위해 필요한 라이브러리. 마지막에 ReactDOM.render로 html상의 특정 div에 React로 생성된 DOM을 로드시켜야한다.


### 6. 프로젝트 관리
+ https://ko.reactjs.org/community/examples.html
+ 적절한 예시들은 위에 다 있다.
+ js, css를 나눠서 관리한다.

### 7. 추가 문법
#### input 받기
https://velopert.com/3634
###


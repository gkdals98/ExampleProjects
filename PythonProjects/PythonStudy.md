## Python

> ### 링크
> * Python EXE를 만드는 법 - https://wikidocs.net/21952
> * 초보가자 구조를 참조하기 좋은 Python Project들 - https://github.com/MunGell/awesome-for-beginners#python 

### 메모
* Pycharm cmd 라인 띄우기 - **Alt F12**
* venv 작업환경 구축을 위해 global로 설치된 모든 의존성을 제거하고 virtualenv 모듈을 설치하는 방법.
```
#windiw cmd 라인에서 수행한다.
#global로 설치된 모듈의 리스트를 requirements.txt에 저장한다.
>>C:\Users\hmcho> pip freeze > requirements.txt

#해당 list에 있는 모듈을 모두 삭제한다. 즉 global로 설치된 모듈을 전부 삭제한다.
>>C:\Users\hmcho> pip uninstall -r requirements.txt -y

#완료되면 requirements.txt 를 삭제해준다.
>>C:\Users\hmcho> del requirements.txt

#global로 venv를 설치해준다. global 모듈은 venv만 남기는게 좋다.
>>C:\Users\hmcho>virtualenv env
```
* virtual env 구성하기
```
#Pycharm cmd 라인에서 수행한다.
#virtualenv 모듈을 사용해 분리된 가상 env 환경을 생성한다.
C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject>virtualenv env

Using base prefix 'c:\\users\\hmcho\\appdata\\local\\programs\\python\\python37-32'
New python executable in C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject\env\Scripts\python.exe
Installing setuptools, pip, wheel...
done.

#virtualenv 에 접속하기 위해서는 아래와 같은 명령을 수행한다.
C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject>env\Scripts\activate.bat
(env) C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject>

#접속된 venv에서 모듈을 설치하는 방법은 아래와 같다. 예시는 PyQt5의 설치이다.
(env) C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject>pip install PyQt5

#venv에서 빠져나오는 방법은 아래와 같다.
(env) C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject>deactivate
C:\Users\hmcho\PycharmProjects\RepositoryMakeTest\TestProject>
```

> ### 추가적으로 필요한 인프라
> * Github에서 Clone받은 Project의 venv 의존성이 자동으로 구축되도록 환경을 구성해본다. (목표)


-----------------------------------
# Python Basic Study

## 1. 모듈의 개념
+ main.py와 같은 py 파일 하나를 모듈이라고 한다. 일종의 네임 스페이스라고 우선 이해하면 된다.

## 2. Python Class의 생성
+ class 키워드를 사용해 class를 정의할 수 있다.
```
class MyApp(BaseClass):
    def __init__(self):
        pass
```

## 3. module의 import
+ from aaa import something 하면 aaa에 있는 something은 aaa.something 이 아닌 something으로 사용할 수 있다.
+ from없이 import만으로 가져올 경우 -> OuterPy.outer_value로 접근해야한다.
+ from OuterPy import * 로 가져올 경우 -> 이름만으로 접근 가능. 
+ from OuterPy i,port outer_value as Out_py 와 같은 식으로 외부 py 내의 특정 객체를 원하는 특정 이름으로 매핑 가능 
+ .으로 경로 표기를 해야하기에 모듈을 참조할 때 .py 확장자는 쓰지 않는다.
```
from OuterPy import OutString as Out_Py

class App():
    def on_pring(self):
        print(Out_Py)

if __name__ == '__main__':
    app = App()
    app.on_pring()
```

## 4. Double Underscore
+ 흔히 ```__``` 로 시작해서 ```__```로 끝나는 이름들은 보통 시스템에서 정의된 키워드 들이다.
+ 별도 항목없이 바로 설명할 만큼 자주보고 깊게 안들어가는 것들은 아래와 같다.
    * ```__init__(self)``` => 객체의 생성자 속성. 사실상 컨스트럭터 그 자체.
    * ```__name__``` => 모듈의 프로세스명. 흔히 해당 모듈이 main으로 돌고있는지를 검사하기위해 참조한다.
    * ```__del__(self)``` => 객체가 파괴되기 전에 호출된다.
> **_NOTE:_**  Single Underscore(```_```)도 변수 명으로서 의미가 있다.
```
값을 무시하고 싶은 경우 사용되는 암묵적인 변수명이 single underscore이다.
예시로, python에선 함수에서 두 개의 값을 반환하기도 한다.
그 중 현재 필요 없는 값이 있으면 _, value = return_two_val() 와 같이 첫 값을 안받겠다는걸 표시하곤 한다.
혹은 5번 반복되어야하는 for문에서 정작 순서 i값은 의미가 없는 경우 i _로 쓴다거나.
암묵적 룰은 강요되는건 아니지만 지키면 가독성이 증가하고 작업속도가 증가한다.
```

## 5. self

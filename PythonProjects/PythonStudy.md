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

### 1. 모듈의 개념
+ main.py와 같은 py 파일 하나를 모듈이라고 한다. 
+ 일종의 C의 네임 스페이스(파이썬의 네임스페이스는 아예 다른 개념이다.), 그러나 자체적으로 실행 가능한 독립된 개체라고 우선 이해하면 된다.

### 2. Python Class의 생성
+ class 키워드를 사용해 class를 정의할 수 있다.
```
class MyApp(BaseClass):
    def __init__(self):
        pass
```

### 3. module의 import
+ ```from aaa import something``` 하면 ```aaa```에 있는 ```something```은 ```aaa.something``` 이 아닌 ```something```으로 사용할 수 있다.
   * ```from```없이 ```import```만으로 가져올 경우 -> ```OuterPy.outer_value```로 접근해야한다.
   * ```from OuterPy import *``` 로 가져올 경우 -> 이름만으로 접근 가능. 
+ ```from OuterPy import outer_value as Out_py``` 와 같은 식으로 외부 py 내의 특정 객체를 원하는 특정 이름으로 매핑 가능 
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

### 4. Double Underscore
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

### 5. self
+ pythone에서 현재 작업을 수행중인 인스턴스가 자기 자신을 호출하기 위해 사용하는 키워드.
+ python 내의 method들은 method 실행중인 인스턴스를 참조하기 위해 반드시 첫 인자로 self를 넘겨받는다.
+ self는 memory상에서 인스턴스가 바인딩된 장소(id)를 들고있다.
+ 이 instance와 class의 차이에 대해서는 ```__metaclass__```를 다룰 때 자세히 이야기한다.


### 6. namespace
+ Python의 namespace는 변수가 객체를 바인딩할 때 그 둘 사이의 관계를 저장하고 있는 공간을 의미한다.
+ 가령 ```value1 = 2```라면 value1 이라는 이름은 2라는 값에 매핑되어 있는 것이고 이를 들고있는 namespace가 있는 것이다. 여기서 ```value2 = 2```, ```value3 = 2``` 등을 지정하면 value2, value3등도 namespace를 통해 value1과 같은 곳(2)을 참조하게 된다.
+ 각 객체에는 ```__dict__``` 속성이 있다. 이를 참조해 해당 객체의 namespace 목록을 볼 수 있다.
+ 여기서 두 가지 개념이 추가로 등장한다.
   * Class 네임스페이스
   ```Python에선 Class의 정의 자체도 하나의 네임스페이스 공간을 가진다. 즉 정의도 하나의 객체이다.```
   * 인스턴스 네임스페이스
   ```Class를 이용해 생성된 독립된 객체인 인스턴스의 네임스페이스를 의미한다. 현재 생성되어 사용되고 있는 객체를 의미한다.```
   * 위 두 개념을 확인하기 위해선 아래와 같은 코드를 수행해보면 된다.
   ```
   class ValueTest:
       valueinclass = 8

       def __init__(self):
           self.valueininstance = 3

    if __name__ == "__main__":
        a = ValueTest()
        
        #아래 라인의 실행결과로는 ValueTest Class의 클래스 네임스페이스 내의 값들이 나온다.
        #class에서 지정해준 valueinclass의 이름 및 클래스가 가지는 기본적 네임스페이스들은 여기서 확인된다.
        print(ValueTest.__dict__)
        
        #아래 라인의 실행결과로는 ValueTest Class의 인스턴스인 a의 네임스페이스 내의 값들이 나온다.
        #__init__ 수행 시 생성되는 인스턴스의 self에 추가해준 valueininstance의 이름은 여기서 확인된다.
        print(a.__dict__)
   ```
   
### 7. self.value
+ ```self.value = 3``` 과 같은 방법으로 객체 내의 맴버 변수를 생성한다.
+ 객체의 맴버 변수는 생성될 때가 아니더라도 언제든지 추가가 가능하다.
+ 인스턴스 생성 이후에 맴버 변수를 추가하는 예제이다.
```
a = ValueTest()
a.newValue = 15
print(a.newValue)
```
+ 당연하지만 Class의 정의 또한 하나의 객체이므로 아래와 같은 일도 가능하다.
```
ValueTest.newValue = 9
a = ValueTest()
print(a.newValue)
```
+ 위와 같은 자유로운 맴버값 추가때문에 Pycharm등의 IDE는 변수의 맴버 참조 시 없는 이름을 참조해도 에러를 표시하지 않는다.


### 8. if 내에서 정의한 값은 어디 있을까?
+ if 내에서 정의한 값에 대해 햇갈리는 부분. 7에서 다룬 특성으로 인해 if 내의 값을 밖에서 참조하는게 가능하다.
+ 아래의 코드를 실행해보면 정상적으로 ValueInIf값이 출력된다.
```
if __name__ == "__main__":
    if True:
        ValueInIf = 9

    print(ValueInIf)
```
+ if를 실행하며 ValueInIf가 현재 실행 중인 모듈(```__name__ == "__main__"```으로 이름을 받아오던)에 네임스페이스로 등록이 되었기 때문이다.
+ 비슷한 코드를 어디서 실행시켜도 동작하며 다만 실행 중인 프로세스 자체에 등록되는 네임스페이스이기에 프로세스 내 어느 객체의 ```__dict__```를 찍어봐도 이를 확인할 수는 없다.
+ 이게 성능에 영향을 줄까? 만약 영향을 준다면 이를 해결하는 방법은 무엇인가. 이 부분은 좀 더 조사해보자.


### 9. ```*args, **kwargs```
+ ```*args```는 함수에 가변 개수 인자를 넘겨줄 때 사용한다. args라는 이름은 관례적 이름으로 실질적으론 ```*```이 이 의미를 가진다.
+ dict 타입(네임스페이스)의 적용으로 ```*args```로 넘겨받은 값은 저마다 타입이 다를 수 있다. 
```
def printarg(*args):
    print(args[0])

if __name__ == "__main__":
    newValues = [1, 2, 3]

    printarg(*newValues)
    printarg(7, 6, 4)
```
+ ```**kargs```는 함수에 가변 개수 인자를 dict타입으로 넘겨줄 때 사용한다. 즉, Key값과 값을 함께 넘겨줄 때 사용한다.
+ kwargs라는 이름은 관례적 이름으로 실질적으론 ```**```이 이 의미를 가진다.
```
def printkwargs(**kwargs):
    for key, value in kwargs.items():
        print("{key} = {value}".format(key=key, value=value))


if __name__ == "__main__":
    newdict = {
        "Key1": 5,
        "Key2": 3
    }

    printkwargs(**newdict)
    printkwargs(Key1=9, Key2=10)
```
+ 직접 쓰기도 꽤 쓰겠지만 모듈 내 클래스들을 상속해 쓰다보면 상당히 자주 보인다.

### 10. 상속
+ Python의 상속의 문법은 아래와 같다.
```
class BaseClass:
    pass
class ChildClass(BaseClass): #여기가 상속을 하는 부분. ()안에 Base 클래스를 적어준다.
    pass

if __name__ == "__main__":
    a = ChildClass()
    print(isinstance(a, BaseClass))
    print(ChildClass.__mro__) #이건 class 네임스페이스 내에 있는 상속 관계를 보여주는 네임스페이스.
```
+ 부모의 참조는 super()를 통해 함께 생성된 부모의 인스턴스 네임스페이스를 참조한다. super로는 부모의 클래스 네임스페이스를 참조할 수 있다.
```
class BaseClass:
    pass
class ChildClass(BaseClass):
    def __init__(self):
        super().__init__()
    def printSuper(self):
        print(super().__dict__)
        print(super.__dict__)

if __name__ == "__main__":
    a = ChildClass()
    a.printSuper()
```
+ Python은 다중 상속을 지원한다.
```
class BaseClass:
    pass
class OtherBaseClass:
    pass
class ChildClass(BaseClass, OtherBaseClass):
    pass

if __name__ == "__main__":

    a = ChildClass()
    print(isinstance(a, BaseClass))
    print(isinstance(a, OtherBaseClass))
```
+ 부모의 참조


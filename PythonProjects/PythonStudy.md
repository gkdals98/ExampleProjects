## Python

Python 3.0 이후의 환경을 기준으로 study한 내용의 정리.

> ### 링크
> * Python EXE를 만드는 법 - https://wikidocs.net/21952
> * 초보가자 구조를 참조하기 좋은 Python Project들 - https://github.com/MunGell/awesome-for-beginners#python 

### 시작하기 전에 준비할 
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
+ 일종의 C의 네임스페이스 비슷한 것(파이썬의 네임스페이스는 아예 다른 개념이다.), 그러나 자체적으로 실행 가능한 독립된 개체라고 우선 이해하면 된다.

### 2. Python Class의 정의 방법
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
+ 별도 항목으로 설명할 필요가 없을 정도로 쉽고 자주보는 것들은 아래와 같다.
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
+ 이 instance와 class의 차이에 대해서는 6에서 자세히 이야기한다.


### 6. namespace
+ Python의 namespace는 변수가 객체를 바인딩할 때 그 둘 사이의 관계를 저장하고 있는 공간을 의미한다.
+ 가령 ```value1 = 2```라면 value1 이라는 이름은 2라는 값에 매핑되어 있는 것이고 이를 들고있는 namespace가 있는 것이다. 여기서 ```value2 = 2```, ```value3 = 2``` 등을 지정하면 value2, value3등도 namespace를 통해 value1과 같은 곳(2)을 참조하게 된다.
+ 각 객체에는 ```__dict__``` 속성이 있다. 이를 참조해 해당 객체의 namespace 목록을 볼 수 있다.
+ 여기서 파이썬 고유의 두 가지 개념이 등장한다.
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


### 8. 추가적인 기본 문법
+ for문의 기본은 아래와 같은 형태
```for i in range(0, 15, 1)```
+ for문을 반복 가능 객체에서 사용할 경우 아래와 같은 형태
    * String, index등에서
    ```for c in "Hello"```
    * 키, value를 가지는 Dictionary 타입에서
    ```for key,value in Dictionary.items()```
    ```for value in Dictionary.values()```
    ```for key in Dictionary```
    ```for key in Dictionary.keys()```
+ while문의 기본적인 형태
```while i < 10```
+ 예외 처리
```
try : 
    z = 10/0
except ZeroDivisionError as e :
    print("ZeroDivision")
finally : 
    print("Good Bye")
```
+ Stack, Queue도 class로 재공
```s = Stack()```
```q = Queue()```

### 9. if 내에서 정의한 값은 어디 있을까?
+ if 내에서 정의한 값에 대해 햇갈리는 부분. 7에서 다룬 특성으로 인해 if 내의 값을 밖에서 참조하는게 가능하다.
+ 아래의 코드를 실행해보면 정상적으로 ValueInIf값이 출력된다.
```
if __name__ == "__main__":
    if True:
        ValueInIf = 9

    print(ValueInIf)
```
+ if를 실행하며 ValueInIf가 현재 실행 중인 모듈(```__name__ == "__main__"```으로 이름을 받아오던)에 네임스페이스로 등록이 되었기 때문이다.
+ 비슷한 코드를 어디서 실행시켜도 동작하며 다만 실행 중인 지점 자체에 등록되는 네임스페이스이기에 프로세스 내 어느 객체의 ```__dict__```를 찍어봐도 이를 확인할 수는 없다.
+ 이게 성능에 영향을 줄까? 만약 영향을 준다면 이를 해결하는 방법은 무엇인가. 이 부분은 좀 더 조사해보자.


### 10. ```*args, **kwargs```
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

### 11. 상속
+ Python의 상속의 문법은 아래와 같다.
```
class BaseClass:
    pass
class ChildClass(BaseClass): #여기가 상속을 하는 부분. ()안에 Base 클래스를 적어준다.
    pass

if __name__ == "__main__":
    a = ChildClass()
    print(isinstance(a, BaseClass))
    print(ChildClass.__mro__) #class 네임스페이스 내에 있는 상속 관계를 들고있는 네임스페이스. 쉽게 쓰자면 상속관계 확인하는 용도.
```
**여기서 부터는 분석이 좀 더 필요한 내용이다.**
+ super는 객체 부모의 인스턴스를 참조할 때 사용하는 built-in Method이다. (PyCharm 상에선 보라색으로 표시)
+ super()를 통해 함께 생성된 부모의 인스턴스 네임스페이스를 참조한다. 
```
class BaseClass:
    pass
class ChildClass(BaseClass):
    def __init__(self):
        super(ChildClass, self).__init__()
    def printSuper(self):
        print(super().__dict__)
        print(super.__dict__)

if __name__ == "__main__":
    a = ChildClass()
    a.printSuper()
```
+ ```super().__init__()```의 호출법이 왜 저런식인지에 대해서는 하기 링크를 참조한다.
    https://stackoverflow.com/questions/576169/understanding-python-super-with-init-methods
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
+ 일단 보이는 문제점은 다중 상속시 super()를 호출하면 첫 번째 부모만 호출이 가능하다는 점이다. Python은 다중 상속으로 인한 함수명 충돌 문제를 첫 번째 부모의 함수를 우선 호출하는 식으로 피했는데 아마도 super()가 첫 번째 부모만을 리턴하는 식으로 이를 구현한 것으로 보인다.
+ 따라서 부모 클래스가 둘 이상일 경우, ```super().__init__```은 첫 번째 부모만을 의미하기에 아래와 같은 방법으로 초기화를 진행한다.
```
class BaseClass:
    pass
class OtherBaseClass:
    pass
class ChildClass(BaseClass, OtherBaseClass):
    def __init__(self):
        BaseClass.__init__(self)
        OtherBaseClass.__init__(self)
if __name__ == "__main__":

    a = ChildClass()
    print(isinstance(a, BaseClass))
    print(isinstance(a, OtherBaseClass))
```
+ 이 부분에서 주목할 게 ```BaseClass.__init__(self)```가 멀쩡히 동작하고있다.. 부모의 클레스 네임스페이스인 BaseClass로 자식 인스턴스와 함께 생성된 부모 **인스턴스 네임스페이스**를 참조하고 있다는 뜻인데... 이 부분은 아직 분석이 덜 된 상태.
+ 이에 관해 조금 다른 의문점을 또 추가하자면 아래의 예제가 있다.
https://stackoverflow.com/questions/9575409/calling-parent-class-init-with-multiple-inheritance-whats-the-right-way

### 12. builtin 모듈
+ 위에서 언급한 builtin이란. ```__buildins__```에 정의되어있는 기본 내장 클래스들을 의미한다. type, len, sum등이 대표적이다.

### 13. classmethod, staticmethod
+ python에서 정적 method를 정의하기 위한 두 가지 방법이다. 다만 클래스의 네임스페이스와 인스턴스 네임스페이스가 엄연히 구분되는 파이썬인 만큼 두 가지 방법이 존재한다.
##### @staticmethod
+ **정의된 클래스의 네임스페이스**를 기준으로 메서드가 고정된다.
+ 정의된 클래스를 상속받는 클래스가 있어도 해당 메서드는 변하지 않는다.
##### @classmethod
+ **메서드를 수행하는 클래스의 네임스페이스**를 기준으로 메서드가 실행된다.
+ 만약 정의된 클래스를 상속받는 클래스에서 메서드에 관여하는 인자가 변경되었다면 새롭게 정의된 클래스를 기준으로 메서드가 실행된다.

+ 이를 확인하기 위해 아래의 예제를 참조한다. - https://hamait.tistory.com/635
```
class Date:
    word = "date : "
    def __init__(self, date):
        self.date = self.word + date

    @staticmethod
    def stnow():
        return Date("today")

    @classmethod
        def clnow(cls):
        return cls("today")

    def show(self):
        print(self.date)

class KoreanDate(Date):
    word = "날짜 : "
    
if __name__ == "__main__":
    a = Date.stnow()
    a.show()

    b = KoreanDate.stnow()
    b.show()
    c = KoreanDate.clnow()
    c.show()
```

### 14. 연산자 오버로딩
+ ```+, -, +=, -=```등의 연산자도 ```__add__(self, other)```와 같은 형식으로 상위 클래스인 operator상에 정의된 것이다.
+ 클래스 내에서 ```__add__``` 등 오버로딩을 하면 해당 클래스 객체의 연산 방식을 재정의할 수 있다.

### 15. 추상화 메서드, 추상화 클래스
+ python에선 추상화를 구현하기 위해 abc라는 모듈을 제공한다. 작명이 왜 이 모양일까. builtin은 아니며 import 해주어야 한다.
+ 추상화 클래스의 구현방법은 아래와 같다.
```
import abc
class AbstClass(metaclass = abc.ABCMeta):
    @abc.abstractmethod
    def abcfunc(self):
        pass
```
+ 위와 같이 metaclass를 ABCMeta로 지정하고 메서드에 abstractmethod 데코레이터를 달아주면 해당 클래스를 상속받는 클래스들은 abstract 데코레이터가 달린 메서드를 구현하지 않을 시 에러를 표시하게 된다.

### 16. Python Decorator
##### Decorator function
+ Decorator란, 디자인 패턴 중 데코레이터 패턴을 간편하게 구현하도록 도와주는 파이썬의 기능이다. 프로세스 수행중 유동적으로 생성되는 데코레이트를 만드는게 아닌, 로그를 남긴다거나, 특정 형태의 메소드에 반복적으로 들어가는 기능의 구현 등을 편하게 하기 위한 기능이다.
+ ```@```문자를 통해 데코레이터를 호출 가능하다. 우선 Decorator 함수의 예시이다.
```
def printStartEnd(func):
    def decorateStartEnd():
        print("Start")
        func()
        print("End")
    return decorateStartEnd
```
+ 위와 같이 인자로 function을 받고, return으로 데코레이트 메서드를 반환하는 function을 정의한다.
+ 호출은 아래와 같이 한다.
```
@printStartEnd
def decoratedfunc():
    print("Executed")
```
+ 위와 같이 @로 네임스페이스를 호출하면 파이썬은 자동으로 아래의 동작을 한다.
    * 1. @를 통해 데코레이터로 지정된 객체에 Decorator 대상이 되는 메서드를 인자로 주고 결과 객체를 받는다.
    * 2. 결과 객체를 실행.
+ 구체적으로 코드로 묘사해보자면 아래와 같다.
```
result = printStartEnd(decoratedfunc)
result()
```
+ 즉 func을 인자로 받아 return으로 function를 돌려주는 형태의 퍼스트 클래스 함수로 데코레이터를 정의할 수 있으며 @로 사용할 수 있다.
    
##### call - https://jupiny.com/2016/09/25/decorator-class/
+ ```def __call__(self, *args, **kwargs):```은 정의할 시 class로 생성된 instance를 함수처럼 실행가능하게 해준다.
+ 원리적으론 아래와 같은게 가능하다. 흔히 접하는 call중 하나가 type의 call로 ```type(instance)```를 수행해 인스턴스의 타입을 알아볼 수 있다.
```
class CallableClass:
    def __init__(self, value):
        self.value = value
    def __call__(self, *args, **kwargs):
        print(self.value)

if __name__ == "__main__" :
    c = CallableClass(5)
    c()
    CallableClass(10)()
```

##### Decorator class
+ ```__call__```을 사용해 class 타입의 데코레이터를 정의할 수 있다. 
+ 인스턴스를 생성할 때 function을 받고, ```__call__```을 정의해 실행 가능한 객체로 만들어 실행되도록 하면 된다.
+ function 방식보다는 덜 간결하지만 더 복잡한 작업을 수행할 수 있다.
```
import time

class Timer():
    def __init__(self, function):
        self.function = function

    def __call__(self, *args, **kwargs):
        start_time = time.time()
        result = self.function(*args, **kwargs)
        end_time = time.time()
        print("실행시간은 {time}초입니다.".format(time=end_time-start_time))
        return result

@Timer
def print_hello(name):
    print("hello, ", name)

print_hello('python')
```
+ 여기서 ```__call__```은 args와 kwargs로 데코레이트되는 함수의 파라미터를 넘겨받는다. 위의 예시에선 print_hello의 인자로 넘겨준 'python'이 args를 통해 넘어온다.
+ function 타입과 실행되는 동작 원리도 똑같다.
```
result = Timer(print_hello) #print_hello를 인자로 Timer 객체 생성
result()                    #생성된 객체의 실행
```

### 17. MetaClass - https://code.tutsplus.com/ko/tutorials/quick-tip-what-is-a-metaclass-in-python--cms-26016
+ **class 네임스페이스**를 만드는 객체.
+ 일반적인 클래스는 metaclass를 지정해주지 않으면 기본적으로 type을 메타클래스로 삼는다. 즉 class 네임스페이스는 type을 통해 생성된다.
+ 구체적으로 type은 아래와 같은 동작을 수행하는 것이 가능하다.
```
newInst = type('myClass', (), {
    'a': True,
    'Hello': print("Hello")
})

newInst.Hello
```
+ metaclass는 아래와 같은 방법으로 지정해줄 수 있다.
```
class MetaTest(metaclass = BaseMeta) 
```
+ 구체적으로 metaclass를 통해 수행되는 동작은 아래와 같다고 한다.
```
myClass = myMetaClass.__new__(myMetaClass, name, bases, dictionary)
myMetaClass.__init__(myClass, name, bases, dictionary)
```

### 18. 위 까지의 지식과 MetaClass를 이용한 Singletone 구현법
```
class Singleton(type):   #일단 type객체를 상속받아 MetaClass 기능을 가져옴.
    _instances = {}       #빈 배열인 _instances를 생성.
    def __call__(cls, *args, **kwargs):		#call의 정의(지금은 metaclass=Singleton으로 call 됨.)
        if cls not in cls._instances:		#cls._instances안에 없는 cls라면
            cls._instances[cls] = super(Singleton, cls).__call__(*args, **kwargs)	#super는 type이고 type에 Singleton과 cls를 넣음.
        return cls._instances[cls]		#최종적으로 해당 Class를 Call로 불렸다면 cls instance를 return하게 된다.

상속은
class MySingleton(base, metaclass=Singleton):
    def foo(self):
        pass

또는
@Singleton
class MySingleton2(base):
    def foo(self):
       pass

사용은
MySingleton().foo() 
```

### 19. os 모듈
+  os.~ 와 같은 식으로 os상에서 특정 프로세스를 실행시키는게 가능.

### 20. threading 모듈 - https://soooprmx.com/archives/8834
+ 파이썬은 모듈기반으로 '특정 시점에 실행중인 파이썬 코드는 하나뿐이다.'라는 인터프리터 락킹이란 개념이 있다. 따라서 코드를 병렬로 분할해서 실행하기 위해서는 인터리빙이라는 방식이 적용되어야 한다. 다중 CPU 환경에서 병렬 실행을 원하는 경우엔 multiprocessing 모듈을 사용한다. 일단 threading은 아래와 같이 사용한다.
```
import threading

def sum(low, high):
    total = 0;
    for i in range(low, high):
        total += i
    print("Subthread", total)

t = threading.Thread(target=sum, args(1, 10000))
t.start()
```
```
import threading, requests, time

class HtmlGetter(threading.Thread):
    def __init(self, url):
        threading.Thread.__init__(self)
        self.url = url
    def run(self):
        resp = requests.get(self.url)
        time.sleep(1)
        print(self.url, len(resp.text), 'chars')

t = HtemlGetter(''http://google.com")
t.deamon = True
t.start()
```
+ deamon 속성이 중요하다. deamon이 True인 경우, 해당 서브 스레드는 메인 스레드가 종료될 때 무조건 함께 종료된다. 반면 false인 경우는 main이 종료되더라도 자신의 작업을 끝까지 수행한다. 기본이 False이기에 상황에 맞게 해당 옵션을 True로 바꿔주는 것이 중요하다.


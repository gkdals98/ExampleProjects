## Python

> ### 링크
> * Python EXE를 만드는 법 - https://wikidocs.net/21952
> * 초보가자 구조를 참조하기 좋은 Python Project들 - https://github.com/MunGell/awesome-for-beginners#python 

### 메모
* Pycharm cmd 라인 띄우기 - Alt F12
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
main.py와 같은 py 파일 하나를 모듈이라고 한다.
일종의 네임 스페이스라고 우선 이해하면 된다.

## 2. Python Class의 생성
class 키워드를 사용해 class를 정의할 수 있다.




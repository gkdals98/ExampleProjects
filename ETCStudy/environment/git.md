# Git 기본 사용법과 개념
+ https://git-scm.com/book/ko/v2/Git-브랜치-브랜치와-Merge-의-기초#_basic_merging
+ 위 가이드의 내용을 이해한대로 정리한다.
+ 이해한 다음에 다시 정독해야할 이클립스 상의 git 사용법 - https://gasaesososo.tistory.com/13

## Git 핵심 개념
+ Git은 프로젝트의 스냅샷을 저장한다. 즉 각 파일별 히스토리를 저장하는 것이 아닌 프로젝트의 특정 시점 상태를 저장하는 것이다. 
+ 다시 설명하자면, cvs는 파일별 diff를 통해 버전관리가 이루어진다. Git은 파일단위가 아닌 Commit할 당시의 상황을 저장한다.

### 1. 파일의 상태
+ 로컬 Git에서 관리되는 동안 파일은 아래의 세 가지 상태로 나뉜다.
    * Committed : 데이터가 로컬 데이터베이스에 안전하게 저장됐음을 의미
    * Modified : 수정한 파일이 아직 로컬 데이터베이스에 커밋되지 않았음을 의미
    * Staged : 수정한 파일을 곧 커밋할 것이라고 표시한 상태 (Added)
    
+ 로컬 Git이 관리하지 않는 File은 아래와 같은 상태 하나 뿐이다.
    * Untracked : git 디렉토리 내에 있으나 git이 관리하지 않는 파일.

### 2. Clone, Checkout
+ Clone : Clone은 Remote 저장소를 Local에 Git 디렉토리 형태로 복사해오는 것을 말한다. Git 디렉토리, 또는 Git 저장소는 모두 Git에 의해 관리되는 디렉토리를 칭한다.
+ Checkout : Local, 혹은 Remote Git에서 특정 커밋 또는 브랜치를 가져오는 것을 말한다. 이를 통해 만들어진 작업 공간을 Working Tree라고 한다. 
+ 즉, 일반적으로 Clone은 Repository를 복사해 가져오며 초기화하는 행위를 뜻하며, Checkout은 작업중인 Git 저장소에서 특정 커밋 또는 브랜치로 Working Tree를 옮기는(동시에 코드도 해당 커밋 또는 브랜치의 상태로 업데이트하는) 행위를 뜻한다.
+ git checkout -- <file> 을 수행하면 해당 파일을 마지막 commit 상태로 되돌린다.

#### 3. Git Config
+ Git은 하기와 같은 Git Config파일들의 설정으로 Git 디렉토리를 관리한다.
    * /etc/gitconfig : 시스템의 모든 사용자와 모든 저장소에 적용되는 설정. git config --system을 통해 관리된다.
    * ~/.gitconfig 또는 ~/.config/git/config : 특정 사용자에게만 적용되는 설정. git config --global에서 적용한다.
    * .git/config : 특정 git 디렉토리에만 적용되는 설정. git config --local에서 적용한다. Default 옵션이다.
+ 위 옵션들은 지협적인 옵션부터 광범위한 옵션 순으로 우선순위가 적용된다.
+ git config --list 명령으로 설정내용을 전부 볼 수 있다.

#### 4. Staging Area
+ Staging Area는 구축죽인 스냅샷을 의미한다.
+ add를 통해 새로운 파일 혹은 수정된 파일의 상태를 추가하면 Staging Area상에 있는 스냅샷에 반영된다.
+ commit 수행 시 Staging Area상에 있는 스냅샷이 올라가게 된다.
+ Commit이 수행될 시 Staging Area의 스냅샷은 메타데이터(저자, 커밋 메시지 등) 와 이전 커밋에 대한 포인터 등을 포함하는 커밋 개체로 저장된다. 이는 아래에서 좀 더 자세히 다룬다.

#### 5. Git Objects
+ Git은 내부적으로 commit, tree, blob, tag 총 네 종류의 Object를 관리한다.
+ Object 파일들은 .git/objects 내부에 존재한다.
+ Object 파일의 이름은 파일 이름에서 40자리 해시값을 추출해 결정된다. 이는 blob과 tree를 통해 리눅스의 inode-dentry와 비슷하게 관리된다.(찾아보자) 
+ git은 수많은 Object를 파일의 앞 두 글자를 딴 디렉토리에 보관해 분산관리한다.
    * blob : 컨텐츠의 용량을 표시하는 사이즈, 이름과 형식을 제외한 파일의 모든 것을 담는 컨텐츠로 구성 
    * tree : tree의 용량을 표시하는 사이즈, 하위 디렉토리의 트리객체를 재귀적으로 참조할 수 있는 tree, 한 디렉토리에 있는 모든 객체를 담는 blob을 가지고 있음.
    * commit : 작성자, 커밋 실행자, 커밋 날짜, 로그 메시지 , tree 객체(해당 커밋에서의 dir/file 상태를 알 수 있음)를 가지고 있다.
    * tag : 객체 종류, 태그 이름, tagger, 태그메시지, PGP 서명 정보를 담고있다.
+ Object 디렉토리 내에서 git cat-file 을 통해 해당 Object의 정보를 확인할 수 있다.

#### 5. Branch
+ **Git에서 Branch는 일종의 포인터이다.** Git에서 특정 Commit을 가르키기 위한 포인터가 브랜치라고 생각하면 된다. 브랜치 자체로는 아무것도 아니다.
+ master또한 init시 최초생성되는 브랜치라는 점을 제외하면 다른 브랜치와 다를 것이 전혀 없는 암묵적 이름일 뿐이다.
+ **HEAD** 는 현재 작업중인 Branch를 가리키는 포인터이다.
+ 아직 커밋하지 않은 파일이 Checkout할 Branch와 충돌할 경우, 해당 브랜치로의 checkout은 불가능핟.

#### 6. Fast forward
+ 아래와 같은 Merge 상황을 Fast forward라고 칭한다.
``` 
iss43 이라는 커밋이 기존의 master branch가 가리키고 있는 커밋이였다 가정한다. 
여기에서 hotfix 브랜치를 땄고 hotfix 브랜치에서 추가 작업이 이루어졌다. 
이후 hotfix를 커밋해 iss 44 라는 커밋이 생겼다. 
여기서 hotfix의 내용을 master 브랜치에 반영해도 상관 없다는 결론이 난 경우, 
hotfix는 master에서 부터 작업된 브랜치이기에 별도의 작업이 필요없이 master 브랜치가 가리키는 커밋을 iss44로 옮기면 끝이다.
이 경우를 Fast forward라고 한다. 
```

#### 7. 3-way merge
+ 아래와 같은 Merge 상황을 3-Way Merge라고 칭한다.
```
커밋 iss 44로부터 나뉘어 각각 작업된 브랜치 fix1과 fix2가 있다.
각자 작업이 완료되어 fix1은 커밋 iss 44-1,  fix2는 커밋 iss44-2를 가리키게 되었고 
최종적으로 둘을 Merge해 최종 완성본 commit인 iss45와 이후의 작업의 시작점이 될 브랜치 master를 만들려 한다 치자.
이 경우 iss 44-1과 iss44-2는 별도로 작업된 브랜치이기에 서로를 기준으로 Merge할 수 없다.
이 때 둘이 뻗어나온 공통의 조상인 iss44에 이 둘을 Merge해 iss45를 만드는 것을 3way-merge라고 한다.
```
+ 3-way merge는 다른 작업 결과물들을 합치는 merge인 만큼, 충돌이 자주 발생한다.
+ 충돌 시 git status 명령을 통해 unmerged 상태인 파일을 확인할 수 있다. 이는 git에서 지정된 에디터를 사용해 수정한 뒤 git add를 다시 하고 merge하는 식으로 해결해야한다...
+ 보다 자세한 내용은 https://git-scm.com/book/ko/v2/Git-%EB%B8%8C%EB%9E%9C%EC%B9%98-%EB%B8%8C%EB%9E%9C%EC%B9%98%EC%99%80-Merge-%EC%9D%98-%EA%B8%B0%EC%B4%88 를 참조하자. 요약해선 소용 없고 정독하면서 문제를 해결해야할 상황을 보인다.

#### 8. work flow
+ 참조할 workflow 모델들 - https://git-scm.com/book/ko/v2/Git-%EB%B8%8C%EB%9E%9C%EC%B9%98-%EB%B8%8C%EB%9E%9C%EC%B9%98-%EC%9B%8C%ED%81%AC%ED%94%8C%EB%A1%9C

#### 9. Remote Refs
+ Remote Refs : 리모트 저장소에 있는 포인트.... 일단 공식문서에 설명된 것만 놓고보면 브랜치와 비슷한 개념.
+ Remote Tracking Branch : 리모트 브랜치를 추적하는 브랜치. 로컬에 있지만 임의로 조작 불가능하며 remote에 연결될 때마다 자동으로 갱신된다. <remote name>/<branch name> 와 같은 형식으로 표시된다. origin/master가 이것.
+ 

#### . Merge vs Rebase
+ Merge는 두 개의 Branch가 서로 충돌하지 않을 경우 두 Branch의 수정사항들을 합치는 방식이다.
+ Rebase는 두 브랜치 fix1, fix2에 대해 fix2의 변경사항을 patch 형식으로 fix1에 적용하는 방식을 말한다.


## Git 명령어 위주 정리
#### 1. git 디렉토리 생성
+ Git에서 Git 디렉토리를 만드는 방법은 아래의 두 가지가 있다.
    * ```git init``` : 기존에 있던 디렉토리를 git 디렉토리로 만드는 명령어이다.
    * ```git clone <url>``` : url의 git 저장소를 clone해온다. clone으로 local repository가 생성되자마자 git 디렉토리로 취급된다.
+ ```git clone <url> <directory name>``` : directory name으로 폴더를 만들어 url의 repository를 가져온다.
+ git clone을 옵션없이 수행할 경우, 저장소 이름은 origin이 된다.

#### 2. 파일 상태 확인 (git status) 
+ git status : 파일의 상태(위에서 말한 3단계 상태 + Untrackted)를 확인할 수 있다.

#### 3. 파일의 변경 내용 확인 (git diff)
+ Staged와 Unstaged의 차이점을 보기 위해 사용한다.
+ ```git diff <filename>``` : filename에 해당하는 파일의 Staged와 Unstaged의 차이점을 본다. 

#### 4. git add
+ Untracked인 상태, 혹은 Modified 상태인 파일의 현 상태를 스냅샷에 추가한다.
+ ```git add <filename>``` : filename에 해당하는 파일을 스냅샷에 추가한다.
+ -a : a 옵션을 통해 커밋하기 전, 모든 파일을 자동으로 Staging area에 추가한다.

#### 5. git rm
+ git 레파지토리상에 파일의 삭제상태를 반영하기 위해선 git rm 명령어를 통해 파일을 삭제해야한다.
+ git rm을 거치지 않고 그냥 삭제한 파일은 git 상에서는 Unstaged, 즉 modified 상태로 인식된다.
+ 만약 staged 상태인 파일을 삭제하려 한다면 -f 옵션을 주어 강제로 삭제하도록 해야한다.
+ --cached : 특정 파일을 삭제하지 말고 git의 Staging area에서만 지운다.
+ 정규식을 사용해 여러파일을 한 번에 삭제할 수도 있다.

#### 6. git mv
+ git mv는 특정 파일의 이름을 바꿀 때 Stage사용한다.
+ ```git mv <file.oldname> <file.newname>``` 으로 사용한다.
+ git 관점에선 rm 후 add와 같은 동작이다.

#### 7. git commit
+ 스냅샷을 'Local' Git에 저장한다. commit과 push의 차이는 로컬 git이냐, remote git이냐의 차이이다.
+ 파일들을 Git에 저장하기 전 Status 명령의 결과를 보여준다.
+ ```--amend``` 옵션을 사용하면 기존 커밋에 수정사항을 덧붙일 수 있다.
+ ```-a``` (auto?)옵션은 변경된 파일(삭제와 수정)을 검출해 자동으로 Staging area에 추가한 뒤 commit하는 옵션이다. 즉 자동 add 및 rm인 셈.

#### 8. git log
+ git log를 통해 commit 히스토리를 조회할 수 있다.
+ -p 옵션이 내 입장에선 가장 중요한데, 각 커밋의 diff 결과를 보여주는 옵션이다.
+ -<number> 옵션은 숫자만큼 최근 커밋만을 출력하도록 한다. 예로 git log -2 이면 최신 2개 커밋의 log만 출력한다.
+ --stat 옵션은 히스토리에 대한 통계를 보여준다. 어떤 파일이 수정됐으며 얼마나 많은 파일이 변경되고 얼마나 많은 라인이 추가, 삭제됐는지를 보여준다.
+ --pretty=format을 지정하면 히스토리 출력의 포멧을 지정할 수도 있다. 자세한건 필요하다면 알아보자.
+ --since=2.weeks 등 since 옵션은 출력되는 기간을 제한한다.
+ 기타 옵션들에 대해서는 추후 필요할 시 추가조사한다.

#### 9. git reset
+ file의 상태를 되돌리는 명령어
+ ```git reset HEAD <file>``` : file을 현재 HEAD상태인 Branch 내용으로 완전히 덮어쓴다.

#### 10. git remote
+ remote만으론 연결되어있는 원격 저장소를 확인하는 명령어이다. remote 뒤에 명령어를 더 붙이는 식으로 remote branch에 대한 작업을 수행한다.
+ ```git remote add -f <connection name> <remote git url>```과 같은 방법으로 원격 저장소를 추가로 연결할 수 있다. 여기서 name은 로컬 사용자가 정하는 임의의 연결명이다.
+ ```git remote show <connection name>``` 은 저장소의 디테일을 확인할 수 있는 명령어이다.
+ ```git remote rename <connection name.old> <connection name.new>``` 는 저장소의 이름을 변경하는 명령어이다.
+ ```git remote remove <connection name>``` 은 해당 저장소를 삭제한다.
+ ```git ls-remote <connection name>``` 은 해당 연결의 모든 Refs를 보여준다.

#### 11. git fetch
+ remote상의 데이터를 모두 가져오는 명령어.
+ 이미 작업중이던 저장소에서 수행하면 수정된 부분들을 가져오되, Merge해주지는 않는다. Merge까지 포함하는게 git pull.
+ ```git fetch <remote name>``` : **저장소 정보를 remote에 동기화하기 위해 사용하는 명령어.**

#### 12. git tag
+ 커밋에 이름을 붙여 관리하기 위함

#### 13. git alias
```
$ git config --global alias.co checkout
$ git config --global alias.br branch
$ git config --global alias.ci commit
$ git config --global alias.st status
```
+ 위와 같이 특정 명령에 대한 alias를 걸 수 있다. 위 예시대로 입력하면 git co, git ci라고만 입력해도 checkout, commit을 할 수 있다.

#### 14. git branch 및 branch를 위한 명령어 옵션들
+ ```git branch``` 를 그냥 실행하면 현재 브랜치의 목록이 나온다. 이름 앞에 *이 붙어있는 branch가 현재 checkout된 branch이다. -v 옵션을 주면 마지막 커밋 메시지도 함께 보여준다.
+ ```git branch <branch name>``` 으로 새 branch 생성이 가능하다. 새로 만든 브랜치는 지금 작업하고 있던 마지막 커밋을 가리킨다. 이 명령어는 새 Branch를 만들 뿐, HEAD를 변경하진 않는다.
+ ```git log --online --decorate``` 명령어를 사용하면 HEAD 브랜치가 어떤 커밋을 가리키는지 알 수 있다.
+ ```git checkout <branch name>```으로 다른 브랜치로 옮길 수 있다.
+ ```git log --online --decorate --graph --all``` 은 현재 HEAD 브랜치가 가르키고있는 커밋의 히스토리를 자세히 보여준다.
+ ```git checkout -b <commit name>``` 으로 해당 커밋을 채크아웃하며 브랜치까지 새로 생성할 수 있다.
+ ```git branch -d <branch name>``` 으로 쓸모없게된 브랜치를 삭제할 수 있다. **아직 Merge되지 않은 내용을 담고있는 브랜치라면 삭제할 수 없다.** -d 대신 -D 옵션을 주면 강제로 삭제하지만 위험하니 가급적 자제하자.
+ ```git branch --merged``` 또는 ```git branch --unmerged``` 명령어로 현재 checkout 상태인 브랜치에 merge된 branch 또는 merge되지 않은 branch를 확인 가능하다.
+ 

#### 15. git checkout
+ **구체적으론 브랜치를 옮기기 위한 명령어이다.**
+ 브랜치를 옮기면서 내용을 받아오기때문에 해당 branch를 받아오는 명령어처럼도 동작할 뿐이다.
+ ```git checkout <branch name>``` : HEAD를 특정 Branch로 옮기고 Branch가 가리키는 commit을 참조해 코드를 받아온다.

#### 16. git push
+ ```git push <remote name> <branch>:<remote branch> ``` : 해당 remote의 remote branch에 branch를 push한다. branch를 새로 전송하는 개념이기에 branch name은 로컬에서 명령어를 실행하는 시점에서 정할 수 있다.
+ 위 명령어로 전송된 branch는 나중엔 일반적인 Remote Tracking Branch들처럼 ```<remote name>/<remote branch>``` 로 참조할 수 있다.

#### 17. git rebase
+ ```git rebase <branch name>``` 명령어는 


##결론
+ 우리가 찾던 특정 디렉토리 Sync를 가장 안전하게 돌릴 방법
```git checkout origin/master -- *```
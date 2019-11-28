## 인벤토리
+ 인벤토리의 디자인이 상당히 중요하다.... Looting, SkillPool 조정, 소모템의 사용등을 전부 한 번에 때려박아야한다. 그것도 알기 쉬운 구조로.
+ 아마도 좌측이 Looting된 아이템 또는 캐릭터의 장비와 스킬 풀, 상점 또는 재조소(이하 상점) 등이 표시되는 영역, 우측이 전체 인벤토리인 구조로 구성돼야한다.

#### 인벤토리 활성화
+ 인벤토리는 유저가 인벤토리를 눌렀을 경우, 상점에 진입했을 경우 총 두 가지 경우에 활성화된다.
+ 상점에선 상인을 누르면 상점용 Inventory가, 인벤토리를 누르면 일반 Inventory가 뜬다. 팝업으로 출력해서 둘이 동시에 출력되는 사태를 막자.

#### 인벤토리 영역
+ 우측에서 현재 가방에 들어있는 아이템들을 확인할 수 있다.
+ 아이템의 정보는 툴팁으로 보여준다.
+ 아래엔 사용버튼, 버리기 버튼이 있다. 사용버튼은 상점에선 비활성화 시켜야한다. (v-if)
+ 사용을 누르고 대상을 누르면 적절한 효과가 발휘된다. 혹은 발휘되지 않는다. 발휘되지 않는 경우는 별도 팝업을 띄우기 보단 선택 대상의 태두리에 빨간 불이 들어오게.
+ 사용상태에서의 취소는 취소버튼 또는 우클릭을 통해 하자.

### 좌측 상호작용 영역
+ 상호작용 영역에 들어갈 수 있는 UI는 당장 떠오르는걸론 Looting, 캐릭터 창, 상점. 이후 떠오르는대로 추가.
+ Inventory를 그냥 열었다면.... 가급적 상호작용 영역은 표시가 안되도록 해야한다. CSS로 좀만 고생하면 금방 구현하지 싶음.
#### Lotting
+ 드랍된 아이템을 표시하는 UI.
+ 드랍된 아이템을 클릭하여 인벤토리로 옮길 수 있다.
+ 폭탄이 설치된 채로 드랍됐거나? 하는 식으로 아이템을 사용해서 빼가야하는 것도 있게 만들면 재밌을 듯. 대신 좋은거 드립니다.
#### 캐릭터 창
#### 상점 또는 제조소
## Game Controller 설계
* Game Controller란 이름에 맞게, 게임의 전체 플로우를 관장한다.
* 게임의 전체 플로우란 무엇인가. 기본적으로는 Scene이 넘어가는 순간을 의미한다.
* 추가적으로는 던전의 생성 및 Stage 진행, 새 게임 진행을 위한 Data Clear 동작의 실행 타이밍 정도. 더 있을텐데 당장 기억나는건 우선 던전 생성 하나인듯.
* 각 Scene에서 다른 Scene로 진행돼야 하는 이벤트들을 전부 적어보자. Scene 내부에서의 화면전환은 각 Scene의 Controller가 진행한다.
* 흐름에 관여하는 데이터, 즉 Stage는 Game Controller가 들고있자.
#### 1. Main 화면  
* Start Button을 누르면 Intro로 넘어가야함. 여기는 Data Clean을 수행할 곳. (o)
#### 2. Intro 화면
* Start Button을 누르면 Gacha Scene으로 넘어가야함. (o)
#### 3. Gacha 화면
* Go 누르면 Map으로 넘어가야함. 이 때가 Dungeon 생성 타이밍이다. (o)
#### 4. Map 화면
* 노드 누른 후 Dive 누르면 Stage로 넘어가야함. (o)
#### 5. Stage 화면
* 캐릭터 사망시 Result Scene에 Game Over로...
* 이외의 경우는 전부 Event Clear 및 Item 루팅을 마친 것을 전재함.
* 보스 Room Clear 시 다음 Stage로 넘어가야 하는데... 이거도 Result Scene으로 처리하자. Stage Clear로.
* 최종 스테이지 Clear 시 Clear로... 이것 또한 Result Scene이다. 결론은 이 세 이벤트는 똑같이 Result Scene으로 넘어간다. (o)
* result scene으로 넘길 때엔 score도 함께 넘기자.
* 일반 Room Clear 시 Map으로 돌아간다. (o)
#### 6. Result 화면
* 스테이지 Clear일 시 Gacha Scene으로 넘어가야함. 여기가 넘어가기 전에 Stage 진행을 수행할 곳. 이건 공통으로 빼자. (o) 
* 게임이 끝났을 시, Main Menu로 넘어가야함. (o)
#### 종합
* 위 동작들을 전부 메서드로 만든다. 그게 가장 깔끔하다. 짜피 자주 호출되는 메서드들도 아니니까 가시성이 더 중요한듯.

#### Scene 전환의 판단 기준.
* Party Panel에서 버튼을 눌렀다고 신호를 주긴 해야합니다.
* 근데 그 버튼을 두고 어디로 넘길지 정하는 주체가 너무 애매합니다.
* 요약하자면 '현재 통제권을 가지고 있는 controller'가 신호를 받고 판단해야함. 다음은 어디로 갈 지를 말이지.
* GameController가 큰 흐름을 쥐고있지만 우선적으로 GameController가 Scene을 넘길지를 정하는 건 각각의 Scene의 Controller들이다.
    + Gacha - 파티 5인이 다 차야 Scene을 넘길 수 있다.
    + Map - 다음 노드를 골라야 Scene을 넘길 수 있다.
    + Stage - 기본적으로 Next는 비활성이며 이벤트를 클리어한 후 누르면 Phase를 진행한다. Phase 진행 후 다음 Phase 또는 Map 또는 Result로 넘어가야한다.
    + Player - 모든 캐릭터의 사망신고가 접수되면 무조건 Game Over로 판단하고 Result로 넘어간다.
    
* 다음으로 가는 판단의 주체는 사실 저 넷인데. Game Controller도 말이 Game Controller지 저 넷 + Result의 지령을 받아 장면만 바꿔주는게 역할이다.
* 그렇다면 Party Panel은 어떤 방법으로 알맞은 Game Scene들에 지령을 전하나? 걍 GameController 통해서 가지 뭐.. 결국 어디가 뭐하고있는지 아는건 이놈이니.
* 일단 Next 버튼 비활성화 방안부터 좀 어떻게 해봐야할듯. 아래로 이어진다.

#### Scene 전환의 룰
* 결론적으로 Next 버튼을 활성화시킬 조건이 됐는지, 즉 다음 Scene으로 넘어갈 조건이 완료됐는지를 판단하는건 각각의 Scene들이다.
* 다음 Scene이 뭐가 될 지 또한 각각의 Scene들이 판단해야한다.
* Game의 흐름 관리는 GameController를 통해 이루어져야한다.
* 위 세 가지 룰을 고려한 결과, 결국 서로의 영역을 침범할 수 밖에 없다는 결론이 나온다.
* 따라서 Scene동작에 한해 아래와 같은 타협된 동작룰을 재정한다.
    + Scene에서 Next Button을 활성화한다.
    + Next Button을 누르면 이벤트에선 Game Controller를 호출한다.
    + Game Controller는 다음 Scene이 어디인지를 각각의 Scene에게 물어본다.
    + 각각의 Scene는 독자적인 판단을 통해... 다음 Scene이 어디인지를 정하고 답변한다.
    + Scene이 GameController를 통해 next button 활성화 -> Game Controller가 현재 Scene에게 다음으로 넘어갈 Scene 질의, Scene Controller가 답변을 반환, Game Controller가 Scene을 Set

## Data Controller
* 위에도 적었듯, Game Controller는 이름에 맞게 게임의 전체 흐름만 관장한다. 즉, 데이터는 다른 컨트롤러들이 관리한다.
* Map Controller는 현재 몇 층인지에 따라 Map을 생성한 다음엔 현재 위치 채크, 다음 방 세팅 정도만 하면 된다.
* User Controller는 유저의 데이터를 관리해야한다. 세분화가 좀 필요하겠지만... 유저와 맵만이 게임 전체에 남아있는 데이터인듯.
* Room 내에서 이벤트를 처리하는건 Current Controller가 BattleController, EventController, RootingController 등과 연계해서 처리하는걸로 생각중이다. Event와 Battle을 한 곳에서 처리하기엔 로직이 많지만, 어쨌건 방에 입장하면 일어나는 일이기에 이렇게 하기로.
* 별도로 Rooting Controller를 둔 이유는 유저 컨트롤 + 발생 이벤트와 연계할 일이 많은 데이터이기 때문. 창도 따로 둬야하고...
* GachaController는 심플하다. 픽업 정하고, 몇 연챠 버튼 눌렀는지 채크하고 유저 자원 채크해서 데이터 생성하고 화면 바꾸는 정도의 일을 한다.

## Data의 원칙
* Data를 참조하는건 어디서든 가능. 하지만 Data를 조작하는 것은 Controller로만 가능.
* Data를 참조할 때는 Model에 어디어디서 보고있는지 주석으로 적어주자.

## 플로우의 초반은 여기까지. 좀 더 제작하고 봅시다.
+ 뼈대가 생각보다 금방 만들어졌다. 이젠 첫 Scene에서 조건을 충족하지 못하면 다음으로 넘어갈 수 없으니 첫 Scene의 구현으로 넘어간다.

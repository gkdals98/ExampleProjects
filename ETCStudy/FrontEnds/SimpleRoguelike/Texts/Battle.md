### 전투의 구체적인 사항들에 대한 이야기를 해보자.
+ 지금 정해진 러프한 계산 및 판정들을 순서대로 쭉 적어본다.
    1. 상태이상을 적용한다.
    2. (적용 후) 속도를 계산해 순서를 정한다.
    3. 가장 빠른 유닛의 스킬풀의 첫 스킬을 불러온다.
    4. 스킬의 대상들에게 스킬을 발동시킨다.
    5. 데미지 계산 종료후 상태이상 효과, 혹은 버프효과를 적용한다.
+ 매 턴 터지는 상태이상(독)과 지속 상태이상(수치저하 등)을 어떻게 처리하나?
+ 매 턴 원래 스텟에서 상태이상을 새로 적용하는 식으로 가자. 데미지 터질건 터지고, 말건 말고.
+ 공격 시, 피격 시에 영향이 오는 상태이상은 before_attack, before_damaged, at_every_attack, at_every_damaged, after_attack, after_damaged, after_death, after_kill 이름의 배열에 넣어 보관할까.
+ 그렇다면 각 과정을 다시 정리해보면
#### 0. 아이템 효과 적용
+ 스탠바이 스텝. 싸움 개시와 동시에 소모템과 유저템(슬더스 유물 따라한 거), 장비아이템의 효과를 적용한다.
#### 1. 상태이상의 적용
1. 플레이어 캐릭터 앞에서 뒤로, 이후 적 캐릭터 앞에서 뒤로 순서로 사망한 캐릭터를 제외하고 호출한다.
2. 캐릭터가 호출되면 적용된 상태이상 큐를 가져온다. (큐는 vue로 ui에 연동)
3. 상태이상 큐를 앞에서부터 확인하며 아래의 효과들을 적용한다.
    * 수치 변동 (체력에 데미지를 입는 화상, 체력을 회복하는 힐 또한 현재 체력이라는 수치에 대한 변동으로 취급하여 함께 계산)
    * before_attack, before_damaged, at_every_attack, at_every_damaged, after_attack, after_damaged, after_death, after_kill 큐에 특정 효과가 발동해야하는 상태이상들을 넣는다.
+ 여기서 참조하는 수치는 GD, 지구력, Hit Point를 제외하곤 전부 스탠바이 스텝만 거쳐 가공된 const 값이다. 같은 상태이상 값이 두 번 적용되는 걸 막기 위해 매 턴 새로 계산한다.   
#### 2. 속도의 계산
+ 1. 플레이어 캐릭터의 앞에서 뒤로, 이후 적 캐릭터 앞에서 뒤로 순서로 사망한 캐릭터를 제외하고 호출한다.
+ 2. 각 캐릭터의 최종 속도치를 비교해 버블소트로 행동 순서 배열에 넣는다. 속도가 같은 경우에도 뒤로 보냄. 즉 무조건 탱커가 먼저 행동하며 무조건 플레이어가 선공싸움에 유리하다.
#### 3. 스킬의 활성화 => 4. 발동 => 5. 데미지 계산.
+ 공격에 의해 대상이 죽지 않았음을 전재로 쭉 적어보자.. 
+ 사이의 #은 캐릭터 및 대상의 사망여부 판단 지점이다.
1. 행동 순서 배열의 첫 캐릭터를 액터로 올린다.
2. 액터의 스킬풀 큐의 가장 첫 스킬을 불러 Attack 메서드에 넣는다.
3. 스킬풀의 범위를 읽어들여 범위에 해당하는 대상들을 공격대상 큐에 앞에서 부터 넣는다.
4. 액터의 before_attack을 읽어들여 적용한다. #
5. for 대상만큼 - { 대상의 before_damaged을 읽어들여 적용한다. #
6. (최종 계산된 액터의 스킬 데미지를 공격대상 큐의 앞에서부터 적용, 액터의 at_every_attack 적용 #, 대상의 at_every_damaged 적용 #) * 공격 횟수
7. 액터의 스킬 효과나 대상의 효과에 따른 상태이상을 대상에게 적용  #
8. 대상의 after_damaged을 읽어들여 적용한다. #} 5~7을 대상만큼 루프.
9. 액터의 after_attack을 읽어들여 적용한다. #
+ 진행 도중 액터, 대상 둘 중 하나가 사망판정이 나면 그 즉시 6의 루프 과정을 중단한다.
+ 8에 해당하는 효과는 반격인지 여부를 반드시 정한다. 반격의 경우 사망시엔 발동하지 않는다.
+ 사망처리의 경우, after_death, after_kill 처리를 수행한다.
+ 액터의 사망판정 시 다음 액터의 차래로, 대상의 사망 판정시 대상을 바꿔 루프를 진행한다.

#### 데미지 계산의 방법.
+ 우선 데미지 계산 관련 내용들을 러프하게 적어보자.
+ 명중률은 명중률과 회피율의 합산 수치만큼 random을 돌려 명중률의 수치 이하가 나오면 명중한 것으로 계산한다.
+ 명중했다면 GD가 0이 아니라면 GD를 1 차감한다. GD가 0이라면 아래로 진행한다.
+ 지구력은 기본적으로 100이 넘어가는 수치이며 이후 데미지를 입을 때 마다 100 이하로 떨어진 수치를 기반으로 피격 확률을 계산한다.
+ 지구력이 100 이하일 시, 떨어진 수치를 기반으로 확률을 계산, 판정에 성공했다면 Hit 포인트를, 실패했다면 지구력에 데미지를 입힌다.
+ 수치조절이 좀 중요해보이네... 못해도 5합부터는 사망자가 나와야되는걸 고려하면... 실험을 위한 유저의 평균 DPT 초기값은 얼추 10 중후반? 몹들 초반 지구력은 평균 120대로 생각하면 될까.
+ 반면 유저의 값은 좀 높게 주고.... 랜덤성이 강한 게임이니 유저들에게 방어수단을 줬을지언정 몹들의 DPT를 너무 높게 주지는 말자... 불합리하다. DPT가 높은 몹은 대신 빨리 죽거나...
+ 획득한 스킬들은 인벤토리에 남되 몇 개 있는지를 적어주는 식으로 하자. 중반 이후로는 커먼스킬은 원하는 비중대로 세팅할 수 있게.
+ 커먼스킬들은 방어에 치중된 스킬이 많은걸로 가자. 탄막을 흩뿌리며 자신의 회피율 소폭증가라거나. 
+ 유저의 생존력을 올리는 빌드와 다양한 방법으로 생존력을 저해하는 적들. 이게 이상적인가.
+ 스킬 컨셉의과 수치에 관한 벨런싱은 실험 진행이 좀 돼야 가능하지싶다. 수많은 실험을 통해서 말이지.... 
+ 종합적으로 과정은 아래와 같다.
1. 액터의 최종 명중률, 대상의 최종 회피율을 더한다.
2. random으로 해당 수치를 돌려 최종 명중률 아래의 수치가 나오면 데미지 계산.
3. 100 - 지구력이 0보다 크다면 random으로 100을 돌린다. 해당 수치 이하가 나오면 Hit Point 차감.
4. 100 - 지구력이 0보다 작다면 지구력에 데미지를 입힌다.
5. 이 계산으로 Hit 포인트가 0가 됐다면 대상을 사망처리하고 대상의 at_every_damaged, 버프 디버프 적용, after_attack을 처리하지 않으며 after_death, after_kill 순서로 효과를 적용한다. 
6. after_death와 after_kill은..... 음... 있어야하나? 자주 있는 패턴이긴 한데.
+ 이렇게 상태이상 적용 큐를 적용타이밍별로 죄다 나눠놓고보니.. 구현이 상당히 번거로워 보이지만 이게 스킬 효과 구현시의 가시성이 더 높을 것 같아서....

#### 이렇게 된다면 전투 컨트롤러에 필요한 값들은 무엇일까?
1. 캐릭터들의 스텟 원래 수치 (상세하게 뭐뭐 필요한지 적기)
    + 최대 지구력 (장비아이템 적용 완료된, 이하 적용 완료된으로 줄임)
    + 최대 Hit Point (적용 완료된)
    + ATK (적용 완료된)
    + 회피율 (적용 완료된)
    + SPD (적용 완료된)
    + AM (적용 완료된)
    + 직업군과 포지션은 전투 수치에 관여할까 말까.... 버프 한정으로 영향 받게 해야겠지?
    + 스킬 풀
2. 캐릭터들의 현재 수치 (상세하게 뭐뭐 필요한지 적기) 현재 지구력 및 현재 Hit Point를 제외한 수치는 전투 끝나면 삭제.
    + 현재 지구력 
    + 현재 Hit Point
    + ATK 최종 계산값
    + 회피율 최종 계산값
    + SPD 최종 계산값
    + GD 최종 계산값 (효과에 의해서만 발생함으로 기본치는 0이다. 따라서 원래 수치는 필요없다.)
    + AM 최종 계산값.
    + 스킬 풀 현재 위치
    + 적용된 상태이상 및 버프 큐.
3. 아군, 적 캐릭터들의 배치 배열
4. 행동 순서 배열
5. 액터 (사망여부 포함)
6. 대상 (사망여부 포함)
7. before_attack, before_damaged, at_every_attack, at_every_damaged, after_attack, after_damaged, after_death, after_kill 큐.

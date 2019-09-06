
//게임 진행에 따라 동적으로 바뀌는 캐릭터 데이터를 다루기 위한 스트럭쳐.
//Player Model에서 들고있을 캐릭터는 해당 데이터로 관리된다.
//repository에서 받은 데이터를 해당 객체로 매핑시켜 사용.
export default class CharacterData {
  constructor(data){
    this.original_data = data;

    //아래는 데이터를 참조할 예정이지만 데이터 안에 뭐가 들어가야하나 한 번 정리한 것.

    //이름
    this.char_name = data.char_name;

    //레어도
    this.char_rarity = data.char_rarity;

    //포지션. 설 수 있는 자리를 제한.
    this.char_position = data.char_position;

    //직군, 전투스타일 - 첩보요원, 스파이, 전투요원, 히트맨 등등?
    this.char_style = data.char_style;

    //설명. 툴팁에 출력.
    this.char_description = data.char_description;

    //캐릭터 생존 여부
    this.is_alive = true;

    //스테미나 맥스치.
    this.max_stamina = data.char_stamina;

    //남아있는 스테미나
    this.cur_stamina = data.char_stamina;

    //최대 히트포인트. 초기화시엔 max 값.
    this.max_hit_point = data.char_hit_point;

    //남아있는 히트포인트. 초기화시엔 max값.
    this.cur_hit_point = data.char_hit_point;

    //회피율
    this.char_dodge_rate = data.char_dodge_rate;

    //행동 속도
    this.char_speed = data.char_speed;

    //판정에 쓰일 테크 스텟
    this.char_tech = data.char_tech;

    //피격당할 시 데미지 판정을 무효화 할 수 있는 가드
    this.cur_guard = data.char_guard;

    //지구력에 입는 데미지를 줄여주는 아머
    this.cur_armor = data.char_armor;

    //기본 장비 3세트를 담아두는 배열
    this.equipment = data.equipment;

    //스킬세트. 순서대로 발동.
    this.skill_pool = data.skill_pool;

    //전투중 받게 된 버프들
    this.buff_stack = [];

    //전투중 받게 된 디버프들
    this.debuff_stack = [];

    CharacterData.prototype.setAlive = function(is_alive){
      this.is_alive = is_alive;
    }

  }
}

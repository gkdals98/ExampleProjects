export default class CharacterData {
  constructor(data){
    this.original_data = data;

    //아래는 데이터를 참조할 예정이지만 데이터 안에 뭐가 들어가야하나 한 번 정리한 것.
    this.char_name = data.char_name;
    this.char_rarity = data.char_rarity;
    this.char_position = data.char_position;
    this.char_style = data.char_style;
    this.char_description = data.char_description;
    this.char_stamina = data.char_stamina;
    this.char_hit_point = data.char_hit_point;
    this.char_dodge_rate = data.char_dodge_rate;
    this.char_speed = data.char_speed;
    this.char_guard = data.char_guard;
    this.char_armor = data.char_armor;
    this.is_dead = false;
    this.equipment = data.equipment;
    this.skill_pool = data.skill_pool;

    //게임 진행에 따라 유동적으로 바뀌는 값들. 처음엔 맥스 수치.
    this.cur_stamina = data.char_stamina;
    this.cur_hit_point = data.char_hit_point;

    this.skill_pull = data.char_armor;
    this.buff_stack = [];
    this.debuff_stack = [];

    //다음 노드의 배열상 좌표를 얻어온다.
    CharacterData.prototype.addNext = function(array_x, array_y){
      //console.log("Get next - x : " + array_x + ", y : " + array_y);
      this.next_node_array_xy.push(
        { x : array_x,  y : array_y }
      )
    }
    CharacterData.prototype.setClickable = function(is_clickable){
      this.clickable = is_clickable;
    }
  }
}

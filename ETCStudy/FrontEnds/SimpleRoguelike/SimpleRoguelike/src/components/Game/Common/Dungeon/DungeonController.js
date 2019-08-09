import RoomNode from './RoomNode.js';
import { map_model } from './CurrentMapModel.js';
import { ui_valiables } from '../Data/UIValiables.js'
class DungeonController{
  constructor(){
    this.floor_length = 0;
    this.name = "Dungeon Controller"
  }
}

DungeonController.prototype.clearDungeon = function(stage){
  console.log(this.name + " : Clear Dungeon");
  map_model.commit('clearMapModel');
}
DungeonController.prototype.createDungeon = function(stage){
  // 층수는 1층이 6  2, 3층이 7  4, 5층이 8
  console.log(this.name + " : Start dungeon generating - floor " + stage);
  this.floor_length = parseInt( 6 + (stage / 2) );
  this.createRoomNodes();
  this.connectMapLine();
  this.setRandomEncounter();
  console.log(this.name + " : Dungeon generating done");
}

/**
*
*  Initialize Dungeons
*
**/

//층수만큼의 방을 생성하는 부분.
DungeonController.prototype.createRoomNodes = function(){
  console.log(this.name + " : Create RoomNodes");

  for (var current_floor = 1; current_floor <= this.floor_length; current_floor++){

    var floor_data = [];
    var floor_width = Math.floor(Math.random() * 3) + 3;

    for (var current_room = 1; current_room <= floor_width; current_room++){
      var roomnode = new RoomNode(this.makePXposition( ui_valiables.canvas_x, this.floor_length, current_floor ),
          this.makePXposition( ui_valiables.canvas_y, floor_width ,current_room ));
      floor_data.push(roomnode);
    }
    map_model.commit('addFloor', floor_data);
  }

  console.log(this.name + " : Create RoomNodes finish");
}


/**
*
*  Connect Line
*
**/

//방들 사이에 선을 이어주는 부분.
DungeonController.prototype.connectMapLine = function(){
  console.log(this.name + " : Connect Line Between Nodes");

  var map = map_model.state.current_map_model;
  var oneway_count = [0, 0, 0, 0, 0];
  //1층부터 마지막 층 직전까지 (floor가 x, )
  for (var current_floor = 0; current_floor < this.floor_length-1 ; current_floor++){
    console.log(this.name + " : Connect Line Start from " + (current_floor+1) + "F, length : " + map_model.state.current_map_model[current_floor].length);
    //더 큰 쪽에서 더 작은 쪽으로 선 긋기 알고리즘을 진행하기 위한 준비.
    console.log("흠흠흠흠흠흠흠")

    console.log(oneway_count)
    console.log(map[current_floor])
    var leng1 = map[current_floor].length;
    var leng2 = map[current_floor+1].length;

    var is_front_from = leng1 >= leng2 ? true : false;

    var from_length = is_front_from ? leng1 : leng2;
    var to_length = is_front_from ? leng2 : leng1;

    //from에서 to만큼 to에 선을 그어야하며,
    //추후 선을 긋는 동작에서 배제된 노드에서 선을 이어야하기에 배제노드 좌표를 저장한다.
    var connect_latter = [];
    for(var term = 0; term < from_length - to_length; term++){
      var is_ok = true;
      var latter_y = 0;
      while(is_ok){
        latter_y = Math.floor(Math.random() * from_length);
        is_ok = connect_latter.includes(latter_y);
      }
      connect_latter.push(latter_y);
    }
    console.log(this.name + " : Connected Latter on " + (current_floor+1) + "F");
    console.log(connect_latter)
    //제외되지 않은 노드들을 반대편과 연결한다.
    //직후 수행될 vector 충돌 검사를 위해 배열상 벡터 저장. 여기서 from의 x는 0으로, to의 x는 1로 계산.
    var to_y = 0;
    var connected_vector = [];

    console.log(this.name + " : Connect line from not delayed node " + (current_floor+1) + "F" )

    for(var from_y = 0; from_y < from_length; from_y++){
      if(!connect_latter.includes(from_y)){
        map_model.commit('connectLine',
          { f_x : current_floor,
            f_y : (is_front_from ? from_y : to_y),
            t_x : current_floor + 1,
            t_y : (is_front_from ? to_y : from_y)
          });

        connected_vector.push( {f_x : 0, f_y : from_y, t_x : 1, t_y : to_y} );
        to_y++;
      }
    }

    console.log(this.name + " : Connected Vector on " + (current_floor+1) + "F");
    console.log(connected_vector)
    //제외된 노드들을 연결한다. 이 과정에서 벡터 충돌검사를 통해 연결 가능 여부를 판단한다.
    //from의 x좌표를 0으로, to의 x좌표를 1로 계산한다.
    for(var i in connect_latter){
      var is_ok = false;
      while(!is_ok){
        var random_y = Math.floor(Math.random() * to_length);
        is_ok = true;
        var r_vector = {f_x : 0, f_y : connect_latter[i], t_x : 1, t_y : random_y}
        console.log(r_vector)
        for(var j in connected_vector){
          if(this.isLineCrossed(connected_vector[j], r_vector)){
            is_ok = false;
          }
        }
        if(is_ok){
          map_model.commit('connectLine', {
            f_x : current_floor,
            f_y : (is_front_from ? connect_latter[i] : random_y),
            t_x : current_floor + 1,
            t_y : (is_front_from ? random_y : connect_latter[i])
          });
          connected_vector.push( r_vector );
        }
      }
    }

    //외길 카운트를 세어 한 가닥으로 생성된 길에 추가로 갈림길을 달아주는 메서드.
    //외길 수치가 4 이상이 되면 랜덤으로 4번, 해당 노드에서 길을 생성하며 선 교차가 없을 시 선을 이어준다. 생성 안됐다면 카운트를 계속 늘린다.
    var to_y_for_oneway = 0;

    for(var from_y = 0; from_y < from_length; from_y++){
      //추가 갈림길 생성 대상이 아니라면 외길로 판단할 수 있다.
        console.log("야 너 몇 번 돌아" + from_y)
      if(!connect_latter.includes(from_y)){
        //우선 현재 front 중 외길 카운트가 3인지 채크한 후 동작 결정.
        var need_connect = false;
        if(is_front_from){
          need_connect = (oneway_count[from_y] > 2);
        }else{
          need_connect = (oneway_count[to_y_for_oneway] > 2);
        }
        //선을 잇는 동작이 필요할 시 6번 시도
        if(need_connect){
          console.log("그 처절한 시도나 함 봅시다. need : " + need_connect + ", from where? : " + (is_front_from ? from_y : to_y_for_oneway))
          for(var _ = 0; _ < 10; _++){
            var random_back = 0;

            if(is_front_from){
              random_back = Math.floor(Math.random() * to_length);
              r_vector = {f_x : 0, f_y : from_y, t_x : 1, t_y : random_back};
            }else{
              random_back = Math.floor(Math.random() * from_length);
              r_vector = {f_x : 0, f_y : random_back, t_x : 1, t_y : to_y_for_oneway};
            }

            var is_ok = true;
            for(var j in connected_vector){
              if(this.isLineCrossed(connected_vector[j], r_vector)){
                is_ok = false;
              }
            }
            //한 번만 OK가 나면 바로 break한다.
            if(is_ok){
              var front_y = is_front_from ? from_y : to_y_for_oneway;

              map_model.commit('connectLine', {
                f_x : current_floor,
                f_y : front_y,
                t_x : current_floor + 1,
                t_y : random_back
              });
              connected_vector.push( r_vector );
              oneway_count[front_y] = 0;
              break;
            }
          }
        }
        //새로 생긴 선이 없다면 count를 ++한다.

        //to 쪽의 y카운트도 증가시킴.
        to_y_for_oneway++;
      }
    }

    //추가 갈림길을 0~1개 생성한다.
    //from의 x좌표를 0으로, to의 x좌표를 1로 계산한다.
    var more_line = (Math.floor(Math.random() * 2));
    console.log("More line " + more_line)
    for(var i = 0; i < more_line; i++){
      var is_ok = false;
      while(!is_ok){
        var random_f_y = Math.floor(Math.random() * from_length);
        var random_t_y = Math.floor(Math.random() * to_length);
        is_ok = true;
        var r_vector = {f_x : 0, f_y : random_f_y, t_x : 1, t_y : random_t_y}
        for(var j in connected_vector){
          if(this.isLineCrossed(connected_vector[j], r_vector)){
            is_ok = false;
          }
        }
        if(is_ok){
          map_model.commit('connectLine', {
            f_x : current_floor,
            f_y : (is_front_from ? random_f_y : random_t_y),
            t_x : current_floor + 1,
            t_y : (is_front_from ? random_t_y : random_f_y)
          });
          connected_vector.push( r_vector );
        }
      }
    }

    var temp_oneway_count = [0, 0, 0, 0, 0];

    //선을 다 이었으면 외길 채크를 해야한다.
    //외길의 조건은 front 노드에서 이어진 길이 하나뿐인 경우.
    var check_front_is_oneway = [[],[],[],[],[]];
    for(var j in connected_vector){
      if(is_front_from){
        check_front_is_oneway[(connected_vector[j].f_y)].push(connected_vector[j]);
      }else{
        check_front_is_oneway[(connected_vector[j].t_y)].push(connected_vector[j]);
      }
    }

    //from에서 간 길이 하나뿐이라면 그 하나뿐인 to의 카운트를 올린다. + 두 군데에서 진입해올 경우 카운트가 높은 쪽을 남겨야한다.
    for(var y_count in check_front_is_oneway){
      if(check_front_is_oneway[y_count].length === 1){
        if(is_front_from){
          temp_oneway_count[check_front_is_oneway[y_count][0].t_y] = oneway_count[check_front_is_oneway[y_count][0].f_y] + 1
        }else{
          temp_oneway_count[check_front_is_oneway[y_count][0].f_y] = oneway_count[check_front_is_oneway[y_count][0].t_y] + 1;
        }
      }
    }
    //마지막으로 다음 순환을 위해 외길 카운트를 저장한다.
    oneway_count = temp_oneway_count;
    console.log("허허허허허허")

    console.log(temp_oneway_count)
    console.log(oneway_count)
  }
  console.log("Line Connecting finished");
}


/**
*
*  Setting Event
*
**/

//방에 이벤트를 세팅한다.
DungeonController.prototype.setRandomEncounter = function(){

}


/**
*
*  Utilities
*
**/

//다음으로 진행 가능한 노드들을 model상에서 clickable 상태로.
DungeonController.prototype.setClickableNext = function(next_array_x, next_array_y){

}

//다음으로 진행 가능한 노드들을 model상에서 clickable 상태로.
DungeonController.prototype.makePXposition = function ( const_px, number_of_column, current_position ){
  // 층 당 x좌표의 시작점이 되는 위치의 단위.
  var px_scale = parseInt( const_px / number_of_column );

  // 현재 층의 x좌표 시작점. 스케일만큼 포지션을 움직인 뒤 버튼의 정위치(스케일의 절반지점에서 버튼 절반크기만큼 이동한 지점)를 더함
  var px_start = px_scale * ( current_position - 1 ) + (parseInt( px_scale / 2) - ui_valiables.node_button_harf);

  // 정위치에서 움직일 랜덤값. + -로 버튼 크기의 3분에 1만큼 이동.
  var px_to_move = Math.floor( Math.random() * Math.floor( ( ui_valiables.node_button * 2 ) / 3 ) )
      - Math.floor( ui_valiables.node_button_harf / 3 ) ;

  // 최종좌표
  return px_start + px_to_move;
}

DungeonController.prototype.isLineCrossed = function(vector1, vector2){
  let p1 = { x : vector1.f_x, y : vector1.f_y };
  let p2 = { x : vector1.t_x, y : vector1.t_y };
  let p3 = { x : vector2.f_x, y : vector2.f_y };
  let p4 = { x : vector2.t_x, y : vector2.t_y };

  if(p1.x==p3.x && p2.x==p4.x && p1.y==p3.y && p2.y==p4.y) return true;

  if(Math.max(p1.x, p2.x) <= Math.min(p3.x, p4.x)) return false;
  if(Math.min(p1.x, p2.x) >= Math.max(p3.x, p4.x)) return false;
  if(Math.max(p1.y, p2.y) <= Math.min(p3.y, p4.y)) return false;
  if(Math.min(p1.y, p2.y) >= Math.max(p3.y, p4.y)) return false;

  let sign1 = (p2.x-p1.x)*(p3.y-p1.y) - (p3.x-p1.x)*(p2.y-p1.y);
  let sign2 = (p2.x-p1.x)*(p4.y-p1.y) - (p4.x-p1.x)*(p2.y-p1.y);
  let sign3 = (p4.x-p3.x)*(p1.y-p3.y) - (p1.x-p3.x)*(p4.y-p3.y);
  let sign4 = (p4.x-p3.x)*(p2.y-p3.y) - (p2.x-p3.x)*(p4.y-p3.y);

  if(sign1==0 && sign2==0 && sign3==0 && sign4==0) return true;

  return sign1*sign2<0 && sign3*sign4<0;
}

DungeonController.prototype.clearMap = function(){

}

/**
*
*  export
*
**/

export let dungeon_controller = new DungeonController();

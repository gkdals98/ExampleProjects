import RoomNode from './RoomNode.js';
import { map_model } from './CurrentMapModel.js';
import { const_ui_valiables } from '../Common/Data/UIValiables.js'
class DungeonController{
  constructor(){
    this.floor_length = 0;
  }
}

DungeonController.prototype.createDungeon = function(stage){
  // 층수는 1층이 6  2, 3층이 7  4, 5층이 8
  console.log("Start dungeon generating");
  this.floor_length = parseInt( 6 + (stage / 2) );
  this.createRoomNodes();
  this.connectMapLine();
  this.setRandomEncounter();
  console.log("Dungeon generating done");
}

/**
*
*  Initialize Dungeons
*
**/

//층수만큼의 방을 생성하는 부분.
DungeonController.prototype.createRoomNodes = function(){
  console.log("Create RoomNodes");

  for (current_floor = 1; current_floor <= floor_length; current_floor++){

    var floor_data = [];
    var floor_width = Math.floor(Math.random() * 2) + 3;

    for (current_room = 1; current_room <= floor_width; current_room++){
      var roomnode = new RoomNode(this.makeXposition( current_floor ), this.makeXposition( floor_width ,current_room ));
      floor_data.push(roomnode);
    }

    map_model.commit('addFloor', floor_data);
  }

  console.log("Create RoomNodes finish");
}

//방들 사이에 선을 이어주는 부분.
DungeonController.prototype.connectMapLine = function(){

}

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
DungeonController.prototype.makeXposition( const_px, px_width, current_position ){
  // 층 당 x좌표의 시작점이 되는 위치의 단위. 
  var px_scale = parseInt( const_px / px_width ));

  // 현재 층의 x좌표 시작점
  var px_start = xscale * ( current_position - 1 )

  // 층 당 시작점에서부터 움직일 x좌표 기준점.
  var px_to_move = parseInt(( const_px / px_width ) / 2) - const_ui_valiables.node_button_harf;

  var x
}

DungeonController.prototype.makeYposition( floor_width, current_room ){
  // 층 당 y좌표의 시작점이 되는 위치
  var y_scale = parseInt(const_ui_valiables.canvas_y / floor_width);

  // 현재 위치의 y좌표 시작점
  var y_start = xscale * ( current_room - 1 )

  // 층 당 시작점에서부터 움직일 y좌표 기준점.
  var y_to_move = parseInt((const_ui_valiables.canvas_y / floor_width) / 2) - const_ui_valiables.node_button_harf;
}

DungeonController.prototype.clearMap = function(){

}

/**
*
*  export
*
**/

export let dungeon_controller = new DungeonController();

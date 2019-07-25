import RoomNode from './RoomNode.js';
import { map_model } from './CurrentMapModel.js';
import { ui_valiables } from '../Data/UIValiables.js'
class DungeonController{
  constructor(){
    this.floor_length = 0;
  }
}

DungeonController.prototype.clearDungeon = function(stage){
  console.log("Clear Dungeon");
  map_model.commit('clearMapModel');
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

DungeonController.prototype.clearMap = function(){

}

/**
*
*  export
*
**/

export let dungeon_controller = new DungeonController();

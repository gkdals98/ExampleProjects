import Vue from 'vue'
import Vuex from 'vuex'
import RoomNode from './RoomNode.js';

Vue.use(Vuex)

//MapScene에서 참조중

export const map_model = new Vuex.Store({
  // 맵, 플레이어의 현재 위치
  state: {
    current_map_model: [],
    current_player_x:0,
    current_player_y:0,
  },
  //필요할진 모르곘는데 나중에 활용할 수 있을때도 깜빡해서 못쓸까봐 우선 정의
  getters: {
    next_array_x : state => {
      state.current_map_model[state.current_player_x][state.current_player_y].next_node_array_xy[0]
    },
    player_xy : state => {
      {current_player_x, current_player_y}
    }
  },
  mutations: {
    clearMapModel ( state ) {
      state.current_map_model = [];
      state.current_player_x = 0;
      state.current_player_y = 0;
    },
    addFloor( state, floor ){
      state.current_map_model.push(floor);
    },
    connectLine ( state, {f_x, f_y, t_x, t_y}){
      //console.log("Map Model : Got it - " + f_x + ", " + f_y + ", " + t_x + ", " + t_y);
      state.current_map_model[f_x][f_y].addNext( t_x, t_y )
    },
    moveToNextNode (state, {next_x, next_y}){
      state.current_player_x = next_x;
      state.current_player_y = next_y;
    }
  }
});


// class CurrentMapModel{
//   constructor(){
//     this.nodeData = null;
//   }
// }
//
// CurrentMapModel.prototype.b = function(){
//   this.a++;
// }
// export let map_model = new CurrentMapModel();

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export const stage_model = new Vuex.Store({
  // 현 노드의 구성 정보, 노드의 진행 정도, 현재 표시되는 Scene (Battle, Event)
  state: {
    current_node_info : null,
    current_phase : 0,
    current_stage_scene : 0,
  },
  getters: {
  },
  mutations: {
    clearStageModel ( state ) {
      state.current_node_info = null;
      state.current_phase = 0;
      state.current_stage_scene = 0;
    },
    phaseUp( state, floor ){
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

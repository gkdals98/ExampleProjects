import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const game_model = new Vuex.Store({
  // 현 Stage와 현 Scene
  state: {
    current_stage : 0,
    current_scene : 0,
    current_game_state : 0,
    next_button_state : false,
    next_button_text : "Go"
  },
  getters: {
  },
  mutations: {
    clearStageData( state ){
      state.current_stage = 0;
      state.current_scene = 0;
      state.current_game_state = 0;
    },
    setScene( state, scene ){
      state.current_scene = scene;
    },
    progressGame( state, current_state ){
      state.current_game_state = current_state;
    },
    stageUp( state ){
      state.current_stage++;
    },
    setNextButtonClickable( state, next_button_state ){
      state.next_button_state = next_button_state;
    },
    setNextButtonText( state, next_button_text ){
      state.next_button_text = next_button_text;
    }
  }
});

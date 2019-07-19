export const map_model = new Vuex.Store({
  // 맵, 플레이어의 현재 위치
  state: {
    current_map_model: [],
    current_array_x:0,
    current_array_y:0,
  },
  //필요할진 모르곘는데 나중에 활용할 수 있을때도 깜빡해서 못쓸까봐 우선 정의
  getters: {
    next_array_x : state => {
      state.current_map_model[state.current_array_x][state.current_array_y].next_node_array_xy[0]
    }
  },
  mutations: {
    clearMapModel ( state ) {
      state.current_map_model = [];
    },
    addFloor( state, floor ){
      state.current_map_model.push(floor);
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

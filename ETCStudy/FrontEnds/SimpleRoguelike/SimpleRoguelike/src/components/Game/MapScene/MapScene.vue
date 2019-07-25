<template>
  <div id="map_scene">
      <!--<MapLineCanvas>
        <MapLine
          v-for="(line, index) in map_node"
          v-bind:x1="line.x"
          v-bind:y1="line.y"
          v-bind:x2="line.x_next"
          v-bind:y2="line.y_next"
          v-bind:key="index"
        />
      </MapLineCanvas>-->
    <li v-for="(floors, index) in map_node">
      <MapNode
        v-for="(node, index) in floors"
        v-bind:x="node.position_x"
        v-bind:y="node.position_y"
        v-bind:key="index"
      />
    </li>
  </div>
</template>

<script>
import MapNode from './MapNode.vue'
import MapLine from './MapLine.vue';
import MapLineCanvas from './MapLineCanvas.vue'
import { dungeon_controller } from '../Common/Dungeon/DungeonController.js'
import { map_model } from '../Common/Dungeon/CurrentMapModel.js'

export default {
  name : 'MapScene',
  created : function(){
    //필히 추후 clear 시점을 수정할 것. 지금 상태로라면 맵이 매번 새로 생긴다.
    this.clearMap();
  },
  mounted : function(){
    //필히 추후 setMap 시점을 수정할 것. 지금 상태로라면 맵이 매번 새로 생긴다.
    this.setMap(1);
  },
  data : function(){
    return {
      map_node : map_model.state.current_map_model
    }
  },
  components : {
    MapNode,
    MapLineCanvas,
    MapLine
  },
  methods:{
    tryMove : function(){
    },
    clearMap: function(){
      dungeon_controller.clearDungeon();
    },
    setMap : function(stage){
      dungeon_controller.createDungeon(stage);
      console.log(stage + " Stage, Map Generated")
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../common/css/size-template";
#map_scene {
  position : relative;
  height: 100%;
  display: -webkit-flex; /* Safari */
  -webkit-flex-flow: row wrap; /* Safari 6.1+ */
  display: flex;
  flex-flow: row wrap;
}
</style>

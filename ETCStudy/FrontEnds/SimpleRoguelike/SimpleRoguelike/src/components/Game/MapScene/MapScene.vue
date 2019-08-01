<template>
  <div id="map_scene">
    <MapLineCanvas>
      <div
        v-for="(floors, index) in map_node">
        <div
          v-for="(node, index) in floors">
          <!--<MapLine
            v-for="(next_node_array_xy, index) in node"
            v-bind:x1="next_node_array_xy.node.position_x"
            v-bind:y1="next_node_array_xy.node.position_y"
            v-bind:x2="next_node_array_xy.node2.position_x"
            v-bind:y2="next_node_array_xy.node2.position_y"
            v-bind:key="index"
          />-->
          <!--<MapLine
            v-for="(next_node_array_xy, index) in node"
            v-bind:x1="node.position_x"
            v-bind:y1="node.position_y"
            v-bind:x2="node.position_x+20"
            v-bind:y2="node.position_y+20"
            v-bind:key="index"
          />-->
          <!--<MapLine
            v-for="(next_node_array_xy, index) in node"
            v-bind:x1="node.position_x"
            v-bind:y1="node.position_y"
            v-bind:x2="map_node[0][0].position_x"
            v-bind:y2="map_node[0][0].position_y"
            v-bind:key="index"
          />-->
          <MapLine
            v-for="(next_xy, index) in node.next_node_array_xy"
            v-bind:x1="node.position_x"
            v-bind:y1="node.position_y"
            v-bind:x2="map_node[next_xy.x][next_xy.y].position_x"
            v-bind:y2="map_node[next_xy.x][next_xy.y].position_y"
            v-bind:key="index"
          />
        </div>
      </div>
    </MapLineCanvas>
    <div v-for="(floors, index) in map_node">
      <MapNode
        v-for="(node, index) in floors"
        v-bind:x="node.position_x"
        v-bind:y="node.position_y"
        v-bind:key="index"
      />
    </div>
  </div>
</template>

<script>
import MapNode from './MapNode.vue'
import MapLine from './MapLine.vue'
import MapLineCanvas from './MapLineCanvas.vue'
import { dungeon_controller } from '../Common/Dungeon/DungeonController.js'
import { map_model } from '../Common/Dungeon/CurrentMapModel.js'

export default {
  name : 'MapScene',
  created : function(){
    //필히 추후 clear 시점을 수정할 것. 지금 상태로라면 맵이 매번 새로 생긴다.
    console.log("Created");
    this.setMap(5);
  },
  mounted : function(){
    //필히 추후 setMap 시점을 수정할 것. 지금 상태로라면 맵이 매번 새로 생긴다.
    console.log("Mounted");
    this.clearMap();
  },
  data : function(){
    return {
      map_node : map_model.state.current_map_model,
      sample_line : [[[
        {
          node : {
            position_x : 30,
            position_y : 40
          },
          node2 : {
            position_x : 85,
            position_y : 70
          }
        }
      ]]]
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

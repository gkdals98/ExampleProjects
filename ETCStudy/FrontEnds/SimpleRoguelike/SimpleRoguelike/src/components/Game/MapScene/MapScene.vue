<template>
  <div id="map_scene">
      <MapLineCanvas>
        <MapLine
          v-for="(line, index) in map_node"
          v-bind:x1="line.x"
          v-bind:y1="line.y"
          v-bind:x2="line.x_next"
          v-bind:y2="line.y_next"
          v-bind:key="index"
        />
      </MapLineCanvas>
      <MapNode
        v-for="(pos, index) in map_node"
        v-bind:x="pos.x"
        v-bind:y="pos.y"
        v-bind:key="index"
      />
  </div>
</template>

<script>
import MapNode from './MapNode.vue'
import MapLine from './MapLine.vue';
import MapLineCanvas from './MapLineCanvas.vue'

export default {
  name : 'MapScene',
  created : function(){
    this.setMap(1);
  },
  data : function(){
    return {
      map_node : [
        {
          x : 30,
          y : 30,
          x_next : 340,
          y_next : 130
        },
        {
          x : 340,
          y : 130,
          x_next : 170,
          y_next : 170
        },
        {
          x : 170,
          y : 170,
          x_next : 30,
          y_next : 30
        }
      ]
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
    setMap : function(stage){
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

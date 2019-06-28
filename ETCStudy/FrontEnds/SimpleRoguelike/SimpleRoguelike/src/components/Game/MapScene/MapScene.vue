<template>
  <div id="map_scene">
      <MapLineCanvas/>
      <MapNode
        v-for="(pos, index) in lis"
        v-bind:x="pos.x"
        v-bind:y="pos.y"
      />
  </div>
</template>

<script>
import MapNode from './MapNode.vue'
import MapLineCanvas from './MapLineCanvas.vue'

export default {
  name : 'MapScene',
  created : function(){
    this.setMap(1);
  },
  data : function(){
    return {
      lis : [
        {
          x : 120,
          y : 120
        },
        {
          x : 130,
          y : 340
        },
        {
          x : 170,
          y : 170
        }
      ]
    }
  },
  components : {
    MapNode,
    MapLineCanvas
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

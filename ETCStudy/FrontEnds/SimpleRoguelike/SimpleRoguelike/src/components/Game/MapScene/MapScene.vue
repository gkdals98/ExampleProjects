<template>
  <div id="map_scene">
    <canvas id="canvas" width="300" height="300"></canvas>
  </div>
</template>

<script>

export default {
  name : 'MapScene',
  created : function(){
    this.setMap(1);
  },methods:{
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
  height: 100%;
  background-color : #524d69;
  display: -webkit-flex; /* Safari */
  -webkit-flex-flow: row wrap; /* Safari 6.1+ */
  display: flex;
  flex-flow: row wrap;
  #canvas{
    width: 300px;
    height: 300px;
  }
}
</style>

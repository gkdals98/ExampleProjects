<template>
  <div id="map_scene">
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
}
</style>

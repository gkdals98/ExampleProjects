<template>
  <canvas id="map_line"/>
</template>

<script>
export default {
  name : 'MapLineCanvas',
  mounted : function(){
    this.setLine(1)
  },
  methods:{
    setLine : function(stage){
      console.log(stage + " Stage, Line Sat")
       var c = document.getElementById("map_line");
       var ctx = c.getContext("2d");
       ctx.moveTo(50, 57);
       ctx.lineTo(140, 60);
       ctx.stroke();
    }
  }
}
</script>
<style scoped lang="scss">
#map_line{
  background-color: #524d69;
  width: 800px;
  height: 370px;
}
</style>

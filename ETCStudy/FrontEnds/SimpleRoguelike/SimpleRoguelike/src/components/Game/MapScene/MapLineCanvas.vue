<template>
  <canvas id="map_line" width="800" height="370"></canvas>
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
       //ctx.scale(0.42, 0.42);
       ctx.moveTo(0, 0);
       ctx.lineTo(60, 60);
       ctx.moveTo(60, 60);
       ctx.lineTo(360, 150);
       ctx.stroke();
    }
  }
}
</script>
<style scoped lang="scss">
#map_line{
  background-color: #524d69;
}
</style>

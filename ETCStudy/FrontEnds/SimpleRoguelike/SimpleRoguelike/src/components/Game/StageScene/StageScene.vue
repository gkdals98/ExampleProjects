<template>
  <div id="stage_scene">
  </div>
</template>

<script>

export default {
  name : 'StageScene',
  data : function(){
    return {
    }
  },
  components : {
  },
  methods:{
    tryMove : function(){
    }
  }
}
</script>
<style scoped lang="scss">
#map_scene {
  position : absolute;
  height: 100%;
  display: -webkit-flex; /* Safari */
  -webkit-flex-flow: row wrap; /* Safari 6.1+ */
  display: flex;
  flex-flow: row wrap;
    #map{
      background-color: #524d69;
      width: 800px;
      height: 370px;
    }
}
</style>

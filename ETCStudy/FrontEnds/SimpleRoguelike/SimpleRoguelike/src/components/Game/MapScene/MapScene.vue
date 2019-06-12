<template>
  <div id="map_scene">
    <StatusBar/>
    <PartyPanel @settingEnd="tryMoveToDungeon"/>
  </div>
</template>

<script>
import PartyPanel from "../Common/UI/PartyPanel/PartyPanel"
import StatusBar from "../Common/UI/StatusBar/StatusBar"

export default {
  name : 'MapScene',
  components: {
    StatusBar,
    PartyPanel
  },
  data : function(){
    return{
      jewel : 5
    }
  },
  methods:{
    tryGacha : function(){
    },
    selectCharacter : function(){
    },
    tryMoveToDungeon : function(){
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../common/css/size-template";
#map_scene {
  padding-top: 10px;
  height: 100%;
  background-color : #3d3d3d;
}
</style>

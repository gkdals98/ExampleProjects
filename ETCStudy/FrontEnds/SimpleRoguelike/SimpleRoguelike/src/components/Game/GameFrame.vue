<template>
  <div id="frame">
    <StatusBar/>
    <div id="game">
      <GachaScene v-if="current_scene===0"/>
      <MapScene v-if="current_scene===1"/>
      <StageScene v-if="current_scene===2"/>
    </div>
    <PartyPanel @nextSign="setNextState"/>
  </div>
</template>

<script>
import GachaScene from "./GachaScene/GachaScene"
import MapScene from "./MapScene/MapScene"
import StageScene from "./StageScene/StageScene"
import ResultScene from "./ResultScene/ResultScene"
import PartyPanel from "./Common/UI/PartyPanel/PartyPanel"
import StatusBar from "./Common/UI/StatusBar/StatusBar"
import game_controller from "./Common/Core/GameController.js"

const GACHA = 0
const MAP = 1
const STAGE = 2

export default {
  name : 'GameFrame',
  components: {
    StatusBar,
    GachaScene,
    MapScene,
    StageScene,
    PartyPanel
  },
  data : function(){
    return{
      current_scene : GACHA
    }
  },
  methods:{
    setNextState : function(btnsign){
      if(btnsign==="Go"){
        this.current_scene = MAP;
      }
      else if(btnsign==="Dive"){
        this.current_scene = STAGE;
      }
      else if(btnsign==="Battle"){
        this.current_scene = GACHA;
      }
    }
  }
}
</script>
<style scoped lang="scss">
#frame{
  background-color: #3d3d3d;
  height: 100%;
  padding-top: 10px;
  #game {
    margin-top: 5px;
    height: 370px;
  }
}
</style>

<template>
  <div id="frame">
    <StatusBar/>
    <div id="game">
      <GachaScene v-if="current_scene===0"/>
      <MapScene v-if="current_scene===1"/>
      <StageScene v-if="current_scene===2"/>
    </div>
    <PartyPanel/>
  </div>
</template>

<script>
import GachaScene from "./GachaScene/GachaScene"
import MapScene from "./MapScene/MapScene"
import StageScene from "./StageScene/StageScene"
import ResultScene from "./ResultScene/ResultScene"
import PartyPanel from "./Common/UI/PartyPanel/PartyPanel"
import StatusBar from "./Common/UI/StatusBar/StatusBar"
import { game_controller } from "./Common/Core/GameController.js"
import { game_model } from "./Common/Core/GameModel.js"

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
  computed : {
    current_scene : function(){
      return game_model.state.current_game_state
    }
  },
  data : function(){
    return{
      party_panel_btntxt : "Go"
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

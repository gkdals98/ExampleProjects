<template>
  <div id="game">
    <IntroScene v-if="current_scene===0" @start-game="sceneGachaSet"/>
    <GachaScene v-if="current_scene===1" @settingEnd="scenePlaySet"/>
    <StageScene v-if="current_scene===2" @stageEnd="stageEnd"/>
  </div>
</template>

<script>
import IntroScene from "./IntroScene"
import GachaScene from "./GachaScene/GachaScene"
import StageScene from "./StageScene/StageScene"
import ResultScene from "./ResultScene/ResultScene"

const INTRO = 0
const GACHA = 1
const STAGE = 2

export default {
  name : 'GameFrame',
  components: {
    IntroScene,
    GachaScene,
    StageScene
  },
  data : function(){
    return{
      current_scene : INTRO
    }
  },
  methods:{
    sceneGachaSet : function(){
      this.current_scene = GACHA;
    },
    scenePlaySet : function(){
      this.current_scene = STAGE;
    },
    stageEnd : function(result){

    }
  }
}
</script>
<style scoped lang="scss">
#game {
  height: 100%;
}
</style>

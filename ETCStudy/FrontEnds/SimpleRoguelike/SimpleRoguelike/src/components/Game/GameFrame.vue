<template>
  <div id="game">
    <IntroScene v-if="current_scene===0" @start-game="sceneGachaSet"/>
    <GachaScene v-if="current_scene===1" @settingEnd="sceneBattleSet"/>
    <BattleScene v-if="current_scene===2" @settingEnd="CharSettingEnd"/>
  </div>
</template>

<script>
import IntroScene from "./IntroScene"
import GachaScene from "./GachaScene/GachaScene"
import BattleScene from "./BattleScene/BattleScene"

const INTRO = 0
const GACHA = 1
const BATTLE = 2
const EVENT = 3
const CLEAR = 4
const GAMEOVER = 5

export default {
  name : 'GameFrame',
  components: {
    IntroScene,
    GachaScene,
    BattleScene
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
    sceneBattleSet : function(){
      this.current_scene = BATTLE;
    }
  }
}
</script>
<style scoped lang="scss">
#game {
  height: 100%;
}
</style>

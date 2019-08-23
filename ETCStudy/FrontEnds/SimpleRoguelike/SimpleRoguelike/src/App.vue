<template>
  <div id="app">
    <Main v-if="cur_scene===0"/>
    <IntroScene v-if="cur_scene===1"/>
    <GameFrame v-if="cur_scene===2"/>
  </div>
</template>

<script>
import Main from "./components/MainMenu"
import IntroScene from "./components/IntroScene"
import GameFrame from "./components/Game/GameFrame"
import { game_controller } from "./components/Game/Common/Core/GameController.js"
import { game_model } from "./components/Game/Common/Core/GameModel.js"

export default {
  name: 'App',
  components: {
    Main,
    IntroScene,
    GameFrame
  },
  computed : {
    cur_scene : function(){
      return game_model.state.current_scene
    }
  }
}
</script>

<style scoped lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  /*Dragable False Block*/
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;

  color: #2c3e50;
  /*align center and size fix*/
  margin-left: auto;
  margin-right: auto;
  margin-top : 0px;
  width: 800px;
  height: 550px;
}
</style>

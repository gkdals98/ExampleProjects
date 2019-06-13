<template>
  <div id="app">
    <Main v-if="game===0" @start-intro="gameStartFromMain"/>
    <IntroScene v-if="game===1" @start-game="gameStartFromIntro"/>
    <GameFrame v-if="game===2" @end-game="gameEndFromGame"/>
  </div>
</template>

<script>
import Main from "./components/MainMenu"
import IntroScene from "./components/IntroScene"
import GameFrame from "./components/Game/GameFrame"

const MAIN = 0
const INTRO = 1
const GAME = 2

export default {
  name: 'App',
  components: {
    Main,
    IntroScene,
    GameFrame
  },
  data : function(){
    return {
      game : 0
    }
  },
  methods: {
    gameStartFromMain : function(){
      this.game = INTRO;
    },
    gameStartFromIntro : function(){
      this.game = GAME;
    },
    gameEndFromGame : function(result){
      this.game = MAIN;
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

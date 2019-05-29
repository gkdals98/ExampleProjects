<template>
  <div id="app">
    <Main v-if="game===false" @start="gameStart"/>
    <GameFrame v-if="game===true" @start-game="gameStartClicked"/>
  </div>
</template>

<script>
import Main from "./components/MainMenu"
import GameFrame from "./components/Game/GameFrame"

export default {
  name: 'App',
  components: {
    Main,
    GameFrame
  },
  data : function(){
    return {
      game : false
    }
  },
  methods: {
    gameStart : function(){
      this.game = true;
    },
    gameStartClicked : function(){
      this.game = false;
    }
  }
}
</script>

<style scoped lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-left: auto;
  margin-right: auto;
  width: 800px;
  min-height: 500px;
  height: 500px;
  max-height: 500px;
}
</style>

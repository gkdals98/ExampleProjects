<template>
  <div id="app">
    <Main v-if="game===false" @start="gameStart"/>
    <IntroScene v-if="game===true" @start-game="gameStartClicked"/>
    <IntroScene v-if="game===true" @start-game="gameStartClicked"/>
  </div>
</template>

<script>
import Main from "./components/MainMenu"
import IntroScene from "./components/Game/IntroScene"

export default {
  name: 'App',
  components: {
    Main,
    IntroScene
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

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>

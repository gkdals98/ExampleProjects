<template>
  <div id="app">
    <Main v-if="game===false" @start="gameStart"/>
    <Controller v-if="game===true" @end="gameEnd" display="none"/>
  </div>
</template>

<script>
import Main from './components/MainMenu'
import Controller from './components/GameControllFrame'

export default {
  name: 'App',
  components: {
    Main,
    Controller
  },
  data : function(){
    return {
      game : false
    }
  },
  methods: {
    gameStart : function(){
      this.game = true
      console.log("message start emited")
    },
    gameEnd : function(isCleaer){
      this.game = false
      console.log("message game end emited")
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

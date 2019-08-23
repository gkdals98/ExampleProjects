<template>
  <div id="main-menu">
    <h1>{{title}}</h1>
    <button v-on:click="startButtonClicked">Start</button>
  </div>
</template>

<script>
import { game_controller } from "./Game/Common/Core/GameController.js"

export default {
  name : 'Main',
  data : function(){
    return{
      title : "The Simple"
    }
  },
  methods:{
    startButtonClicked : function(){
      console.log("Start Game")
      game_controller.setIntroSceneAndClearData();
    }
  }
}
</script>
<style scoped lang="scss">
#main-menu {
  text-align: center;
  padding-top: 10px;
  color : #4984c6;
  background-color : #3d3d3d;
  font-size: 50px;
  height: 100%;
  h1{
    margin-top: 150px;
  }
  button {
    border-radius: 10px;
    border-style: none;
    margin-left: auto;
    margin-right: auto;
    background-color : #002e82;
    font-size: 20px;
    color : #FFFFFF;
    width: 130px;
    height: 50px;
  }
}
</style>

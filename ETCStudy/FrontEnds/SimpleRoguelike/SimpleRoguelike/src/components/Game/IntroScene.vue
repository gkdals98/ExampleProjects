<template>
  <div id="intro-scene">
    <h1>Down to the Dungeon...</h1>
    <button v-on:click="startButtonClicked">- Ok. -</button>
  </div>
</template>

<script>
export default {
  name : 'IntroScene',
  data : function(){
    return{
    }
  },
  methods:{
    startButtonClicked : function(){
      this.$emit('start-game')
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../common/css/fadein";
#intro-scene {
  padding-top: 10px;
  color : #d7dce2;
  background-color : #253349;
  font-size: 40px;
  height: 100%;
  button {
    background-color: Transparent;
    background-repeat:no-repeat;
    border : none;
    overflow: hidden;
    outline:none;
    font-size: 20px;
    color : #FFFFFF;
    width: 170px;
    height: 50px;
  }
  h1{
    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
  }
}
</style>

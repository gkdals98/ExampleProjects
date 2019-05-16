<template>
  <div id="intro-scene">
    <h1>Now, It's time to Down to the Dungeon....</h1>
    <button v-on:click="startButtonClicked">- Ok, Let's Go.. -</button>
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
  color : #d7dce2;
  margin-left: auto;
  margin-right: auto;
  padding-top: 80px;
  background-color : #253349;
  font-size: 40px;
  width: 800px;
  height: 500px;
  button {
    animation: 2.5s fadeInwithdelay;
    background-color: Transparent;
    background-repeat:no-repeat;
    border : none;
    overflow: hidden;
    outline:none;
    margin-bottom: 20px;
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

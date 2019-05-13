<template>
  <div id="frame">
    <h1>{{messageText}}</h1>
    <button v-on:click="startButtonClicked">- Ok, Let's Go.. -</button>
  </div>
</template>

<script>
export default {
  name : 'Controller',
  data : function(){
    return{
      messageText : "Now, It's time to Down to the Dungeon...."
    }
  },
  methods:{
    startButtonClicked : function(){
      this.$emit('end')
    }
  }
}
</script>

<style scoped lang="scss">
@import "../common/css/fadein";
#frame {
  color : #d7dce2;
  margin-left: auto;
  margin-right: auto;
  padding-top: 80px;
  background-color : #253349;
  font-size: 40px;
  width: 800px;
  height: 500px;
  button {
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

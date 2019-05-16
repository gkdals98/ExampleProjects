<template>
  <div id="main-menu">
    <h1>{{title}}</h1>
    <button v-on:click="startButtonClicked">Start</button>
  </div>
</template>

<script>
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
      this.$emit('start')
    }
  }
}
</script>
<style scoped lang="scss">
#main-menu {
  color : #1c6cff;
  margin-left: auto;
  margin-right: auto;
  padding-top: 80px;
  background-color : #dbe8ff;
  font-size: 50px;
  width: 800px;
  height: 500px;
  button {
    margin-bottom: 20px;
    background-color : #002e82;
    font-size: 20px;
    color : #FFFFFF;
    width: 130px;
    height: 50px;
  }
}
</style>

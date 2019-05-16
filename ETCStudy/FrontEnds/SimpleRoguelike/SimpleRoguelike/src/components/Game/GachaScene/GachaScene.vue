<template>
  <div id="frame">
    <h1>{{title}}</h1>
    <button v-on:click="startButtonClicked">Start</button>
  </div>
</template>

<script>
export default {
  name : 'Gacha',
  data : function(){
    return{
    }
  },
  methods:{
    startButtonClicked : function(){
      this.$emit('start')
    }
  }
}
</script>
<style scoped lang="scss">
#frame {
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

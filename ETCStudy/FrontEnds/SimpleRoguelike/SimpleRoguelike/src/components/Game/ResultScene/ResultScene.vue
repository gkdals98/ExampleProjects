<template>
  <div id="frame">
    <button v-on:click="startButtonClicked">Lets Go</button>
  </div>
</template>

<script>
export default {
  name : 'ResultScene',
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
  background-color : #333333;
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

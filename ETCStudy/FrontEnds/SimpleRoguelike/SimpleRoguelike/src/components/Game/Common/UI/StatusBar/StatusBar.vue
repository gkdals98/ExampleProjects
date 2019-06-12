<template>
  <div id="status_bar">
    <div class="status_item">{{stex}}</div>
  </div>
</template>

<script>

export default {
  name : 'StatusBar',
  data : function(){
    return{
      stex : 0
    }
  },
  methods:{
    startButtonClicked : function(){
    }
  }
}
</script>
<style scoped lang="scss">
#status_bar {
  display: flex;
  justify-content: space-around;
  background-color : #3d3d3d;
  color : #FFFFFF;
  width: 800px;
  height: 35px;
  .status_item{
    display: inline-block;
    float:left;
    text-align: center;
  }
}
</style>

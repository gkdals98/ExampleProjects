<template>
  <div id="status_bar" class="default-ui rounded">
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
@import "../../../../../common/css/theme_template";
#status_bar {
  display: flex;
  justify-content: space-around;
  width: 800px;
  height: 40px;
}
</style>

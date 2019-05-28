<template>
  <div id="gacha_main">
  </div>
</template>

<script>
export default {
  name : 'GachaMain',
  data : function(){
    return{
    }
  },
  methods:{
    singlegachaButtonClicked : function(){
      this.$emit('single')
    },
    fivegachaButtonClicked : function(){
      this.$emit('five')
    }
  }
}
</script>
<style scoped lang="scss">
#gacha_main {
  background-color : #4286f4;
  height: 100%;
  width: 100%;
}
</style>

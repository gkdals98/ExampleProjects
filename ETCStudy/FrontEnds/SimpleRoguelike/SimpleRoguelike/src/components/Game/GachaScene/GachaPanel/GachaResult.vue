<template>
  <div id="frame">
  </div>
</template>

<script>
export default {
  name : 'GachaResult',
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
  background-color : #bc35f2;
  height: 100%;
  width: 100%;
}
</style>

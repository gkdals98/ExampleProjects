<template>
  <span id="gacha_char_panel">
  </span>
</template>

<script>
export default {
  name : 'GachaCharPanel',
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
#gacha_char_panel {
  background-color : #879ab7;
  width: 120px;
  height: 120px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
}
</style>

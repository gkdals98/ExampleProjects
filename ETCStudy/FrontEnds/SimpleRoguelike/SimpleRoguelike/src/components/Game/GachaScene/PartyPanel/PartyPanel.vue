<template>
  <div id="party_panel">
  </div>
</template>

<script>
export default {
  name : 'PartyPanel',
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
#party_panel {
  background-color : #879ab7;
  width: 800px;
  height: 110px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
}
</style>

<template>
  <div id="gacha_result">
    <div id="results">
      <GachaResultSlot/>
      <GachaResultSlot/>
      <GachaResultSlot/>
      <GachaResultSlot/>
      <GachaResultSlot/>
    </div>
    <GachaButtons/>
  </div>
</template>

<script>
import GachaButtons from "./GachaButtons"
import GachaResultSlot from "./GachaResultSlot";

export default {
  name : 'GachaResult',
  data : function(){
    return{
    }
  },
  components: {
    GachaResultSlot,
    GachaButtons
  },
  methods:{
    startButtonClicked : function(){
      this.$emit('start')
    }
  }
}
</script>
<style scoped lang="scss">
#gacha_result {
  background-color : #cecece;
  height: 100%;
  width: 100%;
  #results {
    position : relative;
    padding-left: 5px;
    padding-top: 5px;
  }
}
</style>

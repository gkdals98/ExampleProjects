<template>
  <div id="ghacha_scene">
    <GachaPanel @tryGacha="tryGacha" @selectCharacter="selectCharacter"/>
    <PartyPanel @settingEnd=""/>
  </div>
</template>

<script>
import GachaPanel from "./GachaPanel/GachaPanel"
import PartyPanel from "./PartyPanel/PartyPanel"

export default {
  name : 'GachaScene',
  components: {
    GachaPanel,
    PartyPanel
  },
  data : function(){
    return{
    }
  },
  methods:{
    tryGacha : function(){
      this.$emit('start')
    },
    selectCharacter : function(){
      this.$emit('start')
    }
  }
}
</script>
<style scoped lang="scss">
#ghacha_scene {
  margin-left: auto;
  margin-right: auto;
  padding-top: 10px;
  background-color : #333333;
  width: 800px;
  height: 500px;
}
</style>

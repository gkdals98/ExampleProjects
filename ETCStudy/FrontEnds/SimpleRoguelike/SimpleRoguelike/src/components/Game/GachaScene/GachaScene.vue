<template>
  <div id="gacha_scene">
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
@import "../../../common/css/size-template";
#gacha_scene {
  height: 100%;
  padding-top: 10px;
  background-color : #333333;
}
</style>

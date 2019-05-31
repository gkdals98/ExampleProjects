<template>
  <div id="gacha_scene">
    <GachaPanel @tryGacha="tryGacha" @selectCharacter="selectCharacter" v-bind:jewel="jewel"/>
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
      jewel : 5
    }
  },
  methods:{
    tryGacha : function(){
    },
    selectCharacter : function(){
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../common/css/size-template";
#gacha_scene {
  height: 100%;
  padding-top: 10px;
  background-color : #000000;
}
</style>

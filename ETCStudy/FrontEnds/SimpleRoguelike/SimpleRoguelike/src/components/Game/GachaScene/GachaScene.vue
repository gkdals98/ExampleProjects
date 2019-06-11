<template>
  <div id="gacha_scene">
    <StatusBar/>
    <GachaPanel @tryGacha="tryGacha" @selectCharacter="selectCharacter" v-bind:jewel="jewel"/>
    <PartyPanel @settingEnd="tryMoveToDungeon"/>
  </div>
</template>

<script>
import GachaPanel from "./GachaPanel/GachaPanel"
import PartyPanel from "./PartyPanel/PartyPanel"
import StatusBar from "../Common/StatusBar"

export default {
  name : 'GachaScene',
  components: {
    StatusBar,
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
    },
    tryMoveToDungeon : function(){
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../common/css/size-template";
#gacha_scene {
  padding-top: 10px;
  height: 100%;
  background-color : #3d3d3d;
}
</style>

<template>
  <div id="gacha_scene">
    <GachaMain v-if="current_panel===0" @single="tryGacha" v-bind:jewel="jewel"/>
    <GachaResult v-if="current_panel===1" v-bind:jewel="jewel"/>
  </div>
</template>

<script>
import GachaMain from "./GachaPanel/GachaMain"
import GachaResult from "./GachaPanel/GachaResult"

const MAIN = 0
const RESULT = 1

export default {
  name : 'GachaScene',
  components: {
    GachaMain,
    GachaResult
  },
  data : function(){
    return{
      current_panel : RESULT,
      jewel : 5
    }
  },
  methods:{
    tryGacha : function(amount){
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../common/css/size-template";
#gacha_scene {
  margin-left: auto;
  margin-right: auto;
  width: 780px;
  height: 100%;
  background-color : #3d3d3d;
}
</style>

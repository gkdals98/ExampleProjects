<template>
  <div id="gacha_panel">
    <GachaMain v-if="current_panel===0" @start-game="sceneGachaSet"/>
    <GachaResult v-if="current_panel===1" @settingEnd="scenePlaySet"/>
  </div>
</template>

<script>
import GachaMain from "./GachaMain"
import GachaResult from "./GachaResult"

const MAIN = 0
const RESULT = 1

export default {
  name : 'GachaPanel',
  data : function(){
    return{
      current_panel : MAIN
    }
  },
  components: {
      GachaMain,
      GachaResult
  },
  methods:{
    startButtonClicked : function(){
      this.$emit('start')
    }
  }
}
</script>
<style scoped lang="scss">
#gacha_panel {
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 10px;
  width: 780px;
  height: 380px;
}
</style>

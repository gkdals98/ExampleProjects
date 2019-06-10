<template>
  <div id="gacha_panel">
    <GachaMain v-if="current_panel===0" @single="trysingle" @five="tryfive" v-bind:jewel="jewel"/>
    <GachaResult v-if="current_panel===1" @settingEnd="scenePlaySet" v-bind:jewel="jewel"/>
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
      defjewel: this.jewel,
      current_panel : RESULT
    }
  },
  props:[
    'jewel',
  ],
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
  margin-top: 5px;
  margin-bottom: 10px;
  width: 780px;
  height: 370px;
}
</style>

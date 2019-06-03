<template>
  <div id="party_panel">
    <div id="buttons">
    <GachaCharPanel v-bind:char="slot1"/>
    <GachaCharPanel v-bind:char="slot2"/>
    <GachaCharPanel v-bind:char="slot3"/>
    <GachaCharPanel v-bind:char="slot4"/>
    <GachaCharPanel v-bind:char="slot5"/>
    <button id="next_button">Go</button>
  </div>
  </div>
</template>

<script>
import GachaCharPanel from "./GachaCharPanel";

export default {
  name : 'PartyPanel',
  data : function(){
    return{
      slot1 : "A",
      slot2 : "B",
      slot3 : "C",
      slot4 : "D",
      slot5 : "E",
    }
  },
  components: {
    GachaCharPanel,
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
  height: 130px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
  #buttons{
    padding-left: 5px;
    padding-top: 5px;
    #next_button{
      font-size: 40px;
      width: 160px;
      height: 120px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    }
  }
}
</style>

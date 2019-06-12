<template>
  <div id="party_panel">
    <GachaCharPanel v-bind:char="slot1"/>
    <GachaCharPanel v-bind:char="slot2"/>
    <GachaCharPanel v-bind:char="slot3"/>
    <GachaCharPanel v-bind:char="slot4"/>
    <GachaCharPanel v-bind:char="slot5"/>
    <button class="btn btn-default-ui" id="next_button">Go</button>
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
@import "../../../../../common/css/theme_template";
#party_panel {
  display: -webkit-flex; /* Safari */
  -webkit-flex-flow: row wrap; /* Safari 6.1+ */
  display: flex;
  flex-flow: row wrap;
  background-color : #879ab7;
  width: 800px;
  height: 130px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
  #next_button{
    margin-top : 5px;
    margin-left: 5px;
    font-size: 40px;
    width: 160px;
    height: 120px;
  	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
  }
}
</style>

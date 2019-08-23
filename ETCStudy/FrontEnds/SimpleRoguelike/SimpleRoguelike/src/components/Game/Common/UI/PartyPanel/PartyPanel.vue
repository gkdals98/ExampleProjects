<template>
  <div id="party_panel">
    <GachaCharPanel v-bind:char="slot1"/>
    <GachaCharPanel v-bind:char="slot2"/>
    <GachaCharPanel v-bind:char="slot3"/>
    <GachaCharPanel v-bind:char="slot4"/>
    <GachaCharPanel v-bind:char="slot5"/>
    <button class="btn default-ui rounded" id="next_button" v-on:click="StartButtonClicked" v-bind:clickable="isClickableNext">{{btnsign}}</button>
  </div>
</template>

<script>
import GachaCharPanel from "./GachaCharPanel";
import { game_model } from "../../Core/GameModel.js";

export default {
  name : 'PartyPanel',
  data : function(){
    return{
      slot1 : "A",
      slot2 : "B",
      slot3 : "C",
      slot4 : "D",
      slot5 : "E"
    }
  },
  computed : {
    isClickableNext : function(){
      return game_model.state.next_button_state
    },
    btnsign : function(){

    }
  }
  components: {
    GachaCharPanel,
  },
  methods:{
    //여기 사인을 판단하고 머하고 있으면 종합판정 99 에바에요. 이건 뷰입니다.
    //애초에 버튼 텍스트를 가지고 이걸 판단하고 있다는 것 자체가...
    //고로 추후 반드시 이 부분도 수정해야한다.
    StartButtonClicked : function(){
      game_controller.getNextState();
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
  margin-top: 5px;
  width: 800px;
  height: 130px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
  #next_button{
    margin-top : 5px;
    margin-left: 5px;
    font-size: 40px;
    width: 160px;
    height: 120px;
  }
}
</style>

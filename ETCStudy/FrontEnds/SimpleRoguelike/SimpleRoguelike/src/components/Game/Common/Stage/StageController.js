import { stage_model } from './StageModel.js';

class StageController{
  constructor(){
    this.name = "Stage Controller";
  }
}
/**
*
*  export
*
**/

const BATTLE = 0
const EVENT = 1


//진행 버튼 입력이 들어올 시 어디로 진행할지를 판단.
StageController.prototype.tryProgress = function(){
  console.log(this.name + " : Try Progress.");
}

//게임 데이터 완전 초기화. game의 종료, 시작부분에서만 수행한다.
//모든 초기화 수행
StageController.prototype.progressPhase = function(stage){
  console.log(this.name + " : Start Gacha Scene.");
  if(stage_model.state.current_phase < 2){
    stage_model.commit("phaseUp");
  }

}

export let stage_controller = new StageController();

//import { stage_model } from './StageModel.js';

class ResultController{
  constructor(){
    this.name = "Result Controller";
  }
}
/**
*
*  export
*
**/

//진행 버튼 입력이 들어올 시 어디로 진행할지를 판단.
ResultController.prototype.tryProgress = function(){
  console.log(this.name + " : Try Progress.");
}

//게임 데이터 완전 초기화. game의 종료, 시작부분에서만 수행한다.
//모든 초기화 수행
ResultController.prototype.setResult = function(result){
  console.log(this.name + " : Set Result.");
}

export let result_controller = new ResultController();

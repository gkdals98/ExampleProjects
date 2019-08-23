//import { stage_model } from './StageModel.js';

class GachaController{
  constructor(){
    this.name = "Gacha Controller";
  }
}
/**
*
*  export
*
**/

const BANNER = 0
const RESULT = 1

//진행 버튼 입력이 들어올 시 어디로 진행할지를 판단.
GachaController.prototype.tryProgress = function(){
  console.log(this.name + " : Try Progress.");
}
//게임 데이터 완전 초기화. game의 종료, 시작부분에서만 수행한다.
//모든 초기화 수행
GachaController.prototype.tryGacha = function(){
  console.log(this.name + " : Start Gacha Scene.");

}

export let gacha_controller = new GachaController();

//import { stage_model } from './StageModel.js';
import {character_repository} from '../Repository/CharacterRepository.js'

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

//가챠 베너와 결과 Pane의 출력을 조정하기 위한 값들.
const BANNER = 0
const RESULT = 1

//Game Controller에게 진행을 알리는 건
const GAME_MAP = 1

//진행 버튼 입력이 들어올 시 어디로 진행할지를 판단.
GachaController.prototype.tryProgress = function(){
  console.log(this.name + " : Try Progress.");
  return GAME_MAP;
}
//가챠 시도
GachaController.prototype.tryGacha = function(){
  console.log(this.name + " : Start Gacha Scene.");
}
//가챠에서 획득한 캐릭터를 파티 Pane에 넣을 때, 파티 Pane의 데이터와 교체하기 위한 Data를 준비한다.
GachaController.prototype.setPartyPaneReadyData = function(){
  console.log(this.name + " : Set Character Entry - ");

}

export let gacha_controller = new GachaController();

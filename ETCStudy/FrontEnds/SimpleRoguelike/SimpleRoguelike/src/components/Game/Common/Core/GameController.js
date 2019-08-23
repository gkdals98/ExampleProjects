import { game_model } from './GameModel.js';
import { gacha_controller } from '../Gacha/GachaController.js';
import { dungeon_controller } from '../Dungeon/DungeonController.js';
import { stage_controller } from '../Stage/StageController.js';
import { result_controller } from '../Result/ResultController.js';

class GameController{
  constructor(){
    this.name = "Game Controller";
  }
}

const MAIN = 0
const INTRO = 1
const GAME = 2

const GAME_GACHA = 0
const GAME_MAP = 1
const GAME_STAGE = 2
const GAME_RESULT = 3
/**
*
*  export
*
**/

//게임 데이터 완전 초기화. game의 종료, 시작부분에서만 수행한다.
//모든 초기화 수행
GameController.prototype.cleanData = function(stage){
  console.log(this.name + " : Start Gacha Scene.");
  dungeon_controller.clearDungeon();
}

//1. 메인 메뉴로 돌아감
//game_state를 0으로 되돌리고 scene을 메인 메뉴로
GameController.prototype.returnToMainMenuScene = function(){
  console.log(this.name + " : Return to Main Menu.");
  game_model.commit('setScene', MAIN);
}

//2. Intro를 재생하며 게임 데이터를 Clear
//scene을 Intro로
GameController.prototype.setIntroSceneAndClearData = function(){
  console.log(this.name + " : Start Intro Scene with Clear data.");
  this.cleanData();
  game_model.commit('setScene', INTRO);
}

//3. Game 시작.
//여기서 GachaScene 실행만 하고 데이터 초기화는 하지 말자. 짜피 게임 끝나면 초기화 하니까.
GameController.prototype.startGame = function(){
  console.log(this.name + " : Initialization for start Game.");
  game_model.commit('setScene', GAME);
  this.stageUpAndSetGachaScene();
}

//Next Button의 활성화 여부. Scene 전환시엔 기본적으로 False이다.
GameController.prototype.setNextButtonState = function(next_button_state){
  console.log(this.name + " : Set Next Button " + next_button_state);
  game_model.commit('setNextButtonClickable', next_button_state);
}

//4. Game의 다음 장면을 결정하는 메서드.
//인터페이스 도입해서 할라다가 더 꼬일 것 같아 그냥 if처리 하기로 함.
GameController.prototype.getNextState = function(){
  console.log(this.name + " : get next state.");
  if(game_model.state.current_game_state===GAME_GACHA){
    gacha_controller.tryProgress();
  }else if(game_model.state.current_game_state===GAME_MAP){
    dungeon_controller.tryProgress();
  }else if(game_model.state.current_game_state===GAME_STAGE){
    stage_controller.tryProgress();
  }else if(game_model.state.current_game_state===GAME_RESULT){
    result_controller.tryProgress();

  }

}

//4. 던전 생성 및 가챠 Scene 로드
//Gacha Scene을 통한 캐릭터 획득은 층이 넘어갈 때에만 활성화되니 Dungeon도 Clear시킨다.
GameController.prototype.stageUpAndSetGachaScene = function(){
  console.log(this.name + " : Start Gacha Scene.");
  this.setNextButtonState(false);
  game_model.commit('stageUp');
  dungeon_controller.clearDungeon();
  game_model.commit('progressGame', GAME_GACHA);
}

//5. Map을 생성하며 MapScene을 로드하는 스테이지 첫 진입 시 사용하는 메서드.
//던전 생성 및 맵 활성화
GameController.prototype.createMapScene = function(){
  console.log(this.name + " : Get in to Stage " + game_model.state.current_stage);
  this.setNextButtonState(false);
  dungeon_controller.createDungeon(game_model.state.current_stage);
  game_model.commit('progressGame', GAME_MAP);
}

//6. 전투를 마치고 스테이지에 진입할 때는 Scene 전환만 일어나고 Map은 생성하지 않는다.
//기존에 있던 맵 활성화.
GameController.prototype.returnToMapScene = function(){
  this.setNextButtonState(false);
  console.log(this.name + " : return to Map.");
  game_model.commit('progressGame', GAME_MAP);
}

//7. node를 이용해 Stage Scene에 이벤트를 활성화한다.
GameController.prototype.setStageScene = function(){
  this.setNextButtonState(false);
  console.log(this.name + " : get in to Encounter.");
  game_model.commit('progressGame', GAME_STAGE);
}

//8. result를 표시하고... Game 끝날지 아닐지를 정해야함. score도 보여줘야함.
//이 정보를 어떻게 관리할지가 하나의 관건이다. result는 어디서 전달받는가. 생각해보면...
GameController.prototype.setResultScene = function(){
  this.setNextButtonState(false);
  console.log(this.name + " : set Result Scene.");
}



export let game_controller = new GameController();

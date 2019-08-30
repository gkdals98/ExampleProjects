

class CharacterRepository{
  constructor(){
    this.name = "Character Repository";
  }
}
/**
*
*  export
*
**/

//전체 데이터를 읽어들인다.
CharacterRepository.prototype.getWholeData = function(){
}
//픽업캐릭터 Data를 읽어들인다.
CharacterRepository.prototype.getPickupData = function(){
}
//Super rare 캐릭터의 Data를 읽어들인다.
CharacterRepository.prototype.getSuperRareData = function(){
}
//rare 캐릭터의 Data를 읽어들인다.
CharacterRepository.prototype.getRareData = function(){
}
//Common 캐릭터의 Data를 읽어들인다.
CharacterRepository.prototype.getCommonData = function(){
}

export let character_repository = new CharacterRepository();

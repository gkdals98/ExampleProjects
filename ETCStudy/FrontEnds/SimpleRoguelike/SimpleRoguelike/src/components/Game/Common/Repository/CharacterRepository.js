class CharacterRepository{
  constructor(){
    const const_char_profiles = require('./StaticCharacterData.json')
    this.char_profiles = const_char_profiles;
    this.getWholeData();
  }
}
/**
*
*  export
*
**/

//전체 데이터를 참조한다.
CharacterRepository.prototype.getWholeData = function(){
  console.log(this.char_profiles)
}
//픽업캐릭터 Data를 참조한다.
CharacterRepository.prototype.getPickupData = function(){
}
//Super rare 캐릭터의 Data를 참조한다.
CharacterRepository.prototype.getSuperRareData = function(){
}
//rare 캐릭터의 Data를 참조한다.
CharacterRepository.prototype.getRareData = function(){
}
//Common 캐릭터의 Data를 참조한다.
CharacterRepository.prototype.getCommonData = function(){
}

export let character_repository = new CharacterRepository();

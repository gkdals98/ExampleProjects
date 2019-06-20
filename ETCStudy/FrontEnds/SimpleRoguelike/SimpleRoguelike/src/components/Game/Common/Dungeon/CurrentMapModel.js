class CurrentMapModel{
  constructor(){
    this.a = 10;
  }
}

CurrentMapModel.prototype.b = function(){
  this.a++;
}
export let map_model = new CurrentMapModel();

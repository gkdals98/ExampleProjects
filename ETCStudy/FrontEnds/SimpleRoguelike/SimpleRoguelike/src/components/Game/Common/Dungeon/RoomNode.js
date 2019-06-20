export class RoomNode {
  constructor(x, y){
    this.x = x;
    this.y = y;
    RoomNode.prototype.b = function(){
      this.x++;
    }
  }
}

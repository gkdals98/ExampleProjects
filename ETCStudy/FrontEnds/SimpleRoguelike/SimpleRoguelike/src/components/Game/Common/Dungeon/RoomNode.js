export class RoomNode {
  constructor(x, y){
    this.x = x;
    this.y = y;
    this.next_node = [];
    RoomNode.prototype.b = function(){
    }
    RoomNode.prototype.setNext = function(next_node){
      if ( next_node instanceof RoomNode ) {
        this.next_node.add(next_node);
      }
    }
  }
}

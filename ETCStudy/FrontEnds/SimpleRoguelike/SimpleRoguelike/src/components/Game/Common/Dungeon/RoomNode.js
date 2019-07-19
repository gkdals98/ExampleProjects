export default class RoomNode {
  constructor(x, y){
    this.position_x = x;
    this.position_y = y;
    this.next_node_array_xy = [];
    this.room_setting = null;
    this.active = false;

    //다음 노드의 배열상 좌표를 얻어온다.
    RoomNode.prototype.addNext = function(array_x, array_y){
      this.next_node_array_xy.push(
        {array_x, array_y}
      )
    }
  }
}

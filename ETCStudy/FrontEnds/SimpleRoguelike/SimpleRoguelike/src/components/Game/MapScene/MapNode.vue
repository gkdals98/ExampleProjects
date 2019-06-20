<template>
  <button id="node_button" class="btn-node" v-bind:style="{top : toppx, left : leftpx}"></button>
</template>

<script>

export default {
  name : 'MapNode',
  methods:{
    whenClickThis : function(){
    }
  },
  props: {
    x : Number,
    y : Number
  },
  data : function(){
    return {
      toppx : this.x + "px",
      leftpx : this.y + "px"
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../common/css/theme_template";
#node_button{
  position : absolute;
  border-radius: 50%;
  height: 40px;
  width: 40px;
}
</style>

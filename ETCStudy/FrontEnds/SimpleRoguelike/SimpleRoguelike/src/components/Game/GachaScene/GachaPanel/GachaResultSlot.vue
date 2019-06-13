<template>
  <button class="btn ui-char rounded">
    {{char}}
  </button>
</template>

<script>
export default {
  name : 'GachaResultSlot',
  data : function(){
    return{
    }
  },
  props : [
      'char'
  ],
  methods:{
    startButtonClicked : function(){
      this.$emit('start')
    }
  }
}
</script>
<style scoped lang="scss">
@import "../../../../common/css/theme_template";
button {
  width: 150px;
  height: 270px;
}
</style>

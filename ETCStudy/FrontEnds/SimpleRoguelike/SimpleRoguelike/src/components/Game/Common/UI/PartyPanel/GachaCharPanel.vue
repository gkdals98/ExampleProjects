<template>
  <div class="ui-char">
    {{char}}
  </div>
</template>

<script>
export default {
  name : 'GachaCharPanel',
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
@import "../../../../../common/css/theme_template";
div {
  margin-top : 5px;
  margin-left: 5px;
  font-size: 40px;
  width: 120px;
  height: 120px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
}
</style>

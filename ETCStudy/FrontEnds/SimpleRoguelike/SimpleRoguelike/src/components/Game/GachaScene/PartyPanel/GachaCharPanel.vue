<template>
  <button>
    {{char}}
  </button>
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
button {
  font-size: 40px;
  background-color : #3d3d3d;
  color : #4984c6;
  width: 120px;
  height: 120px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
}
</style>

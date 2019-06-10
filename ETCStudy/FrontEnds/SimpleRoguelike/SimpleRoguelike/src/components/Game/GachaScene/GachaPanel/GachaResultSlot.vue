<template>
  <button>
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
button {
  font-size: 40px;
  background-color : #3d3d3d;
  width: 150px;
  height: 270px;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
}
</style>

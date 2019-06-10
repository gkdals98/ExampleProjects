<template>
  <div id="gacha-buttons">
    <button v-on:click="singlegachaButtonClicked">1</button>
    <button v-on:click="fivegachaButtonClicked">5</button>
  </div>
</template>

<script>
import bus from '../../bus'
export default {
  name : 'GachaButtons',
  data : function(){
    return{
    }
  },
  methods:{
    singlegachaButtonClicked : function(){
      bus.$emit('single')
    },
    fivegachaButtonClicked : function(){
      bus.$emit('five')
    }
  }
}
</script>
<style scoped lang="scss">
#gacha-buttons{
  height: 80px;
  width : 300px;
  padding-left: 490px;
  padding-top: 10px;
  button{
     font-family:Arial;
     border-style: none;
     border-radius: 10px;
     background-color : #002e82;
     color : #FFFFFF;
     font-size: 40px;
     height: 100%;
     width : 130px;
  }
  button:hover{
  	background-color:#001e62;
  }
}
</style>

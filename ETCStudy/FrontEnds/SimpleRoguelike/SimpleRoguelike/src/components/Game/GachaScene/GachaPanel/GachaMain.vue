<template>
  <div id="gacha_main">
    <div id="gacha-buttons">
      <button v-on:click="singlegachaButtonClicked">1</button>
      <button v-on:click="fivegachaButtonClicked">5</button>
    </div>
  </div>
</template>

<script>
export default {
  name : 'GachaMain',
  data : function(){
    return{
      defjewel: this.jewel
    }
  },
  props:[
    'jewel',
  ],
  methods:{
    singlegachaButtonClicked : function(){
      if(this.defjewel < 1){
      }
      this.$emit('single')
    },
    fivegachaButtonClicked : function(){
      if(this.defjewel < 5){
      }
      this.$emit('five')
    }
  }
}
</script>
<style scoped lang="scss">
#gacha_main {
  background-color : #cecece;
  height: 100%;
  width: 100%;
  #gacha-buttons{
    height: 80px;
    width : 300px;
    padding-left: 450px;
    padding-top: 310px;
    button{
	     font-family:Arial;
       border-style: none;
       border-radius: 10px;
       background-color : #ce4252;
       font-size: 40px;
       height: 100%;
       width : 130px;
	     text-shadow:0px 2px 0px #db7d7d;
    }
    button:hover{
    	background-color:#ff0c28;

    }
  }
}
</style>

<template>
  <div id="gacha-buttons">
    <button class="btn btn-emphasis rounded" v-on:click="singlegachaButtonClicked">1</button>
    <button class="btn btn-emphasis rounded" v-on:click="fivegachaButtonClicked">5</button>
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
@import "../../../../common/css/theme_template";

#gacha-buttons{
  height: 80px;
  width : 300px;
  padding-left: 490px;
  padding-top: 10px;
  button{
     font-size: 40px;
     height: 100%;
     width : 130px;
  }
  button:hover{
  	background-color:#001e62;
  }
}
</style>

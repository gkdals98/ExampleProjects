<template>
  <div id="gacha_main">
    <div id="pickup_image">
    </div>
    <GachaButtons/>
  </div>
</template>

<script>
import GachaButtons from "./GachaButtons"
export default {
  name : 'GachaMain',
  data : function(){
    return{
    }
  },
  components: {
      GachaButtons
  },
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
  #pickup_image {
    position : relative;
    padding-left: 5px;
    padding-top: 5px;
    width: 100%;
    height: 271px;
  }
}
</style>

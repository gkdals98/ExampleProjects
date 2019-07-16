<template>
  <div width="800" height="370">
    <canvas id="map_line" ref="map_line_canvas" width="800" height="370"></canvas>
    <slot></slot>
  </div>
</template>

<script>
export default {
  name : 'MapLineCanvas',
  data() {
    return {
      provider: {
        context: null
      }
    }
  },

  provide () {
    return {
      provider: this.provider
    }
  },

  mounted () {
    this.provider.context = this.$refs['map_line_canvas'].getContext('2d');
    this.provider.context.lineWidth = 5;
  }
}
</script>
<style scoped lang="scss">
#map_line{
  background-color: #524d69;
}
</style>

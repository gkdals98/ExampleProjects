<script>
export default {
  name : 'MapLine',
  inject: ['provider'],
  props: {
    x1: {
      type: Number,
      default: 0
    },
    y1: {
      type: Number,
      default: 0
    },
    x2: {
      type: Number,
      default: 0
    },
    y2: {
      type: Number,
      default: 0
    },

    color: {
      type: String,
      default: '#F00'
    }
  },

  computed: {
    lx1 : function(){
      return this.x1+20
    },
    ly1 : function(){
      return this.y1+20
    },
    lx2 : function(){
      return this.x2+20
    },
    ly2 : function(){
      return this.y2+20
    }
  },

  render () {
    // Since the parent canvas has to mount first, it's *possible* that the context may not be
    // injected by the time this render function runs the first time.
    if(!this.provider.context) return;
    const ctx = this.provider.context;
    ctx.lineWidth = 5;
    ctx.moveTo(this.lx1, this.ly1);
    ctx.lineTo(this.lx2, this.ly2);
    ctx.stroke();
  }
}
</script>

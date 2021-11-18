<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  created(){
    window.addEventListener("beforeunload",()=>{
      localStorage.setItem("messageStore",JSON.stringify(this.$store.state.pathparams))
    })
  },
}
</script>
<style>

</style>

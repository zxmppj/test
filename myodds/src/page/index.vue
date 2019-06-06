<template>
  <div class="center">
    <img src="/static/image/logo.png" class="indexImg" alt="网站名称图片"/>
    <p class="normSubtitleColor indexSubtitle">网站介绍</p>
    <div class="indexInputGroup">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {};
    },
    methods: {}
  };
</script>

<style>
  @import "../style/scss/_index.scss";
</style>

<template>
  <div id="app">
    <navigation></navigation>
    <router-view></router-view>
    <copyright></copyright>
  </div>
</template>

<script>
  import navigation from './components/navigation'
  import copyright from './components/copyright'

  export default {
    name: 'App',
    components: {
      navigation,
      copyright
    },
    created() {
      const localToken = localStorage.getItem('token');
      const sessionToken = sessionStorage.getItem('token');
      if (localToken !== null || sessionToken !== null) {
        const token = localToken === null ? sessionToken : localToken;
        this.$api.setToken(token);
      }
    }
  }
</script>

<style lang="scss">
  @import "./style/style.scss";
  @import "./style/base/_color.scss";
  @import "./style/base/_utils.scss";
</style>

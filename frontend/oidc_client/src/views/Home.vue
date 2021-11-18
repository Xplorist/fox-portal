<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <button v-on:click="logout()">logout</button>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },
  data: function () {
    return {
       userInfo: '',
      access_token: '',
      token_type: '',
      id_token: '',
      expires_in: '',
      state: '',
    };
  },
  computed: {

  },
  methods: {
    logout () {
      window.location.href = "http://10.244.168.180/openid/endsession?id_token_hint=" + this.id_token + 
      '&post_logout_redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fportal%2Fopenid_connect_login' + 
      '&state=' + this.state;
    },
  },
  created: function () {
      this.access_token = sessionStorage.getItem('access_token');
      this.id_token = sessionStorage.getItem('id_token');
      this.state = sessionStorage.getItem('state');
  }
}
</script>

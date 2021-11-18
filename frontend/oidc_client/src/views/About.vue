<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template>

<script>
export default {
  name: 'About',
  props: {
    msg: String
  },
  data: function () {
    return {
      userInfo: '',
      access_token: '',
    };
  },
  computed: {
    
  },
  methods: {
    authorize () {
      window.location.href = 'http://10.244.168.180/openid/authorize?response_type=token%20id_token' +
    '&client_id=MacIPortal' +
    '&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fportal%2Fopenid_connect_login' +
    '&scope=openid%20profile' + 
    '&state=' + Math.round(Math.random()*1e14 + 1e15).toString(16) +
    '&nonce=' + Math.round(Math.random()*1e14 + 1e15).toString(16);
    },
  },
  created: function () {
    this.authorize();
  }
}
</script>
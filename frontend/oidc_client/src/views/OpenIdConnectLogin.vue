<template>
  <div class="about">
    <h1>This is an OpenIdConnectLogin page</h1>
  </div>
</template>

<script>
export default {
  name: 'OpenIdConnectLogin',
  props: {
    msg: String
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
    getUrlParam (param) {
      var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
      var r = window.location.hash.substr(1).match(reg);
      if (r != null) return decodeURI(r[2]);
      return null;
    },
    getUserInfo () {
        this.axios({
          method: 'GET',
          url: '/portal/userInfo/loginUser',
          headers: {'Authorization': 'Bearer ' + this.access_token},
          withCredentials: true
        }).then(response => {
          window.console.log(response.data);
          let result = response.data;
          if (result.code != '1') {
            alert('未登录或登录失效，请登录');
            this.$router.push('authorize');
          } else {
            window.console.log(result.msg);
            this.userInfo = result.data;

            sessionStorage.setItem('userInfo', this.userInfo);
            sessionStorage.setItem('access_token', this.access_token);
            sessionStorage.setItem('token_type', this.token_type);
            sessionStorage.setItem('id_token', this.id_token);
            sessionStorage.setItem('expires_in', this.expires_in);
            sessionStorage.setItem('state', this.state);

            alert('登录成功');
            this.$router.push('/');
          }
        });
    },
  },
  created: function () {
    this.access_token = this.getUrlParam('access_token');
    this.token_type = this.getUrlParam('token_type');
    this.id_token = this.getUrlParam('id_token');
    this.expires_in = this.getUrlParam('expires_in');
    this.state = this.getUrlParam('state');

    if (this.access_token === null || this.access_token === '') {
      alert('没有access_token');
      this.$router.push('authorize');
    } else {
      this.getUserInfo();
    }
  }
}
</script>
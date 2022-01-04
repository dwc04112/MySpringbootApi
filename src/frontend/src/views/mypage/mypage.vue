<template>
  <div>
    <mypage-navi></mypage-navi>

    <v-card
        class="float-left"
        height="512"
        width="512"
        style="margin-top:5%;margin-left: 2%"
        color="#F2DDC1"
    >
      <p>MyPage Main</p>
      <span style="font-weight: bolder">{{this.$store.state.userStore.nickName}}</span><span> 님 환영합니다</span>
    </v-card>
  </div>
</template>

<script>


import MypageNavi from "@/views/mypage/mypageNavi";
export default {
  name:"mypage",
  components: {MypageNavi},

  data () {
    return {
    }
  },
  methods:{

  }
}
</script>
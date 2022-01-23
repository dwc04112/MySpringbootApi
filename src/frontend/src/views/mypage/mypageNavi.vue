<template >
  <div
      style="width: 100%; height: 100%; background-color: #F5F5F5"
  >
    <v-card
        class="float-left"
        height="512"
        width="256"
        style="margin-top:5%; margin-left: 5%"
    >
      <v-navigation-drawer
          class="accent-4"
          color="#99A799"
          dark
          permanent
      >
        <v-list nav>
          <v-list-item
              v-for="item in items"
              :key="item.title"
              :to="item.link"
              link
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <template v-slot:append>
          <div class="pa-2">
            <v-btn block color="#E2C2B9" @click="linkTo('home')">
              Home
            </v-btn>
          </div>
          <div class="pa-2">
            <v-btn block color="#E2C2B9" @click="logout">
              Logout
            </v-btn>
          </div>
        </template>
      </v-navigation-drawer>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "mypageNavi",
  data () {
    return {
      items: [
        { title: 'MyPage', icon: 'mdi-view-dashboard' , link:'/mypage'},
        { title: 'Personal Info', icon: 'mdi-account-box', link:'/mypage/personal-info' },
        { title: 'Admin', icon: 'mdi-gavel' },
      ],

    }
  },
  methods:{
    linkTo(link){
      this.$router.push({path:"/"+link})
    },
    logout(){
      this.$store.dispatch('logout');
      this.$router.push({path: './'});
    },
  }
}
</script>

<style scoped>

</style>
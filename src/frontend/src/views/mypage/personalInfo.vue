<template>
  <div>
      <!--// 배경색 지정해야함-->
    <mypage-navi></mypage-navi>
    <v-card
        class="float-left"
        height="512"
        width="512"
        style="margin-top:5%;margin-left: 2%"
        color="#F2DDC1"
    >
      <p>MyPage Personal-Info</p>

      <div  style="float: left; width: 90%; margin-left: 5%">
        <v-data-table
            id="table"
            :headers="headers"
            :items="userInfo"
            hide-default-header
            hide-default-footer
            class="elevation-0"

        >
          <template v-slot:item.actions="{item}">
          <v-icon
              small
              class="mr-2"
              @click="editInfo(item.id)"
          >
            mdi-pencil
          </v-icon>
          </template>

        </v-data-table>
      </div>
    </v-card>
  </div>
</template>

<script>

import mypageNavi from "@/views/mypage/mypageNavi";
export default {
  name: "personalInfo",
  components: {mypageNavi},
  data () {
    return {
      firstName: '',
      lastName: '',
      email: '',
      nickName: '',
      headers:[
        {text : 'name', value: 'name'},
        {text : 'data', value: 'data'},
        {text: 'Actions',value: 'actions', sortable: false },
      ],
      userInfo:[
        {name:'닉네임', data:  this.$store.state.userStore.nickName , id:'nickname'},
        {name:'이름', data: this.firstName+this.lastName, id:'name'},
        {name:'이메일',  data: this.$store.state.userStore.email, id:'email'},

      ]
    }
  },
  methods: {
    getPersonalInfo() {
      this.email = this.$store.state.userStore.email;
      this.firstName = this.$store.state.userStore.firstName;
      this.lastName = this.$store.state.userStore.lastName;
      this.nickName = this.$store.state.userStore.nickName;
      // this.$store.dispatch('getUserInfo');
    },
    editInfo(id){
      console.log('id는? : ' + id)
      this.$router.push({path:'./'+id})
    },
  },
  mounted(){
    this.getPersonalInfo();
  }
}
</script>

<style scoped>
#table{
  background-color: #F2DDC1;
}
</style>
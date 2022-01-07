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
      headers:[
        {text : 'name', value: 'name'},
        {text : 'data', value: 'data'},
        {text: 'Actions',value: 'actions', sortable: false },
      ],
      userInfo:[
        {name:'닉네임', data: this.$store.state.userStore.nickName , id:'nickname'},
        {name:'이름', data: this.$store.state.userStore.firstName+this.$store.state.userStore.lastName, id:'name'},
        {name:'이메일',  data: this.$store.state.userStore.email , id:'email'},
      ]
    }
  },
  methods: {
    getUserInfo(){
      console.log('다시 정보받기')
      this.$store.dispatch('getUserInfo', this.$store.state.userStore.email)

    },
    editInfo(id){
      console.log('수정할 정보? : ' + id)
      this.$router.push({path:'./'+id})

    },
  },

  mounted(){
    this.getUserInfo();
  }
}

</script>

<style scoped>
#table{
  background-color: #F2DDC1;
}
</style>
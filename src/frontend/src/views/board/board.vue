<template>
  <v-card
      height="500"
      width="512"
      class="mx-auto"
      style="margin-top:5%;"
      color="#FFF8F3"
  >
    <v-card-text  v-for="user in users" v-bind:key="user.bid">
      <div>{{user.writeDate}}</div>
      <div>{{user.writeTime}}</div>
      <div>작성자 : {{user.author}}</div>
      <p class="text-h5 text--primary">
        {{user.subject}}
      </p>
      <div class="text--primary" >
        {{user.content}}
      </div>
    </v-card-text>
    <v-card-actions>
      <v-btn
          text
          color="teal accent-4"
          @click="pageLink"
      >
        Home
      </v-btn>
      <v-btn
          text
          color="teal accent-4"
          @click="editLink"
      >
        Edit
      </v-btn>
      <v-btn
          text
          color="teal accent-4"
          @click="overlay = !overlay"
      >
        Delete
      </v-btn>


      <!--
          01-14 작성

          1. 글 삭제 폼
          board에서 Delete 버튼 클릭시 오버레이로 삭제할지 말지 버튼 생성

          2. 수정할 것
          수정과 삭제시 본인이 맞는지 인증하는 코드 필요
      -->
      <v-overlay
          :absolute="true"
          :value="overlay"
          class="text-center"
      >
        <div style="color: black"> 게시글을 삭제하시겠습니까?</div>
        <div  style="margin-top: 10%">
          <v-btn
              class="ma-3"
              color="#C7B198"
              @click="deleteLink"
          >
            Yes
          </v-btn>
          <v-btn
              class="ma-3"
              color="error"
              @click="overlay = false"
          >
            No
          </v-btn>
        </div>
      </v-overlay>
    </v-card-actions>
  </v-card>
</template>

<script>
import VueCookies from "vue-cookies";
import Vue from "vue";
Vue.use(VueCookies);
export default {

  name: "board",

  data: () => ({
    users:'',
    overlay : false,
  //  content : '',
  //  subject : '',
  }),


  methods: {
    viewUsers(){
      let bid = this.$route.query.bid
      this.$axios.get("boardjpa/viewCount/"+ bid)
      const cookie = this.$cookies.get('viewCookie');
      console.log(cookie); //testValue
    },


    detailUsers() {
      let data = this.$route.query.bid
      this.$axios.get("boardjpa/"+ data,)
        .then(response => {
          this.users = response.data;
        //  this.content = response.data[0].content
        //  this.subject = response.data[0].subject
          console.log(response.data);
        })
        .catch(error =>{
          console.log(error.response);
        })
    },
    pageLink(){
      this.$router.push({path:'/home'})
    },
    editLink(){
      let bid = this.$route.query.bid
      this.$router.push({path:'./boardEdit',query:{bid}})
    },
    deleteLink(){
      let editData = {}
      editData.bid = this.$route.query.bid
      editData.mid = this.$store.state.userStore.mid
      this.$axios.post("boardjpa/delete",JSON.stringify(editData),{
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response=>{
        console.log(response.data)

        if(response.data.success === false){
          alert("글 삭제 권한이 없습니다.")
        }else{
          alert("게시글을 성공적으로 삭제했습니다.");
        }

        this.$router.push({path: './home'});
      }).catch(error =>{
        console.log(error.response);
      })

      this.overlay=false
    },
  },
  mounted() {
    this.viewUsers();
    this.detailUsers();
  }
}
</script>

<style scoped>

</style>
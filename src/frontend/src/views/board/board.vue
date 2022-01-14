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

export default {
  name: "board",

  data: () => ({
    users:'',
    overlay : false,
  }),


  methods: {
    detailUsers() {
      let data = this.$route.query.bid
      this.$axios.get("boardjpa/"+ data,)
        .then(response => {
          this.users = response.data;
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

      this.overlay=false
    },
  },
  mounted() {
    this.detailUsers();
  }
}
</script>

<style scoped>

</style>
<template>
  <v-card
      class="mx-auto"
      max-width="450"
  >
    <v-card-text  v-for="user in users" v-bind:key="user.id">
      <div>{{user.writeDate}}</div>
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
          @click="reveal = true"
      >
        Edit
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>

export default {
  name: "board",

  data: () => ({
    users:''
  }),


  methods: {
    detailUsers() {
      let data = this.$route.query.id
      this.$axios.get("boardjpa/"+ data,{
        headers:{
          Authorization : "Bearer "+ this.$store.state.userStore.token
        }
      }
        ).then(response => {
          this.users = response.data;
          console.log(response.data);
        })
        .catch(error =>{
          console.log(error.response);
          this.$store.commit('loginCheck',error.response.status)
        })
    },
    pageLink(){
      this.$router.push({path:'/'})
    },
  },
  mounted() {
    this.detailUsers();
  }
}
</script>

<style scoped>

</style>
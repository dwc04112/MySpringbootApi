<template>
  <v-card
      class="mx-auto"
      max-width="450"
  >
    <v-card-text  v-for="user in users" v-bind:key="user.id">
      <div>{{user.writeDate}}</div>
      <p class="text-h4 text--primary">
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
          @click="reveal = true"
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
      this.$axios.get("board/"+data).then(response => {
        this.users = response.data;
        console.log(response.data);
      })
          .catch(e => {
            console.log(e);
          })
    }
  },
  mounted() {
    this.detailUsers();
  }
}
</script>

<style scoped>

</style>
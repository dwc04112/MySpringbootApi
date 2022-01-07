<template>
  <v-card
      height="300"
      width="512"
      class="mx-auto"
      style="margin-top:5%;"
      color="#F2DDC1"
  >
    <v-card-title class="text-h8">
      이름 변경
    </v-card-title>
    <v-card-subtitle> 변경하실 이름을 아래에 입력해주세요 </v-card-subtitle>
    <v-row style="margin-top: 5%; margin-left: 20% ;" >
      <v-col>
        <div style="float: left; width: 20%;">
          <v-text-field
              placeholder="성"
              v-model="firstName"
              required >
          </v-text-field>
        </div>
        <div style="float: left; width: 40%; margin-left: 5%">
          <v-text-field
              placeholder="이름"
              v-model="lastName"
              required >
          </v-text-field>
        </div>
      </v-col>
    </v-row>
    <v-card-actions
        style="margin-top: 10%; margin-right: 5%; float: right"
    >
      <v-btn
          text
          @click="editCommit"
      >
        Edit
      </v-btn>
      <v-btn
          text
          @click="pageLink"
      >
        Back
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "name",
  data () {
    return {
      firstName : '',
      lastName : '',
    }
  },
  methods:{
    editCommit(){
      console.log("변경할 이름 : " + this.firstName + this.lastName)
      let editInfo = {}
      editInfo.firstName = this.firstName
      editInfo.lastName = this.lastName
      editInfo.id = this.$store.state.userStore.id
      this.$axios.post("user/edit",JSON.stringify(editInfo),{
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response=>{
        console.log(response.data)
        this.$store.commit('putUserName', {firstName : this.firstName, lastName : this.lastName});
      }).catch(error =>{
        console.log(error.response);
      })
    },
    pageLink(){
      this.$router.push({path:'./personal-Info'})
    },
  },
}
</script>

<style scoped>

</style>
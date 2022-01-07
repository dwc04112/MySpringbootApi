<template>
  <v-card
      height="300"
      width="512"
      class="mx-auto"
      style="margin-top:5%;"
      color="#F2DDC1"
  >
    <v-card-title class="text-h8">
      닉네임 변경
    </v-card-title>
    <v-card-subtitle> 변경하실 닉네임을 아래에 입력해주세요 </v-card-subtitle>
    <v-row style="margin-top: 5%; margin-left: 20% ;" >
      <div>
      <v-col>
        <div style="float: left; width: 60%;">
          <v-text-field
              placeholder="nickname"
              v-model="nickName"
              type="text"
              required >
          </v-text-field>
        </div>
        <div style="float: left; margin-top: 4%; width: 20%; margin-left: 2%">
          <v-btn
              text
              @click="doubleCheck"
          >
            중복확인{{checkNum}}
          </v-btn>
        </div>
      </v-col>
      </div>
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
  name: "nickname",
  data () {
    return {
      checkNum: '', //닉네임 중복값 -> 1이면 중복, 0이면 중복x
      nickName: '',
    }
  },
  methods:{
    doubleCheck(){
      if(this.nickName===""||null||undefined||NaN){
        alert("닉네임을 입력해주세요")
        return false;
      }

      let saveData = {};
      saveData.nickName = this.nickName;
      this.$axios.post("signup/doublecheck",JSON.stringify(saveData),{
        headers: {
          "Content-Type": `application/json`,
        },
      })
          .then(response=>{
            this.checkNum = response.data;
            if(this.checkNum===0){
              alert("사용가능한 닉네임 입니다")
            }else{
              alert("중복된 닉네임 입니다")
            }
            console.log(response.data);
          })
          .catch(error =>{
            console.log(error.response);
          })
    },
    editCommit(){
      if(this.checkNum===1){
        alert("중복확인을 실행해주세요")
        return null;
      }
      console.log("변경할 닉네임 : " + this.nickName)
      let editInfo = {}
      editInfo.nickName = this.nickName
      editInfo.id = this.$store.state.userStore.id
      this.$axios.post("user/edit",JSON.stringify(editInfo),{
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response=>{
        console.log(response.data)
        this.$store.commit('putUserNickName', {nickName : this.nickName});
      }).catch(error =>{
        console.log(error.response);
      })
    },
    pageLink(){
      this.$router.push({path:'./personal-Info'})
    },
  }
}
</script>

<style scoped>

</style>
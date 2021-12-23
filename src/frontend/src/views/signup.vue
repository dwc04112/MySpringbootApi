<template>
  <v-card
      max-width="700"
      style="margin-top: 80px"
      class="mx-auto"
  >
    <v-col cols="12">
      <v-card
          color="#385F73"
          dark
      >
        <v-card-title class="text-h5">
          계정 생성하기
        </v-card-title>

        <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle>
        <v-row style="margin-top: 15px; margin-right: 30px; margin-left: 30px">
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
                  v-model="LastName"
                  required >
              </v-text-field>
            </div>
          </v-col>
        </v-row>


        <v-row style="margin-top: -3%; margin-right: 30px; margin-left: 30px">
          <v-col>
            <div style="float: left; width: 65%;">
              <v-text-field
                  placeholder="Email"
                  v-model="Email"
                  required >
              </v-text-field>
            </div>
          </v-col>
        </v-row>

        <v-row style="margin-top: 0%; margin-right: 30px; margin-left: 30px">
          <v-col>
            <div style="float: left; width: 30%;">
              <v-text-field
                  placeholder="비밀번호"
                  v-model="password"
                  type="password"
                  required >
              </v-text-field>
            </div>
            <div  style="float: left; width: 30%; margin-left: 5%">
              <v-text-field
                  placeholder="비밀번호 확인"
                  v-model="passwordCheck"
                  @mouseleave="passCheck"
                  @keyup="passCheck"
                  type="password"
                  required >
              </v-text-field>
            </div>
            <div style= "float: left; width: 30%; margin-top: 3%; margin-left: 3%; opacity: 70%">
              {{placeholder}}
            </div>
          </v-col>
        </v-row>

        <v-row style="margin-top: 0%; margin-right: 30px; margin-left: 30px">
          <v-col>
            <div style="float: left; width: 30%;">
              <v-text-field
                  placeholder="닉네임"
                  v-model="nickname"
                  type="text"
                  required >
              </v-text-field>
            </div>
            <div style="float: left; margin-top: 2%; width: 20%; margin-left: 2%">
              <v-btn
                  text
                  color="yellow"
                  @click="doubleCheck"
              >
                중복확인
              </v-btn>
            </div>
          </v-col>
        </v-row>

        <v-card-actions>
          <v-btn text style="margin-top: 2%">
            Next
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-card>
</template>

<script>
export default {
  name: "signup",
  data () {
    return {
      firstName: '',
      LastName: '',
      Email: '',
      password: '',
      passwordCheck:'',
      placeholder:'',
      checkNum: '', //닉네임 중복값 -> 1이면 중복, 0이면 중복x
      nickname: '',
    }
  },
  methods: {
    passCheck(){
      if(this.passwordCheck===""||null||undefined||NaN){
        this.placeholder = ""
      }else if(this.password!==this.passwordCheck){
        this.placeholder = "비밀번호가 틀렸습니다"
      }else{
        this.placeholder="비밀번호가 일치합니다";
      }
    },

    doubleCheck(){
      let saveData = {};
      saveData.nickname = this.nickname;

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
    }
  }
}

</script>

<style scoped>

</style>
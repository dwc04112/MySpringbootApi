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

        <v-card-subtitle> hihihi </v-card-subtitle>
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
                  v-model="lastName"
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
                  v-model="email"
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
                  v-model="nickName"
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
                중복확인{{checkNum}}
              </v-btn>
            </div>
          </v-col>
        </v-row>

        <v-card-actions>
          <v-btn
              text
              style="margin-top: 2%"
              @click="signCommit"
          >
            회원가입
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
      lastName: '',
      email: '',

      password: '',
      passwordCheck:'',
      placeholder:'',
      passCheckNum:1, // 비밀번호 틀리면 -> 1 , 0이면 pass

      checkNum: '', //닉네임 중복값 -> 1이면 중복, 0이면 중복x
      nickName: '',
    }
  },
  methods: {
    passCheck(){
      if(this.passwordCheck===""||null||undefined||NaN){
        this.placeholder = ""
      }else if(this.password!==this.passwordCheck){
        this.placeholder = "비밀번호가 틀렸습니다";
        this.passCheckNum = 1;
      }else{
        this.placeholder="비밀번호가 일치합니다";
        this.passCheckNum = 0;
      }
    },

    doubleCheck(){
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

    signCommit(){
      if(this.passCheckNum===1){
        alert("비밀번호를 확인해주세요")
        return null;
      }
      if(this.checkNum===1){
        alert("닉네임을 확인해주세요")
        return null;
      }
      let saveUser = {};
        saveUser.email = this.email;
        saveUser.firstName = this.firstName;
        saveUser.lastName = this.lastName;

        saveUser.password = this.password;
        saveUser.nickName = this.nickName;
      try {
        this.$axios.post("/api/member", JSON.stringify(saveUser), {
          headers: {
            "Content-Type": `application/json`,
          },
        })
            .then((response) => {
              if (response.status === 200) {
                alert("회원가입이 완료되었습니다. 로그인 화면으로 돌아갑니다")
                this.$router.push({path: './'});
              }
            })
            .catch(error =>{
              console.log(error.response);
              if(error.response.status===401){
                alert("회원가입 오류. 다시한번 회원가입을 진행해주세요");
                this.password = null;
                this.passwordCheck = null;
                this.email = null;
                this.firstName = null;
                this.lastName = null;
                this.passCheckNum = 1;
                this.checkNum = 1;
              }
            });
      } catch (error) {
        console.error(error);
      }
    }
  }
}

</script>

<style scoped>

</style>
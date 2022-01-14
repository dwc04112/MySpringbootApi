
<template>
  <v-card
      height="500"
      width="512"
      class="mx-auto"
      style="margin-top:5%;"
      color="#FFF8F3"
  >
    <v-card-title
        style="color:#C7B198;"
    >
      boardWrite
    </v-card-title>

      <div class="text-center">
          <v-col
              style="width: 100%;"
          >
            <div style="margin-top: -2%">
              <v-textarea
                  label="제목을 입력해 주세요"
                  auto-grow
                  outlined
                  color="#C7B198"
                  rows="3"
                  row-height="10"
                  v-model="subject"
                  shaped
              ></v-textarea>

              <v-textarea
                  label="내용을 입력해 주세요"
                  auto-grow
                  outlined
                  color="#C7B198"
                  rows="3"
                  row-height="60"
                  v-model="content"
              ></v-textarea>
            </div>
          </v-col>
          <div style="margin-top: -5%">
            <v-btn
                class="ma-1"
                color="#C7B198"
                plain
                @click="commit"
            >
              Commit
            </v-btn>

            <v-btn
                class="ma-1"
                color="error"
                plain
                @click="linkTo"
            >
              Close
            </v-btn>
          </div>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "boardWrite",
  data () {
    return {
      subject : '',
      content : '',
    }
  },

  methods:{
    commit(){
      let boardData = {}
      boardData.author = this.$store.state.userStore.nickName
      boardData.subject = this.subject
      boardData.content = this.content
      boardData.mid = this.$store.state.userStore.mid
      this.$axios.post("boardjpa/write",JSON.stringify(boardData),{
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response=>{
        console.log(response.data)
        alert("게시글을 성공적으로 등록했습니다.");
        this.$router.push({path: './home'});
      }).catch(error =>{
        console.log(error.response);
      })
    },
    linkTo(){
      this.$router.push({path: './home'});
    },
  },
}

</script>

<style scoped>
.v-text-field--outlined >>> fieldset {
  border-color: #DFD3C3;
  border-width: 3px;
}


</style>
<template>
  <v-card
      height="500"
      width="512"
      class="mx-auto"
      style="margin-top:5%;"
      color="#FFF8F3"
  >
    <div class="text-center">
      <v-col
          style="width: 100%;"
      >
        <div style="margin-top: 2%">
          <v-text-field
              label="수정할 제목을 입력해 주세요"
              v-bind:placeholder="subject"
              outlined
              clearable
              color="#C7B198"
              v-model="subject"
              shaped
          ></v-text-field>

          <v-textarea
              label="수정할 내용을 입력해 주세요"
              auto-grow
              outlined
              color="#C7B198"
              rows="3"
              row-height="60"
              v-model="content"
          >
          </v-textarea>
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
            @click="closeLink"
        >
          Close
        </v-btn>


      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "boardEdit",
  data () {
    return {
      subject : '',
      content : '',

    }
  },

  methods:{
    detailUsers() {
      let bid = this.$route.query.bid
      console.log("bid ? : " + bid)
      this.$axios.get("boardjpa/"+ bid,)
          .then(response => {
            this.content = response.data[0].content
            this.subject = response.data[0].subject
            console.log(response.data);
          })
          .catch(error =>{
            console.log(error.response);
          })
    },
    closeLink(){
      this.$router.go(-1);
    },
    commit(){
      let editData = {}
      editData.bid = this.$route.query.bid
      editData.content = this.content;
      editData.subject = this.subject;
      editData.mid = this.$store.state.userStore.mid
      this.$axios.post("boardjpa/edit",JSON.stringify(editData),{
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response=>{
        console.log(response.data)

          if(response.data.success === false){
            alert("글 수정 권한이 없습니다.")
          }else{
            alert("게시글을 성공적으로 수정했습니다.");
          }

        this.$router.push({path: './home'});
      }).catch(error =>{
        console.log(error.response);
      })
    },
  },
  mounted() {
    this.detailUsers();
  }
}
</script>

<style scoped>
.v-text-field--outlined >>> fieldset {
  border-color: #DFD3C3;
  border-width: 3px;
}

</style>
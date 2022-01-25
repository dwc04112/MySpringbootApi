import router from '@/router'
import axios from "axios";



const userStore = {
    state: {
        email: '',
        token: '',
        nickName: '',
        firstName: '',
        lastName: '',
        mid :'',
    },
    mutations: {
        login: function (state, data) {
            state.email = data.email
            state.token = data.token
            console.log("after email? : ", state.email)
        },
        putUserInfo: function (state, data){
            state.firstName = data.firstName
            state.lastName = data.lastName
            state.nickName = data.nickName
            state.mid = data.mid
            console.log(state)
        },

        putUserNickName: function (state, data){
            state.nickName = data.nickName
        },
        putUserName: function(state, data){
            state.firstName = data.firstName
            state.lastName = data.lastName
        },



        loginCheck: function (state, payload) {
            console.log("error code" + payload)
            if(payload===401){
               // state.token = null;
            }
            if (!state.token) {
                alert("로그인이 만료되었습니다.")
                router.push({ name: 'Login'
                }).catch(error => {
                    console.log(error)
                })
            }
        },
        failUserInfo: function (){
            userStore.state = null;
        },


        resetState: function (state){
        /*
            state.email = '';
            state.token = '';
            state.nickName = '';
            state.firstName = '';
            state.lastName = '';
            state.mid = '';
         */


            console.log(state)
        }


    },
    actions: {
        login({commit,dispatch}, payload){
            let data = {};
            data.email = JSON.parse(atob(payload.token.split('.')[1])).jti;
            data.token = payload.token
            commit('login', data)
            dispatch('getUserInfo', data.email)
        },

        logout({commit}){
            axios.post("/logout")
                .then(response => {
                    console.log(response.data + "로그아웃 처리");
                    commit('resetState')
                })
                .catch(error =>{
                    console.log(error.response);
                })
        },

        getUserInfo({commit}, context){
            console.log("getUserInfo : " + context)
            let data={};
            data.email = context
            axios.post('/user/info', JSON.stringify({"email":data.email}), {
                headers: {
                    "Content-Type": `application/json`,
                },
            })
                .then((res) => {
                    console.log(res.data)

                    data.nickName = res.data.nickName
                    data.firstName = res.data.firstName
                    data.lastName = res.data.lastName
                    data.mid = res.data.mid
                    commit('putUserInfo', data)
                })
                .catch((error)=>{
                    console.log(error.res)
                })
        }
    },
}
export default userStore


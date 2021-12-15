import router from '@/router'

const userStore = {
    state: {
        email: '',
        // userName: '',
        token: ''
    },
    mutations: {
        login: function (state, payload) {
            state.email = payload.email
            // state.userName = payload.userName
            state.token = payload.token
            console.log("after token? : " + state.token)
        },
        loginCheck: function (state, payload) {
            console.log("hihi" + payload)
            if(payload===401){
                state.token = null;
            }
            if (!state.token) {
                alert("로그인이 만료되었습니다.")
                router.push({ name: 'Login'
                }).catch(error => {
                    console.log(error)
                })
            }
        }
    }
}
export default userStore


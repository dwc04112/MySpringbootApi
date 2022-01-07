import axios from 'axios';
import {store} from "@/store";


// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    console.log('request interceptor!!!!')

    if(document.URL.match("signup")){
        console.log("인증이 필요없는 url : " + document.URL)
        return config;
    }
    config.headers.Authorization = "Bearer "+store.state.userStore.token;
    //this.$router.go(0); //새로고침
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    console.log('response interceptor!!!!')
    return response;
}, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
   // console.log("request "+error)
    console.log("response "+ error.response.data.path)
    if(error.response.data.path === "/authenticate"){
        console.log("exception Login page")
    }
    else if(error.response.status===401) {
        store.commit('loginCheck', error.response.status)
    }
    return Promise.reject(error);
});

export default axios;
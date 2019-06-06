import axios from 'axios'
import router from '../router/index'
import {Message} from 'element-ui'

let http = axios.create({
  baseURL: 'http://47.94.229.129:8080/myProject',
  //baseURL: 'https://api.spicybar.cn/v1',
  withCredentials: true,
  transformRequest: [function (data) {
    let newData = '';
    for (let k in data) {
      if (data.hasOwnProperty(k) === true) {
        newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
      }
    }
    return newData;
  }]
});

function removeToken() {
  localStorage.removeItem('token');
  sessionStorage.removeItem('token');
  http.defaults.headers.common = {Accept: "application/json, text/plain, */*"};
}

function apiAxios(method, url, params, success) {
  http({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null
  }).then(res => {
    success(res);
  }).catch(err => {
    if (err.response) {
      if (err.response.status === 401 || err.response.status === 403) {
        removeToken();
        Message.warning('请先登录');
        router.push('login');
        return;
      }
    }
    Message.error(err.message);
  });
}

export default {
  get: (url, params, success, error) => {
    return apiAxios('GET', url, params, success, error)
  },
  post: (url, params, success, error) => {
    return apiAxios('POST', url, params, success, error)
  },
  put: (url, params, success, error) => {
    return apiAxios('PUT', url, params, success, error)
  },
  delete: (url, params, success, error) => {
    return apiAxios('DELETE', url, params, success, error)
  },
  setToken: (token) => {
    http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
  },
  removeToken: () => {
    removeToken();
  }
}

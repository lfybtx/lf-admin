import {login, logout, getInfo} from '@/api/user'
import {getToken, setToken, removeToken} from '@/utils/auth'
import router, {resetRouter} from '@/router'

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },//设置用户ID
  SET_USERUID: (state, userId) => {
    state.userId = userId
  }

}

const actions = {
  // 用户登录
  login({commit}, userInfo) {
  //从用户信息userInfo中解构出用户名和密码
    const {username, password} = userInfo
    return new Promise((resolve, reject) => {
      // user login
      //调用src/api/user.js文件中的login()方法
      login({username: username.trim(), password: password}).then(response => {
    //从response中解构出返回的token数据
        const {token} = response
    //将返回的token数据保存到store中，作为全局变量使用
        commit('SET_TOKEN', token)
    //将token信息保存到cookie中
        setToken(token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const {data} = response

        if (!data) {
          reject('验证失败，请重新登录。')
        }

        const {roles, name, avatar, introduction,id} = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('该用户未分配角色，无法登录')
        }

        commit('SET_ROLES', roles)
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        commit('SET_INTRODUCTION', introduction)
        //将用户ID保存到Vuex中
        commit('SET_USERUID', id);//用户ID
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({commit, state, dispatch}) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/lfybtx/lf-admin/issues/2485
        dispatch('tagsView/delAllViews', null, {root: true})

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  async changeRoles({commit, dispatch}, role) {
    const token = role + '-token'

    commit('SET_TOKEN', token)
    setToken(token)

    const {roles} = await dispatch('getInfo')

    resetRouter()

    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', roles, {root: true})
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)

    // reset visited views and cached views
    dispatch('tagsView/delAllViews', null, {root: true})
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

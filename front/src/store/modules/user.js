import {login, logout, getInfo} from '@/api/user'
import {getToken, setToken, removeToken} from '@/utils/auth'
import {resetRouter} from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    id: '',
    name: '',
    roles: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // user login
  login({commit}, userInfo) {
    const {username, password,role} = userInfo
    return new Promise((resolve, reject) => {
      login({username: username.trim(), password: password,role: role}).then(response => {
        const {data} = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
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
          return reject('Verification failed, please Login again.')
        }
        const {userId, userName, role} = data
        let roles = []
        if (role == 'teacher')
          roles = ['teacher']
        else if (role == 'admin')
          roles = ['admin']
        else if (role == 'user')
          roles = ['user']
        else roles = ['']

        const datas = {userId, userName, roles}
        commit('SET_ID', userId)
        commit('SET_NAME', userName)
        commit('SET_ROLES', roles)

        resolve(datas)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({commit, state}) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        commit('SET_ROLES', [])
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      commit('SET_ROLES', [])
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}


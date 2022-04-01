// app.js
const util = require('./utils/util.js');
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        console.log(res.code)
        util.requestUrl({
          url: 'user/getOpenId',
          method: 'get',
          data: {code: res.code},
          success: function(res){
            console.log("获取openid",res.data.data.openid)
            wx.setStorageSync('openId', res.data.data.openid)
            util.requestUrl({
              url: 'user/addUser',
              method: 'get',
              success: function(res){
                console.log("添加用户情况",res)
              }
            })
          }
        })
      }
    })
  },
  globalData: {
    userInfo: null
  }
})

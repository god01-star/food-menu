const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
/**封装request */
/* 公共request 方法 */
const requestUrl = ({
  url,
  data,
  success,
  method = "post"
}) => {

 wx.showLoading({
    title: '美食加载中',
  });
  let server = 'http://localhost:8888/food-menu/'; //测试域名
  let openid = wx.getStorageSync("openId");
  var header = {
    'content-type': 'application/json',
    'openId': openid
  }

  return new Promise(function (resolve, reject) {
      console.log("进入请求")
      wx.request({
        url: server + url,
        method: method,
        data: data,
        header: header,
        success: success,
        complete: (res) => {
          wx.hideLoading();
          if (!res.data.status || res['statusCode'] !== 200) {
            wx.showToast({
              title: res.msg || '请求出错',
              icon: 'none',
              duration: 2000,
              mask: true
            })
          }
          resolve(res.data)
        },
        fail: function (res) {
          wx.hideLoading();
          wx.showToast({
            title: res,
            icon: 'error',
            duration: 2000,
            mask: true
          })
          reject(res.data)
        },
        complete: function () {
          wx.hideLoading()
        }
      })
    })
    .catch((res) => {})
}
module.exports = {
  formatTime: formatTime,
  requestUrl: requestUrl
}
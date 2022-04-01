// index.js
// 获取应用实例
const app = getApp()
const util = require('../../utils/util.js');
Page({
  data: {
    searchType: 'newList', //newList为查询爬虫列表，collection为查询收藏列表
    keyword: '',
    currentPage: 0,
    foodList: [],
    showDetail: false,
    foodDetail: {},
    currentFoodTitle: '',
    currentFood: {},

  },
   onShow() {
    this.loadFoodList();
  }, 
  onLoad() {
    this.loadFoodList();
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
  },
  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res)
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    })
  },
  getUserInfo(e) {
    // 不推荐使用getUserInfo获取用户信息，预计自2021年4月13日起，getUserInfo将不再弹出弹窗，并直接返回匿名的用户个人信息
    console.log(e)
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  loadFoodList() {
    let _this = this;
    this.setData({
      searchType: 'newList'
    })
    util.requestUrl({
      url: 'food/getFoodList',
      method: 'get',
      data: {
        page: this.data.currentPage
      },
      success: function (res) {
        _this.setData({
          foodList: res.data.data
        })
      }
    })
  },
  searchFood(e) {
    console.log(e.detail.value)
    this.setData({
      keyword: e.detail.value
    })
    let keyword = e.detail.value;
    let _this = this;
    if (this.data.searchType == 'newList') {
      util.requestUrl({
        url: 'food/searchFood',
        method: 'get',
        data: {
          keyword: keyword
        },
        success: function (res) {
          _this.setData({
            foodList: res.data.data
          })
        }
      })
    } else {
      this.showCollectionFood();
    }

  },
  getFoodDetail(e) {
    this.setData({
      currentFood: e.currentTarget.dataset.food
    })
    let detailUrl = e.currentTarget.dataset.food.detailUrl;
    let foodName = e.currentTarget.dataset.food.foodName;
    this.setData({
      currentFoodTitle: foodName
    })
    let _this = this;
    util.requestUrl({
      url: 'food/getFoodDetail',
      method: 'get',
      data: {
        url: detailUrl
      },
      success: function (res) {
        if (res.data.status) {
          _this.setData({
            foodDetail: res.data.data,
            showDetail: true
          })
        }

      }
    })
  },
  listenInput(e) {
    if (e.detail.value.trim() == "") {
      this.setData({
        keyword: ""
      })
      this.data.searchType == 'newList' ? this.loadFoodList() : this.showCollectionFood();
    }
  },
  backList() {
    this.setData({
      showDetail: false
    })
  },
  // 收藏菜谱
  collectionFood() {
    let _this = this;
    util.requestUrl({
      url: 'user/addCollection',
      data: this.data.currentFood,
      success: function (res) {
        let data = _this.data.foodDetail;
        data.isCollection = true;
        if(res.data.status){
          _this.setData({
            foodDetail: data
          })
        }
        wx.showToast({
          title: res.data.message,
          icon: 'success',
          duration: 2000,
          mask: true,

        })
      }
    })
  },
  //收藏列表展示
  showCollectionFood() {
    this.setData({
      searchType: 'collection'
    })
    let _this = this;
    util.requestUrl({
      url: 'user/getCollection',
      method: 'get',
      data: {
        keyword: this.data.keyword
      },
      success: function (res) {
        _this.setData({
          foodList: res.data.data
        })
      }
    })
  },
  removeCollection(e) {
    let preRemoveItem = e.currentTarget.dataset.food;
    let _this = this;
    wx.showModal({
      title: '提示',
      content: '确定删除该菜谱吗？',
      success(res) {
        if (res.confirm) {
          util.requestUrl({
            url: 'user/delCollection',
            method: 'get',
            data: {
              id: preRemoveItem.id
            },
            success: function (res) {
              _this.showCollectionFood();
            }
          })
        }
      }
    })


  }
})
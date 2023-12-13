<template>
  <div class="roots">
    <div class="title">
       {{ notice.title }}
    </div>

    <el-divider></el-divider>
    <div class="publisher">
      <span>
        发布人：{{ notice.publisher }}&nbsp;---
      </span>
      创建时间：{{ notice.ptime }}
    </div>
    <el-divider></el-divider>
    <div class="content">
      {{notice.content}}
    </div>
  </div>
</template>

<script>


import noticeApi from '@/api/notice'

export default {
  data() {
    return {
      id: '',
      notice: ''
    }
  },
  created() {
    const uid = this.$route.params.nid;
    this.getNotice(uid)
    this.id = uid
  },
  methods: {
    getNotice(uid) {
      noticeApi.getNotice(uid).then((response) => {
        this.notice = response.data
      })
    }
  }
}
</script>

<style scoped>
.roots{
  width: 100%;
}
.title{
  font-family: "Adobe 黑体 Std R";
  font-size: 50px;
  text-align: center;
}
.publisher{
  text-align: center;
  font-family: 宋体;
  font-size: 20px;
}
.content{
  text-align: center;
  font-family: 宋体;
  font-size: 20px;
  width: 80%;
  margin-left: 10%;
}
</style>

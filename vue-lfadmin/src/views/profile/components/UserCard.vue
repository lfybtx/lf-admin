<template>
  <el-main>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix" align="center">
      <span>个人中心</span>
    </div>

    <div class="user-profile">
      <div class="box-center">
        <pan-thumb :image="user.avatar" :height="'100px'" :width="'100px'" :hoverable="false">

          <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/api/user/avatar/upload?module=avatar"
            :show-file-list="false"
            :data="uploadHeader"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <div>
              <span style="font-size: x-small">点击修改头像，修改后需提交</span>
            </div>
            <img v-if="imageUrl" :src="imageUrl">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </pan-thumb>
      </div>
      <div class="box-center">
        <div class="user-name text-center">{{ user.nickName }}</div>
        <div class="user-role text-center text-muted">
          <div v-for="(role, index) in roles" :key="index">{{ role }}</div>
        </div>
      </div>
    </div>

    <div class="user-bio">
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header">
          <svg-icon icon-class="education" />
          <span>Education</span>
        </div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            JS in Computer Science from the University of Technology
          </div>
        </div>
      </div>

      <div class="user-skills user-bio-section">
        <div class="user-bio-section-header">
          <svg-icon icon-class="skill" />
          <span>Skills</span>
        </div>
        <div class="user-bio-section-body">
          <div class="progress-item">
            <span>Vue</span>
            <el-progress :percentage="70" />
          </div>
          <div class="progress-item">
            <span>JavaScript</span>
            <el-progress :percentage="18" />
          </div>
          <div class="progress-item">
            <span>Css</span>
            <el-progress :percentage="12" />
          </div>
          <div class="progress-item">
            <span>ESLint</span>
            <el-progress :percentage="100" status="success" />
          </div>
        </div>
      </div>
    </div>
  </el-card>
  </el-main>
</template>

<script>
import PanThumb from '@/components/PanThumb'
import {getToken} from "@/utils/auth";

export default {
  components: { PanThumb },
  props: {
    user: {
      type: Object,
      default: () => ({
      })
    },
    role: {
      type: Array,
      default: () => []
    },
  },
  data(){
    return{
      //上传需要携带的数据
      uploadHeader: {"token": getToken()},
      imageUrl:'',

    }
  },
  methods:{
    /**
     * 上传校验
     * @param file
     * @returns {boolean}
     */
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt10M = file.size / 1024 / 1024 < 10
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt10M) {
        this.$message.error('上传头像图片大小不能超过 10MB!')
      }
      this.uploadHeader.token=getToken()
      return isJPG && isLt10M
    },
    /**
     * 上传成功回调
     * @param res
     * @param file
     */
    handleAvatarSuccess(res, file) {
      this.user.avatar = res.data
      console.log(this.user.avatar)
      this.$forceUpdate()
    },
  },
  computed: {
    roles() {
      return this.role // Access roles directly
    }
  },
  created() {
  }
}
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }
}

.user-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 14px;
    padding: 15px 0;

    .user-bio-section-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>

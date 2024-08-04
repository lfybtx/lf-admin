<template>
  <el-main>
    <el-form>
      <el-form-item label="昵称">
        <el-input v-model="user.nickName"/>
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="user.realName"/>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="user.phone"/>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="user.email"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">修改信息</el-button>
        <el-button type="warning" @click="openPasswordDialog">修改密码</el-button>
      </el-form-item>
    </el-form>
    <system-dialog
      :title="passwordDialog.title"
      :visible="passwordDialog.visible"
      :width="passwordDialog.width"
      :height="passwordDialog.height"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <div slot="content">
        <el-form
          :model="passwordForm"
          ref="passwordForm"
          :rules="rules"
          label-width="80px"

          :inline="false"
          size="small"
        >
          <el-form-item label="新密码" prop="newPassword" label-width="100px">
            <el-input v-model="passwordForm.newPassword" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认新密码" prop="newPasswordAgain" label-width="100px">
            <el-input v-model="passwordForm.newPasswordAgain" show-password></el-input>
          </el-form-item>
        </el-form>
      </div>
    </system-dialog>
  </el-main>
</template>

<script>

import userApi, {logout} from '@/api/user'
//导入对话框组件
import SystemDialog from '@/components/System/SystemDialog.vue'
import {clearStorage, getToken, removeToken} from "@/utils/auth";

export default {
  name: "Account",
  components: {SystemDialog},
  data() {
    return {
      rules: {
        newPassword: [{required: true, trigger: 'blur', message: '请输入新密码'}],
        newPasswordAgain: [{required: true, trigger: 'blur', message: '请再次输入新密码'}]
      },
      newPassword: '',
      newPasswordAgain: '',
      //添加和修改用户窗口属性
      passwordDialog: {
        title: '修改密码',
        height: 160,
        width: 610,
        visible: false
      },
      passwordForm: {
        newPassword: '',
        newPasswordAgain: ''
      },
    }
  },
  props: {
    user: {
      type: Object,
    }
  },

  methods: {
    async logout() {
      try {
        const params = { token: getToken() };
        const res = await logout(params);

        if (res.success) {
          removeToken();
          clearStorage();
          window.location.href = "/login";
        } else {
          this.$message.error("退出失败，请重试！");
        }
      } catch (error) {
        this.$message.error("网络错误，请重试！");
      }
    },

    async onConfirm() {
      if (this.passwordForm.newPassword === this.passwordForm.newPasswordAgain) {
        this.user.password = this.passwordForm.newPassword;
        this.$confirm('您确定要更新您的密码吗?', '确认', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          try {
            // 确保 userApi.updatePassword 是一个异步函数并返回 Promise
            const res = await userApi.updatePassword(this.user);

            if (res.success) { // 假设返回结果包含一个 success 属性
              this.$message({
                message: "修改密码成功",
                type: "success",
                duration: 5 * 1000
              });
              this.onClose();
              console.log("密码修改成功，准备退出");

              // 确保退出操作完成
              await this.logout();

              console.log("退出成功，重定向到登录页面");
            } else {
              this.$message({
                message: "修改密码失败",
                type: 'error',
                duration: 5 * 1000
              });
            }
          } catch (error) {
            this.$message({
              message: "修改密码失败",
              type: 'error',
              duration: 5 * 1000
            });
          }
        }).catch(() => {
          this.$message({
            message: '修改密码已取消',
            type: 'info',
            duration: 3 * 1000
          });
        });
      } else {
        this.$message({
          message: "两次密码不一致",
          type: 'error',
          duration: 5 * 1000
        });
      }
    },

    openPasswordDialog() {
      this.passwordDialog.visible = true
    },
    onClose() {
      this.passwordDialog.visible = false
      // 清空表单
      this.$refs.passwordForm.resetFields()
    },
    submit() {
      this.$confirm('您确定要更新您的信息吗?', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        try {
          // 用户点击确定后的操作
          const res = userApi.updateMe(this.user)
          this.$message({
            message: "修改成功",
            type: "success",
            duration: 5 * 1000
          });

        } catch (error) {
          // 处理错误
          this.$message({
            message: "修改错误",
            type: 'error',
            duration: 5 * 1000
          });
        }
      }).catch(() => {
        // 用户点击取消后的操作（可选）
        this.$message({
          message: '更新已取消',
          type: 'info',
          duration: 3 * 1000
        });
      });
    },

  },
  created() {
  }
}
</script>

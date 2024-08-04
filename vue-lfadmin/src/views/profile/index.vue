// Parent Component
<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
          <user-card :user="user" :role="roleArray" />
        </el-col>
        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane label="活动" name="activity">
                <activity />
              </el-tab-pane>
              <el-tab-pane label="个人设置" name="account">
                <account :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserCard from './components/UserCard'
import Activity from './components/Activity'
import Account from './components/Account'
import userApi from '@/api/user'

export default {
  name: 'Profile',
  components: { UserCard, Activity, Account },
  data() {
    return {
      user: {},
      activeTab: 'activity',
      roleArray: []
    }
  },
  computed: {
    ...mapGetters(['roles', 'userId'])
  },
  created() {
    this.getUser()
  },
  methods: {
    async getUser() {
      let userId = this.userId
      this.user = (await userApi.getMe({ userId })).data
      this.roleArray = this.roles // Ensure roleArray is an array
    }
  }
}
</script>

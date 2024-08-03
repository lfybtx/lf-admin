<template>
  <el-main>
    <!-- 查询条件 -->
    <el-form
      :model="searchModel"
      ref="searchForm"
      label-width="80px"
      :inline="true"
      size="small"
    >
      <el-form-item>
        <el-input v-model="searchModel.jobName" placeholder="请输入岗位名称"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="search(pageNo,pageSize)">查询</el-button>
        <el-button icon="el-icon-refresh-right" @click="resetValue">重置</el-button>
        <el-button type="success" icon="el-icon-plus" @click="openAddWindow"
                   v-if="hasPermission('sys:job:add')"
        >新增
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table
      :data="jobList"
      :height="tableHeight"
      border
      stripe
      style="width: 100%; margin-bottom: 10px"
      empty-text="暂无数据"
    >
      <el-table-column
        prop="id"
        label="岗位编号"
        width="100"
        align="center"
      ></el-table-column>
      <el-table-column prop="jobName" label="岗位名称" align="center"></el-table-column>
      <el-table-column prop="remark" label="岗位备注" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="290">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            type="primary"
            size="small"
            @click="handleEdit(scope.row)"
            v-if="hasPermission('sys:job:edit')"
          >编辑
          </el-button
          >
          <el-button
            icon="el-icon-delete"
            type="danger"
            size="small"
            @click="handleDelete(scope.row)"
            v-if="hasPermission('sys:job:delete')"
          >删除
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页工具栏 -->
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNo"
      :page-sizes="[10, 20, 30, 40, 50]"
      :page-size="10"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
    <!-- 添加和修改岗位窗口 -->
    <system-dialog
      :title="jobDialog.title"
      :visible="jobDialog.visible"
      :width="jobDialog.width"
      :height="jobDialog.height"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <div slot="content">
        <el-form
          :model="job"
          ref="jobForm"
          :rules="rules"
          label-width="80px"
          :inline="false"
          size="small"
        >
          <el-form-item label="岗位名称" prop="jobName">
            <el-input v-model="job.jobName"></el-input>
          </el-form-item>
          <el-form-item label="岗位备注" prop="jobName">
            <el-input v-model="job.remark"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </system-dialog>
  </el-main>
</template>


<script>
import SystemDialog from "@/components/System/SystemDialog";
//导入job.js脚本
import jobApi from '@/api/job';

export default {
  name: "jobList",
  components: {
    SystemDialog,
  },
  data() {
    return {
      //查询条件
      searchModel: {
        jobName: '',
        pageNo: 1,
        pageSize: 10,
        userId: this.$store.getters.userId //用户ID
      },
      jobList: [], //数据列表
      tableHeight: 0, //表格高度
      pageNo: 1, //当前页码
      pageSize: 10, //每页显示数量
      total: 0, //总数量
      jobId: '', //岗位ID
      rules: {
        jobName: [{required: true, trigger: 'blur', message: '请输入岗位名称'}]
      },
      //添加和修改岗位窗口属性
      jobDialog: {
        title: '',
        visible: false,
        height: 230,
        width: 500
      },
      //岗位对象
      job: {
        id: "",
        jobName: "",
        remark: ""
      },
    }
  },
  methods: {
    /**
     * 查询
     */
    async search(pageNo = 1, pageSize = 10) {
      this.searchModel.pageNo = pageNo;//当前页码
      this.searchModel.pageSize = pageSize;//每页显示数量
      //发送查询请求
      let res = await jobApi.getJobListApi(this.searchModel);
      //执行成功
      if (res.success) {
        //岗位列表
        this.jobList = res.data.records;
        //总数量
        this.total = res.data.total;
      }
    },
    /**
     * 重置查询条件
     */
    resetValue() {
      //清空查询条件
      this.searchModel.jobName = "";
      //重新查询
      this.search();
    },
    /**
     * 当每页数量发生变化时触发该事件
     */
    handleSizeChange(size) {
      this.pageSize = size; //将每页显示的数量交给成员变量
      this.search(this.pageNo, size);
    },
    /**
     * 当页码发生变化时触发该事件
     */
    handleCurrentChange(page) {
      this.pageNo = page;
      //调用查询方法
      this.search(page, this.pageSize);
    },
    /**
     * 打开添加窗口
     */
    openAddWindow() {
      //清空表单数据
      this.$resetForm("jobForm", this.job);
      this.jobDialog.title = '新增岗位'//设置窗口标题
      this.jobDialog.visible = true//显示窗口
    },
    /**
     * 窗口取消事件
     */
    onClose() {
      this.jobDialog.visible = false
    },
    /**
     * 窗口确认事件
     */
    onConfirm() {
      //表单验证
      this.$refs.jobForm.validate(async (valid) => {
        if (valid) {
          let res = null;
          //判断岗位ID是否为空
          if (this.job.id === "") {
            //新增
            //发送添加请求
            res = await jobApi.addJobApi(this.job);
          } else {
            //发送修改请求
            res = await jobApi.updateJobApi(this.job);
          }
          //判断是否成功
          if (res.success) {
            this.$message.success(res.message);
            //刷新
            this.search(this.pageNo, this.pageSize);
            //关闭窗口
            this.jobDialog.visible = false;
          } else {
            this.$message.error(res.message);
          }
        }
      })
    },
    /**
     * 打开编辑窗口
     */
    handleEdit(row) {
      //数据回显
      this.$objCopy(row, this.job); //将当前编辑的数据复制到job对象中
      //设置窗口标题
      this.jobDialog.title = "编辑岗位";
      //显示编辑岗位窗口
      this.jobDialog.visible = true;
    },

    async handleDelete(row) {
      // 弹出确认对话框
      this.$confirm('此操作将永久删除该岗位, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        // 确认删除
        let res = await jobApi.deleteJobApi({id: row.id});
        if (res.success) {
          // 提示成功
          this.$message.success('删除成功');
          // 刷新列表
          this.search(this.pageNo, this.pageSize);
        } else {
          // 提示失败
          this.$message.error(res.message);
        }
      }).catch(() => {
        // 用户取消删除
        this.$message.info('已取消删除');
      });
    },

  },
  created() {
    this.search();
  },
  //挂载后调用
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 220
    })
  }
}
</script>

<style scoped>

</style>

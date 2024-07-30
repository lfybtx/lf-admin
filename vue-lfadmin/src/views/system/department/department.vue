<template>
  <el-main>
    <!-- 查询条件 -->
    <el-form :model="searchModel" ref="searchForm" label-width="80px"
             :inline="true" size="small">
      <el-form-item>
        <el-input placeholder="请输入部门名称" v-model="searchModel.departmentName">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="search()">查询
        </el-button>
        <el-button icon="el-icon-refresh-right" @click="resetValue()">重置</el-button>
        <el-button type="success" icon="el-icon-plus" @click="openAddWindow()">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      default-expand-all
      :tree-props="{ children: 'children' }"
    >
      <el-table-column prop="departmentName" label="部门名称"></el-table-column>
      <el-table-column prop="parentName" label="所属部门"></el-table-column>
      <el-table-column prop="phone" label="部门电话"></el-table-column>
      <el-table-column prop="address" label="部门位置"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit-outline"
            type="primary"
            size="small"
            @click="handleEdit(scope.row)"
          >编辑
          </el-button>
          <el-button
            icon="el-icon-close"
            type="danger"
            size="small"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加和编辑部门窗口 -->
    <system-dialog
      :title="deptDialog.title"
      :visible="deptDialog.visible"
      :width="deptDialog.width"
      :height="deptDialog.height"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <div slot="content">
        <el-form
          :model="dept"
          ref="deptForm"
          :rules="rules"
          label-width="80px"
          :inline="true"
          size="small"
        >
          <el-form-item label="所属部门" prop="parentName">
            <el-input v-model="dept.parentName" :readonly="true"
                      @click.native="selectDepartment()"></el-input>
          </el-form-item>
          <el-form-item label="部门名称" prop="departmentName">
            <el-input v-model="dept.departmentName"></el-input>
          </el-form-item>
          <el-form-item label="部门电话">
            <el-input v-model="dept.phone"></el-input>
          </el-form-item>
          <el-form-item label="部门地址">
            <el-input v-model="dept.address"></el-input>
          </el-form-item>
          <el-form-item label="序号">
            <el-input v-model="dept.orderNum"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </system-dialog>
    <!-- 选择所属部门窗口 -->
    <system-dialog
      :title="parentDialog.title"
      :visible="parentDialog.visible"
      :width="parentDialog.width"
      :height="parentDialog.height"
      @onClose="parentOnClose"
      @onConfirm="parentOnConfirm"
    >
      <div slot="content">
        <el-tree
          ref="parentTree"
          :data="treeList"
          node-key="id"
          :props="defaultProps"
          :default-expand-all="true"
          :highlight-current="true"
          :expand-on-click-node="false"
          @node-click="handleNodeClick"
        >
          <div class="customer-tree-node" slot-scope="{ node, data }">
            <span v-if="data.children.length === 0">
              <i class="el-icon-document"></i>
            </span>
            <span v-else @click="openBtn(data)">
              <svg-icon v-if="data.open" icon-class="add-s"/>
              <svg-icon v-else icon-class="sub-s"/>
            </span>
            <span style="margin-left: 5px">{{ node.label }}</span>
          </div>
        </el-tree>
      </div>
    </system-dialog>

  </el-main>
</template>


<script>
//导入api脚本
import departmentApi from "@/api/department";
//导入对话框组件
import SystemDialog from '@/components/System/SystemDialog.vue';

export default {
  name: "department",
  components: {
    SystemDialog
  },
  data() {
    return {
      searchModel: {
        departmentName: "", //部门名称
      },
      tableData: [], //表格数据列表


      //部门窗口属性
      deptDialog: {
        title: "新增部门",
        visible: false, //是否显示
        width: 560,
        height: 170,
      },
      //部门对象
      dept: {
        id: "",
        pid: "",
        parentName: "",
        departmentName: "",
        address: "",
        phone: "",
        orderNum: "",
      },
      //表单验证规则
      rules: {
        parentName: [
          {
            required: true, trigger: "change", message: "请选择所属部门"
          }],
        departmentName: [{required: true, trigger: "blur", message: "请输入部门名称"}],
      },
      //选择所属部门属性
      parentDialog: {
        title: "选择所属部门",
        visible: false, //是否显示
        width: 300,
        height: 400,
      },
      //树形菜单列表
      treeList: [],
      defaultProps: {
        children: "children",
        label: "departmentName",
      },
    };
  },
  methods: {
    /**
     * 查询部门列表
     */
    async search() {
      //发送查询请求
      let res = await departmentApi.getDepartmentList(this.searchModel);
      //判断是否存在数据
      if (res.success) {
        //获取数据
        this.tableData = res.data;
      }
    },
    /**
     * 重置
     */
    resetValue() {
      this.searchModel = {};//清空数据
      this.search();//重新调用方法
    },
    /**
     * 弹窗取消事件
     */
    onClose() {
      //关闭窗口
      this.deptDialog.visible = false;
    },
    /**
     * 编辑部门
     * @param row
     */
    handleEdit(row) {
    //数据回显
      this.$objCopy(row, this.dept);
      //设置窗口标题
      this.deptDialog.title = "编辑部门";
      //显示编辑部门窗口
      this.deptDialog.visible = true;
    },
    /**
     * 删除部门
     */
    async handleDelete(row) {
      //查询部门下是否存在子部门或用户
      let result = await departmentApi.checkDepartment({ id: row.id });
      //判断是否可以删除
      if (!result.success) {
        //提示不能删除
        this.$message.warning(result.message);
      } else {
        //确认是否删除
        let confirm =await this.$myconfirm("确定要删除该数据吗?");
        if (confirm) {
          //发送删除请求
          let res = await departmentApi.deleteById({ id: row.id });
          //判断是否成功
          if (res.success) {
            //成功提示
            this.$message.success(res.message);
            //刷新
            this.search();
          } else {
            //失败提示
            this.$message.error(res.message);
          }
        }
      }
    },
    /**
     * 弹窗确认事件
     */
    onConfirm() {
      //表单验证
      this.$refs.deptForm.validate( async (valid) => {
        //如果验证通过
        if (valid) {
          let res = null;//后端返回的数据
          //判断部门ID是否有数据，如果部门ID为空，则表示新增，否则就是修改
          if(this.dept.id===''){//新增
            //发送新增请求
            res = await departmentApi.addDept(this.dept);
          }else{
            //发送修改请求
            res = await departmentApi.updateDept(this.dept);
          }
          //判断是否成功
          if (res.success) {
            this.$message.success(res.message);
            //刷新
            this.search();
            //关闭窗口
            this.deptDialog.visible = false;
          } else {
            this.$message.error(res.message);
          }
        }
      });
    },
    /**
     * 打开添加部门窗口
     */
    openAddWindow() {
      //清空表单数据
      this.$resetForm("deptForm", this.dept);
      //设置窗口标题
      this.deptDialog.title = "新增部门";
      //显示添加部门窗口
      this.deptDialog.visible = true;
    },
    /**
     * 选择所属部门
     */
    async selectDepartment() {
      //显示窗口
      this.parentDialog.visible = true;
      //设置窗口标题
      this.parentDialog.title = "选择所属部门";
      //查询部门列表
      let res = await departmentApi.getParentTreeList();
      //判断是否成功
      if (res.success) {
        //赋值
        this.treeList = res.data;
      }
    },
    /**
     * 选择所属部门取消事件
     */
    parentOnClose() {
      this.parentDialog.visible = false;
    },
    /**
     * 选择所属部门确认事件
     */
    parentOnConfirm() {
      this.parentDialog.visible = false;
    },
    /**
     * 递归获取所有子部门的 ID
     * @param {Object} node - 当前节点
     * @param {Set} idSet - 存储所有子部门 ID 的集合
     */
    getAllDescendantIds(node, idSet) {

      // 如果当前节点有子节点，则递归遍历
      if (node.children && node.children.length > 0) {
        node.children.forEach(child => {
          idSet.add(child.id); // 添加子部门 ID 到集合
          this.getAllDescendantIds(child, idSet); // 递归调用
        });
      }
    },

    /**
     * 处理节点点击事件
     * @param {Object} data - 点击的节点数据
     */
    handleNodeClick(data) {

      // 初始化一个集合来存储子部门 ID
      const descendantIds = new Set();

      // 如果编辑部门 ID 不为空，则获取其所有子部门 ID
      if (this.dept.id) {
        // 获取当前编辑部门的所有子部门 ID
        this.getAllDescendantIds(this.$refs.parentTree.store.nodesMap[this.dept.id].data, descendantIds);
      }

      // 检查是否选择了当前部门或其子部门
      if (descendantIds.has(data.id) || data.id === this.dept.id) {
        this.$message.warning("不能选择自己或子部门作为所属部门");
      } else {
        // 当点击树节点时，赋予选中的值
        this.dept.pid = data.id;
        this.dept.parentName = data.departmentName;
        this.parentDialog.visible = false;
      }
    },
    /**
     * 点击树节点+-号折叠展开事件
     */
    openBtn(data) {
      //修改折叠展开状态
      data.open = !data.open;
      this.$refs.parentTree.store.nodesMap[data.id].expanded = !data.open;
    }
  },
  //初始化时执行
  created() {
    //调用查询部门列表方法
    this.search();
  },
};

</script>

<style lang="scss" scoped>

::v-deep .el-tree {
  .el-tree-node {
    position: relative;
    padding-left: 10px;
  }

  .el-tree-node__children {
    padding-left: 20px;
  }

  .el-tree-node :last-child:before {
    height: 40px;
  }

  .el-tree > .el-tree-node:before {
    border-left: none;
  }

  .el-tree > .el-tree-node:after {
    border-top: none;
  }

  .el-tree-node:before,
  .el-tree-node:after {
    content: "";
    left: -4px;
    position: absolute;
    right: auto;
    border-width: 1px;
  }

  .tree :first-child .el-tree-node:before {
    border-left: none;
  }

  .el-tree-node:before {
    border-left: 1px dotted #d9d9d9;
    bottom: 0px;
    height: 100%;
    top: -25px;
    width: 1px;
  }

  .el-tree-node:after {
    border-top: 1px dotted #d9d9d9;
    height: 20px;
    top: 14px;
    width: 24px;
  }

  .el-tree-node__expand-icon.is-leaf {
    width: 8px;
  }

  .el-tree-node__content > .el-tree-node__expand-icon {
    display: none;
  }

  .el-tree-node__content {
    line-height: 30px;
    height: 30px;
    padding-left: 10px !important;
  }
}

::v-deep .el-tree > div {
  &::before {
    display: none;
  }

  &::after {
    display: none;
  }
}
</style>

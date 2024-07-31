<template>
  <el-main>
    <!-- 新增按钮 -->
    <el-button type="success" icon="el-icon-plus" @click="openAddWindow">新增</el-button>

    <!-- 数据表格 -->
    <el-table
      style="margin-top: 10px"
      :height="tableHeight"
      :data="menuList"
      row-key="id"
      :default-expand-all="false"
      :tree-props="{ children: 'children' }"
      :border="true"
      stripe
    >
      <el-table-column prop="label" label="菜单名称" align="center"></el-table-column>
      <el-table-column prop="type" label="菜单类型" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type == '0'" size="normal">目录</el-tag>
          <el-tag type="success" v-else-if="scope.row.type == '1'" size="normal">菜单</el-tag>
          <el-tag type="warning" v-else-if="scope.row.type == '2'" size="normal">按钮</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="icon" label="图标" align="center">
        <template slot-scope="scope">
          <i :class="scope.row.icon" v-if="scope.row.icon.includes('el-icon')"></i>
          <svg-icon v-else :icon-class="scope.row.icon"></svg-icon>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="路由名称" align="center"></el-table-column>
      <el-table-column prop="path" label="路由地址" align="center"></el-table-column>
      <el-table-column prop="url" label="组件路径" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="small"
            @click="editMenu(scope.row)"
          >编辑
          </el-button>
          <el-button
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="deleteMenu(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增或编辑弹框 -->
    <system-dialog
      :title="menuDialog.title"
      :width="menuDialog.width"
      :height="menuDialog.height"
      :visible="menuDialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <div slot="content">
        <el-form
          :model="menu"
          ref="menuForm"
          :rules="rules"
          label-width="80px"
          :inline="true"
          size="small"
        >
          <el-row>
            <el-col :span="24">
              <el-form-item prop="type" label="菜单类型">
                <el-radio-group v-model="menu.type">
                  <el-radio :label="0">目录</el-radio>
                  <el-radio :label="1">菜单</el-radio>
                  <el-radio :label="2">按钮</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item prop="parentName" size="small" label="所属菜单">
            <el-input
              @click.native="selectParentMenu"
              v-model="menu.parentName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item prop="label" size="small" label="菜单名称">
            <el-input v-model="menu.label"></el-input>
          </el-form-item>
          <el-form-item size="small" label="菜单图标">
            <my-icon @selecticon="setIcon" ref="child"></my-icon>
          </el-form-item>
          <el-form-item
            prop="name"
            v-if="menu.type == 1"
            size="small"
            label="路由名称"
          >
            <el-input v-model="menu.name"></el-input>
          </el-form-item>
          <el-form-item
            prop="path"
            v-if="menu.type != 2"
            size="small"
            label="路由地址"
          >
            <el-input v-model="menu.path"></el-input>
          </el-form-item>
          <el-form-item
            prop="url"
            v-if="menu.type == 1"
            size="small"
            label="组件路径"
          >
            <el-input v-model="menu.url"></el-input>
          </el-form-item>
          <el-form-item prop="code" size="small" label="权限字段">
            <el-input v-model="menu.code"></el-input>
          </el-form-item>
          <el-form-item size="small" label="菜单序号">
            <el-input v-model="menu.orderNum"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </system-dialog>

    <!-- 选择所属菜单弹框 -->
    <system-dialog
      :title="parentDialog.title"
      :width="parentDialog.width"
      :height="parentDialog.height"
      :visible="parentDialog.visible"
      @onClose="onParentClose"
      @onConfirm="onParentConfirm"
    >
      <div slot="content">
        <el-tree
          style="font-size: 14px"
          ref="parentTree"
          :data="parentMenuList"
          node-key="id"
          :props="defaultProps"
          empty-text="暂无数据"
          :show-checkbox="false"
          default-expand-all
          :highlight-current="true"
          :expand-on-click-node="false"
          @node-click="handleNodeClick"
        >
          <div class="customer-tree-node" slot-scope="{ node, data }">
            <!-- 长度为0说明没有下级 -->
            <span v-if="data.children.length === 0">
              <i class="el-icon-document" style="color: #8c8c8c; font-size: 18px"></i>
            </span>
            <span v-else @click.stop="openBtn(data)">
              <svg-icon v-if="data.open" icon-class="add-s"></svg-icon>
              <svg-icon v-else icon-class="sub-s"></svg-icon>
            </span>
            <span style="margin-left: 3px">{{ node.label }}</span>
          </div>
        </el-tree>
      </div>
    </system-dialog>
  </el-main>
</template>


<script>
//导入对话框组件
import SystemDialog from '@/components/System/SystemDialog.vue';
import menuApi from "@/api/menu";
//导入自定义的icon图标库
import {elementIcons} from "@/utils/icons";
//导入自定义图标组件
import MyIcon from '@/components/System/MyIcon.vue'

export default {
  name: 'menuList',
  components: {
    SystemDialog,
    MyIcon
  },
  data() {
    return {
      iconList: [],//图标列表
      userChooseIcon: "",//用户选中的图标
      menuList: [], //菜单列表
      tableHeight: 0, //表格高度
      //新增或编辑弹框属性
      menuDialog: {
        title: "",
        width: 630,
        height: 270,
        visible: false,
      },
      //菜单属性
      menu: {
        id: "",
        type: "",
        parentId: "",
        parentName: "",
        label: "",
        icon: "",
        name: "",
        path: "",
        url: "",
        code: "",
        orderNum: "",
      },
      rules: {
        type: [{required: true, trigger: "change", message: "请选择菜单类型"}],
        parentName: [{
          required: true, trigger: "change", message: "请选择所属菜单"
        }],
        label: [{required: true, trigger: "blur", message: "请输入菜单名称"}],
        name: [{required: true, trigger: "blur", message: "请输入路由名称"}],
        path: [{required: true, trigger: "blur", message: "请输入路由路径"}],
        url: [{required: true, trigger: "blur", message: "请输入组件路径"}],
        code: [{required: true, trigger: "blur", message: "请输入权限字段"}],
      },
      parentDialog: {
        title: '选择所属菜单',
        width: 280,
        height: 450,
        visible: false
      },
      //树属性定义
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      parentMenuList: [], //所属菜单列表

    }
  },
  methods: {
    /**
     * 查询菜单列表
     */
    /**
     * 查询菜单列表
     */
    async search() {
      //发送查询请求
      let res = await menuApi.getMenuList();
      //判断是否成功
      if (res.success) {
        //赋值
        this.menuList = res.data;
      }
    },
    /**
     * 打开添加窗口
     */
    openAddWindow() {
      this.$resetForm('menuForm', this.menu) //清空表单数据
      this.menuDialog.title = '新增菜单' //设置窗口标题
      this.menuDialog.visible = true //显示窗口
      this.$nextTick(() => {
        this.$refs["child"].userChooseIcon = "";//清空菜单图标
      })
    },
    /**
     * 获取图标列表
     */
    getIconList() {
      this.iconList = elementIcons;
    },
    //给icon绑定的点击事件
    setIcon(icon) {
      this.menu.icon = icon;
    },
    /**
     * 添加和修改菜单窗口关闭事件
     */
    onClose() {
      this.menuDialog.visible = false; //关闭窗口
    },
    /**
     * 添加和修改菜单窗口确认事件
     */
    onConfirm() {
      //表单验证
      this.$refs.menuForm.validate(async (valid) => {
        if (valid) {
          let res = null;
          //判断菜单ID是否为空
          if (this.menu.id === "") {
            //发送添加请求
            res = await menuApi.addMenu(this.menu);
          } else {
            //发送修改请求
            res = await menuApi.updateMenu(this.menu);
          }
          //判断是否成功
          if (res.success) {
            this.$message.success(res.message);
            //刷新
            this.search();
            //关闭窗口
            this.menuDialog.visible = false;
          } else {
            this.$message.error(res.message);
          }
        }
      })
    },
    /**
     * 编辑菜单
     * @param row
     */
    editMenu(row) {
      //把当前要编辑的数据复制到数据域，给表单回显
      this.$objCopy(row, this.menu);
      //设置弹框属性
      this.menuDialog.title = "编辑菜单";
      this.menuDialog.visible = true;
      this.$nextTick(() => {
        this.$refs["child"].userChooseIcon = row.icon;//菜单图标回显
      })
    },
    /**
     * 删除菜单
     * @param row
     */
    async deleteMenu(row) {
      //判断是否存在子菜单
      let result = await menuApi.checkPermission({ id: row.id });
      //判断是否可以删除
      if (!result.success) {
      //提示不能删除
        this.$message.warning(result.message);
      } else {
        //确认是否删除
        let confirm =await this.$myconfirm("确定要删除该数据吗?");
        if (confirm) {
          //发送删除请求
          let res = await menuApi.deleteById({ id: row.id });
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
     * 选择所属菜单
     */
    async selectParentMenu() {
      //显示窗口
      this.parentDialog.visible = true;
      //发送查询请求
      let res = await menuApi.getParentMenuList();
      //判断是否成功
      if (res.success) {
        //赋值
        this.parentMenuList = res.data;
      }
    },
    /**
     * 选择所属菜单取消事件
     */
    onParentClose() {
      this.parentDialog.visible = false //关闭窗口
    },
    /**
     * 选择所属菜单确认事件
     */
    onParentConfirm() {
      this.parentDialog.visible = false //关闭窗口
    },
    /**
     * 切换图标
     * @param data
     */
    openBtn(data) {
      data.open = !data.open
      this.$refs.parentTree.store.nodesMap[data.id].expanded = !data.open
    },

    /**
     * 获取所有子菜单ID的集合
     * @param {Object} node - 当前节点
     * @param {Set} idSet - 存储子节点ID的集合
     */
    getAllDescendantIds(node, idSet) {
      // 如果当前节点有子节点，则递归遍历
      if (node.children && node.children.length > 0) {
        node.children.forEach(child => {
          idSet.add(child.id); // 添加子菜单ID到集合
          this.getAllDescendantIds(child, idSet); // 递归调用
        });
      }
    },
    /**
     * 所属菜单节点点击事件
     * @param {Object} data - 点击的节点数据
     */
    handleNodeClick(data) {
      // 初始化一个集合来存储子菜单ID
      const descendantIds = new Set();

      // 如果编辑菜单ID不为空，则获取其所有子菜单ID
      if (this.menu.id) {
        // 获取当前编辑菜单的所有子菜单ID
        this.getAllDescendantIds(this.$refs.parentTree.store.nodesMap[this.menu.id].data, descendantIds);
      }

      // 检查是否选择了当前菜单或其子菜单
      if (descendantIds.has(data.id) || data.id === this.menu.id) {
        this.$message.warning("不能选择自己或子菜单作为所属菜单");
      } else {
        // 当点击树节点时，赋予选中的值
        this.menu.parentId = data.id;
        this.menu.parentName = data.label;
        this.parentDialog.visible = false;
      }
    },
  },
//初始化时调用
  created() {
//调用查询菜单列表的方法
    this.search();
    this.getIconList();
  },
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 180
    })
  }
}
</script>

<style scoped>
.iconList {
  width: 400px;
  height: 300px;
  overflow-y: scroll;
  overflow-x: hidden;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;

i {
  display: inline-block;
  width: 60px;
  height: 45px;
  color: #000000;
  font-size: 20px;
  border: 1px solid #e6e6e6;
  border-radius: 4px;
  cursor: pointer;
  text-align: center;
  line-height: 45px;
  margin: 5px;

&
:hover {
  color: orange;
  border-color: orange;
}

}
}

.chooseIcons {
  width: 175px;
  background-color: #FFFFFF;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #DCDFE6;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  height: 33px;
  line-height: 25px;
  outline: none;
  padding: 0 15px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
}
</style>

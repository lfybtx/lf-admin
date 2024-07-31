import http from '@/utils/request'
export default {
  /**
   * 查询角色列表
   * @returns
   */
  async getRoleListApi(params) {
    return await http.get("/api/role/list", params);
  },
  /**
   * 添加角色
   * @returns
   */
  async addRoleApi(params) {
    return await http.post("/api/role/add", params);
  },
  /**
   * 编辑角色
   * @returns
   */
  async updateRoleApi(params) {
    return await http.put("/api/role/update", params);
  },
  /**
   * 查询分配权限树列表
   * @returns
   */
  async getAssignTreeApi(params){
    return await http.get("/api/role/getAssignPermissionTree",params);
  },
  /**
   * 分配权限
   * @returns
   */
  async assignSaveApi(params){
    return await http.post("/api/role/saveRoleAssign",params);
  },
  /**
   * 删除角色
   * @returns
   */
  async deleteRoleApi(params){
    return await http.delete("/api/role/delete",params);
  }
}

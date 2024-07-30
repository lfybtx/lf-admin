import http from '@/utils/request';
export default {
  /**
   * 查询部门列表
   * @param param
   * @returns
   */
  async getDepartmentList(param) {
    return await http.get("/api/department/list", param);
  }
}

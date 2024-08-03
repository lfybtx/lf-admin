import http from '@/utils/request'
export default {
  /**
   * 查询岗位列表
   * @returns
   */
  async getJobListApi(params) {
    return await http.get("/api/job/list", params);
  },
  /**
   * 添加岗位
   * @returns
   */
  async addJobApi(params) {
    return await http.post("/api/job/add", params);
  },
  /**
   * 编辑岗位
   * @returns
   */
  async updateJobApi(params) {
    return await http.put("/api/job/update", params);
  },
  /**
   * 删除岗位
   * @returns
   */
  async deleteJobApi(params){
    return await http.delete("/api/job/delete",params);
  }
}

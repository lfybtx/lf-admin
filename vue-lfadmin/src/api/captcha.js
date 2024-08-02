import http from "@/utils/request";

/**
 * 获取验证码图
 * @returns
 */
export async function getCaptcha(param) {
  return await http.get("/captcha", param);
}

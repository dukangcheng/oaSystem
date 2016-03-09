package com.web.oa.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.web.oa.model.User;
/**
 * 使用标签过滤的只是页面上的超链接提交的请求，但通过地址栏仍然可以进行网页的访问，这个时候就需要拦截器
 * @author dukangcheng
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invoke) throws Exception {
		// 获取当前an用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		// 获取当前的用户访问的url
		String namespace = invoke.getProxy().getNamespace();
		String actionName = invoke.getProxy().getActionName();
		String privilegeUrl = null;
		// 判断namepace的形式，获取url
		if (namespace.endsWith("/")) {
			privilegeUrl = namespace + actionName;
		}else{
			privilegeUrl = namespace + "/" + actionName;
		}
		//如果privilegeUrl以    /  开头  则去掉   /
 		if(privilegeUrl.startsWith("/")){
			privilegeUrl=privilegeUrl.substring(1);
		}
		// 如果当前用户为空 即未登录用户
		if (user == null) {
			// 当用户正在使用登录功能，就放行
			if (privilegeUrl.startsWith("userAction_login")) {
				return invoke.invoke();
			} else {//如果不是登录，就转到登录页面
				return "loginUI";
			}
		} else {// 如果是已登录用户，则判断权限
            //如果判断权限通过
			if (user.hasPrivilegeByPrivilegeUrl(privilegeUrl)) {
				return invoke.invoke();
			} else {
				//权限未通过则返回  权限认证失败页面
				return "noPrivilegeError";
			}
		}

	}

}

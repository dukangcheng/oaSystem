package com.web.oa.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.web.oa.model.User;
/**
 * ʹ�ñ�ǩ���˵�ֻ��ҳ���ϵĳ������ύ�����󣬵�ͨ����ַ����Ȼ���Խ�����ҳ�ķ��ʣ����ʱ�����Ҫ������
 * @author dukangcheng
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invoke) throws Exception {
		// ��ȡ��ǰan�û�
		User user = (User) ActionContext.getContext().getSession().get("user");
		// ��ȡ��ǰ���û����ʵ�url
		String namespace = invoke.getProxy().getNamespace();
		String actionName = invoke.getProxy().getActionName();
		String privilegeUrl = null;
		// �ж�namepace����ʽ����ȡurl
		if (namespace.endsWith("/")) {
			privilegeUrl = namespace + actionName;
		}else{
			privilegeUrl = namespace + "/" + actionName;
		}
		//���privilegeUrl��    /  ��ͷ  ��ȥ��   /
 		if(privilegeUrl.startsWith("/")){
			privilegeUrl=privilegeUrl.substring(1);
		}
		// �����ǰ�û�Ϊ�� ��δ��¼�û�
		if (user == null) {
			// ���û�����ʹ�õ�¼���ܣ��ͷ���
			if (privilegeUrl.startsWith("userAction_login")) {
				return invoke.invoke();
			} else {//������ǵ�¼����ת����¼ҳ��
				return "loginUI";
			}
		} else {// ������ѵ�¼�û������ж�Ȩ��
            //����ж�Ȩ��ͨ��
			if (user.hasPrivilegeByPrivilegeUrl(privilegeUrl)) {
				return invoke.invoke();
			} else {
				//Ȩ��δͨ���򷵻�  Ȩ����֤ʧ��ҳ��
				return "noPrivilegeError";
			}
		}

	}

}

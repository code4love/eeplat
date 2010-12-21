package com.exedosoft.plat.login;

import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class ResetPassword extends DOAbstractAction {

	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		//do.bx.user.update.passowrd
		
		// 	do.bx.user.findbynameAndPassword
		String old_password = this.actionForm.getValue("old_password");
		String new_password1 = this.actionForm.getValue("new_password1");
		String new_password2 = this.actionForm.getValue("new_password2");
		
		if(old_password==null || "".equals(old_password.trim())){
			this.setEchoValue("�����벻��Ϊ��");
			return NO_FORWARD;
		}
		
		if(!new_password1.equals(new_password2)){
		   this.setEchoValue("��������������벻һ��");
		   return NO_FORWARD;
		   
		}
		
		DOService findUser = DOService.getService("do.bx.user.findbynameAndPassword");
		String userName = DOGlobals.getInstance().getSessoinContext().getUser().getName();
	  
		List users =  findUser.invokeSelect(userName,StringUtil.MD5(old_password));
		
		if(users==null || users.size()==0){
			this.setEchoValue("������ĵľ����벻��ȷ");
			return NO_FORWARD;
		}else{
			
			DOService updatePassword = DOService.getService("do.bx.user.update.passowrd");
			updatePassword.invokeUpdate(StringUtil.MD5(new_password1),DOGlobals.getInstance().getSessoinContext().getUser().getUid());
		}
	
		return DEFAULT_FORWARD;
	}
	
	public static void main(String[] args) throws ExedoException{
		
		DOService aService = DOService.getService("DO_ORG_ACCOUNT_ResetPassword");
		aService.invokeUpdate("a","a");
		
		
	}

}
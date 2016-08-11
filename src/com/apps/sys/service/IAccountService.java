package com.apps.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apps.sys.domain.AccountVO;
import com.apps.sys.domain.QrClient;
import com.framework.core.domain.PageBeanVO;

public interface IAccountService {

	public PageBeanVO<AccountVO> selectAccountPage(PageBeanVO<AccountVO> page,
			AccountVO accountVO);

	public boolean saveAccount(AccountVO accountVO);

	public boolean updateAccount(AccountVO accountVO);

	public boolean delAccount(AccountVO accountVO);

	public AccountVO selectAccountById(AccountVO accountVO);

	public List<AccountVO> checkPassword(AccountVO accountVO);

	public String updatePasswords(AccountVO accountVO);

	public void updateLoginStatus(boolean b,String account_id);

	public AccountVO selectAccountByEsn(AccountVO accountVO);

	public void updateRegister(AccountVO accountVO);

	public boolean updateEsnEmpty(AccountVO account);

	public boolean updateAudit(AccountVO account);

	public List<QrClient> selectQrClient();

	public void updateQrClient(QrClient qrClient);

	public void updateReset(AccountVO account);

	public boolean selectSameAccount(AccountVO account);
	
	public boolean selectUniquePhoneAccount(AccountVO account);
	
	public boolean updateUserInfo(AccountVO account);

	public boolean selectUniqueAndRegPhoneAccount(AccountVO accountVO);

	public List<AccountVO> selectAccountList(AccountVO accountVO);

	/**
	 * 根据登录人查询所在区域
	 */
	public String selectAccountArea(AccountVO account);

}

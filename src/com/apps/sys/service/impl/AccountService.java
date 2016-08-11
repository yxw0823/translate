package com.apps.sys.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.apps.sys.domain.AccountVO;
import com.apps.sys.domain.QrClient;
import com.apps.sys.service.IAccountService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.utils.Const;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.StringUtils;
import com.framework.wight.security.SecurityContextUtil;

@Service
public class AccountService extends BaseService implements IAccountService
{
    /**
     * 创建sql语句
     * 
     * @author 黄登亮
     * @param AccountVO
     * @return sql语句
     */
    public String createSQL(AccountVO accountVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("	SELECT                                       ");
        sql.append(" ( select SUBSTRING_INDEX(GROUP_CONCAT(area), ',', -1)   from m_user_info m where m.account_id=a.account_id)  area   ,  ");
        sql.append("		a.account_id,                              ");
        sql.append("		a.user_icon,                              ");
        // sql.append(" a.department_id, ");
        sql.append("		a.role_id,                                 ");
        // sql.append(" a.position_id, ");
        sql.append("		a.account_name,                            ");
        sql.append("		a.pass_word,                               ");
        sql.append("		a.user_name,                               ");
        sql.append("		a.sex,                                     ");
        sql.append("		a.tel,                                     ");
        sql.append("		a.esn,                                     ");
        sql.append("		a.clientid,                                     ");
        sql.append("		a.is_use,                                  ");
        sql.append(" date_format(a.create_time,'%Y-%m-%d') create_time ,");
        sql.append("		r.role_name                          ");
        sql.append("	FROM                                         ");
        sql.append("		sys_account_manage a ,                     ");
        sql.append("		sys_role_manage r                      ");
        sql.append("	WHERE                                        ");
        sql.append("		1 = 1                                      ");
        // sql.append(" and a.is_use = '1' ");
        sql.append("	and r.role_id = a.role_id			           ");
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getAccount_id()))
        {
            sql.append(" and a.account_id = :account_id 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getPass_word()))
        {
            sql.append(" and a.pass_word = :pass_word 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getDepartment_id()))
        {
            sql.append(" and d.department_id = :department_id 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getUser_name()))
        {
            sql.append(" and a.account_name = :user_name 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getAccount_name()))
        {
            accountVO.setAccount_name(StringUtils.encodeLike(accountVO.getAccount_name(), 3));
            sql.append(" and a.user_name like :account_name 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getTel()))
        {
            sql.append(" and a.tel = :tel 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getCreate_time_begin()))
        {
            sql.append(" and a.create_time  > :create_time_begin 					");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getCreate_time_end()))
        {
            sql.append(" and a.create_time  < :create_time_end				");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getRole_id()))
        {
            sql.append(" and a.role_id  = :role_id				");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getIs_use()))
        {
            sql.append(" and a.is_use  = :is_use				");
        }
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getArea()))
        {
            sql.append(" and ( select SUBSTRING_INDEX(GROUP_CONCAT(area), ',', -1)   from m_user_info m where m.account_id=a.account_id) = :area				");
        }
//        
        return sql.toString();
    }

    /**
     * 账户分页查询
     * 
     * @param page
     * @param article
     * @return
     */
    public PageBeanVO<AccountVO> selectAccountPage(PageBeanVO<AccountVO> page, AccountVO accountVO)
    {
        return this.query4PageExt(page, this.createSQL(accountVO), accountVO);
    }

    /**
     * 根据id查询职位
     * 
     * @author 黄登亮
     * @param AccountVO
     */
    public AccountVO selectAccountById(AccountVO accountVO)
    {
        return (AccountVO) this.queryForClass(this.createSQL(accountVO), AccountVO.class, accountVO);
    }

    /**
     * 保存职位
     * 
     * @author 黄登亮
     * @param AccountVO
     * @return 是否添加成功：true 是，false 否
     */
    public boolean saveAccount(AccountVO accountVO)
    {
        StringBuffer sql = new StringBuffer();
        accountVO.setAccount_id(GuidUtils.getRandomGUID(true));
        // 默认密码为6个8
        accountVO.setPass_word(SecurityContextUtil.encodePassword("888888"));
        sql.append("	 insert into sys_account_manage             ");
        sql.append("	   (account_id,                             ");
        sql.append("	    department_id,                          ");
        sql.append("	    role_id,                                ");
        sql.append("	    user_icon,                                ");
        sql.append("	    position_id,                            ");
        sql.append("	    account_name,                           ");
        sql.append("	    pass_word,                              ");
        sql.append("	    user_name,                              ");
        sql.append("	    sex,                                    ");
        sql.append("	    tel,                                    ");
        sql.append("	    is_use,                                 ");
        sql.append("	    create_time)                            ");
        sql.append("	 values                                     ");
        sql.append("	   (:account_id,                            ");
        sql.append("	    :department_id,                         ");
        sql.append("	    :role_id,                               ");
        sql.append("	    :user_icon,                                ");
        sql.append("	    :position_id,                           ");
        sql.append("	    :account_name,                          ");
        sql.append("	    :pass_word,                             ");
        sql.append("	    :user_name,                             ");
        sql.append("	    :sex,                                   ");
        sql.append("	    :tel,                                   ");
        sql.append("	    1,                                ");
        sql.append("	    now())                                  ");
        return this.execSql(sql.toString(), accountVO) > 0;
    }

    /**
     * 修改职位
     * 
     * @author 黄登亮
     * @param AccountVO
     * @return 是否添加成功：true 是，false 否
     */
    public boolean updateAccount(AccountVO accountVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("	 update sys_account_manage                  ");
        sql.append("	    set department_id = :department_id,     ");
        sql.append("	        role_id       = :role_id,           ");
        sql.append("	        position_id   = :position_id,       ");
        sql.append("	    	user_icon=:user_icon,        ");
        sql.append("	        account_name  = :account_name,      ");
        sql.append("	        user_name     = :user_name,         ");
        sql.append("	        sex           = :sex,               ");
        sql.append("	        tel           = :tel ,              ");
        sql.append(" is_use = :is_use , ");
        sql.append(" esn = :esn , ");
        sql.append(" create_time = :create_time ");
        sql.append("	  where account_id = :account_id            ");
        return this.execSql(sql.toString(), accountVO) > 0;
    }

    /**
     * 根据menu_id删除职位
     * 
     * @author 黄登亮
     * @param menu_id 如果是多个，则使用','分隔
     * @return 是否添加成功：true 是，false 否
     */
    public boolean delAccount(AccountVO accountVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from sys_account_manage				");
        sql.append("   where account_id = :account_id			");
        return this.batchExecSql4List(sql.toString(), extractToList("account_id", accountVO.getAccount_id(),
                AccountVO.class)).length > 0;
    }

    public List<AccountVO> checkPassword(AccountVO accountVO)
    {
        accountVO.setPass_word(SecurityContextUtil.encodePassword(accountVO.getPass_word()));
        StringBuffer sql = new StringBuffer(this.createSQL(accountVO));
        boolean flag = false;
        boolean isMobile = false;
        if (!StringUtils.isEmpty(accountVO.getEsn()))
        {
            isMobile = true;
            int result =
                    Integer.parseInt(String.valueOf(this.queryForColumn(
                            "select count(0) from sys_account_manage where esn = '" + accountVO.getEsn()
                                    + "' and clientid='" + accountVO.getClientid() + "'", String.class)));
            if (result == 0)
            {// 为0 说明esn号不存在 为第一次手机登录 绑定esn号
                flag = true;
            }
        }
        List<AccountVO> accountVOs = this.query(sql.toString(), AccountVO.class, accountVO);
        // 默认不需要更新
        if (!accountVOs.isEmpty())
            accountVOs.get(0).setFlag(true);
        if (isMobile && accountVOs != null & !accountVOs.isEmpty())
        {
            this.execSql(" update sys_account_manage  set loginstatus='0' where account_id ='"
                    + accountVOs.get(0).getAccount_id() + "' ");
        }
        if (flag && accountVOs != null & !accountVOs.isEmpty()
                && (accountVOs.get(0).getEsn() == null || "".endsWith(accountVOs.get(0).getEsn())))
        {
            this.execSql("update sys_account_manage set clientid ='" + accountVO.getClientid() + "',  esn = '"
                    + accountVO.getEsn() + "' where account_id='" + accountVOs.get(0).getAccount_id() + "' ");
            accountVOs.get(0).setFlag(false);
        }
        return accountVOs;
    }

    /**
     * 修改密码
     */
    public String updatePasswords(AccountVO accountVO)
    {
        accountVO.setOld_pass_word(SecurityContextUtil.encodePassword(accountVO.getOld_pass_word()));
        if (!accountVO.getOld_pass_word().equals(accountVO.getPass_word()))
        {
            return "旧密码不正确";
        }
        accountVO.setNew_pass_word(SecurityContextUtil.encodePassword(accountVO.getNew_pass_word()));
        StringBuffer sql = new StringBuffer();
        sql.append(" update  sys_account_manage  ");
        sql.append("  set	pass_word = :new_pass_word ");
        sql.append(" where  account_id = :account_id ");
        this.execSql(sql.toString(), accountVO);
        return "修改成功";
    }

    public void updateLoginStatus(boolean b, String account_id)
    {
        this.execSql(" update sys_account_manage  set loginstatus='" + (b ? "1" : "0") + "' where account_id ='"
                + account_id + "' ");
    }

    public AccountVO selectAccountByEsn(AccountVO accountVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("	SELECT                                       ");
        sql.append("		a.account_id,                              ");
        sql.append("		a.role_id,                                 ");
        sql.append("		a.account_name,                            ");
        sql.append("		a.pass_word,                               ");
        sql.append("		a.user_name,                               ");
        sql.append("		a.sex,                                     ");
        sql.append("		a.tel,                                     ");
        sql.append("		a.esn,                                     ");
        sql.append("		a.clientid,                                     ");
        sql.append("		a.is_use,                                  ");
        sql.append("		a.loginstatus,                                  ");
        sql.append("      a.user_icon,                                  ");
        sql.append(" date_format(a.create_time,'%Y-%m-%d') create_time ,");
        sql.append("		r.role_name                          ");
        sql.append("	FROM                                         ");
        sql.append("		sys_account_manage a ,                     ");
        sql.append("		sys_role_manage r                      ");
        sql.append("	WHERE                                        ");
        sql.append("		1 = 1                                      ");
        sql.append("	and r.role_id = a.role_id			           ");
        if (!StringUtils.isEmpty(accountVO) && !StringUtils.isEmpty(accountVO.getEsn()))
        {
            sql.append(" and a.esn = '" + accountVO.getEsn() + "'		");
        }
        Object obj = this.queryForClass(sql.toString(), AccountVO.class, accountVO);
        if (obj instanceof AccountVO)
        {
            return (AccountVO) obj;
        }
        return null;
    }

    public void updateRegister(AccountVO accountVO)
    {
        if (accountVO.getIs_use() == null)
        {
            accountVO.setIs_use("0");
        }
        if (accountVO.getRole_id() == null)
        {
            accountVO.setRole_id("-1");
        }
        StringBuffer sql = new StringBuffer();
        accountVO.setAccount_id(GuidUtils.getRandomGUID(true));
        accountVO.setPass_word(SecurityContextUtil.encodePassword(accountVO.getPass_word()));
        sql.append("	 insert into sys_account_manage             ");
        sql.append("	   (account_id,                             ");
        sql.append("	    account_name,                           ");
        sql.append("	    pass_word,                              ");
        sql.append("	    user_name,                              ");
        sql.append("	    sex,                                    ");
        sql.append("	    role_id,                                    ");
        sql.append("	    tel,                                    ");
        sql.append("	    is_use,                                 ");
        sql.append("	    create_time)                            ");
        sql.append("	 values                                     ");
        sql.append("	   (:account_id,                            ");
        sql.append("	    :account_name,                          ");
        sql.append("	    :pass_word,                             ");
        sql.append("	    :user_name,                             ");
        sql.append("	    :sex,                                   ");
        sql.append("	    :role_id,                                    ");
        sql.append("	    :tel,                                   ");
        sql.append("	    :is_use,                                ");
        sql.append("	    now())                                  ");
        this.execSql(sql.toString(), accountVO);
    }

    public boolean updateEsnEmpty(AccountVO account)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("update sys_account_manage set clientid=null,  esn = null  where account_id= :account_id ");
        return this.batchExecSql4List(sql.toString(), extractToList("account_id", account.getAccount_id(),
                AccountVO.class)).length > 0;
    }

    public boolean updateAudit(AccountVO account)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("update sys_account_manage set is_use = '1'  where account_id= :account_id ");
        return this.batchExecSql4List(sql.toString(), extractToList("account_id", account.getAccount_id(),
                AccountVO.class)).length > 0;
    }

    public List<QrClient> selectQrClient()
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append("qrid,         ");
        sql.append("qrurl,        ");
        sql.append("qrimg,        ");
        sql.append("create_time   ");
        sql.append(" from client_qr ");
        sql.append(" order by qrid ");
        return this.query(sql.toString(), QrClient.class);
    }

    public void updateQrClient(QrClient qrClient)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("update  client_qr set ");
        sql.append("qrurl = :qrurl,        ");
        sql.append("qrimg = :qrimg,        ");
        sql.append("create_time = now()   ");
        sql.append(" where  qrid = :qrid ");
        this.execSql(sql.toString(), qrClient);
    }

    public void updateReset(AccountVO account)
    {
        String pwd = (SecurityContextUtil.encodePassword("888888"));
        StringBuffer sql = new StringBuffer();
        sql.append("update sys_account_manage set pass_word ='" + pwd + "'  where account_id= :account_id ");
        this.batchExecSql4List(sql.toString(), extractToList("account_id", account.getAccount_id(), AccountVO.class));
    }

    // 注册用户名判断重复 结果为true则有same的 反之 没有same的用户名
    public boolean selectSameAccount(AccountVO account)
    {
        String sql =
                "select count(0) from sys_account_manage where account_name = '" + account.getAccount_name() + "' ";
        return Integer.valueOf((String) this.queryForColumn(sql, String.class)) != 0;
    }

    // 返回true 则可以不注册 返回false 则可以注册 已经注册过了
    public boolean selectUniquePhoneAccount(AccountVO account)
    {
        String sql = " select  count(0)  from m_user_info where tel='" + account.getTel() + "' and status = 1 ";// and
        // account_id
        // is
        // null
        return ((Integer) this.queryForColumn(sql, Integer.class)) == 1;
    }

    public boolean updateUserInfo(AccountVO account)
    {
        String sql =
                " update m_user_info set account_id = '" + account.getAccount_id() + "' where tel = '"
                        + account.getTel() + "' and status = 1 ";
        return this.execSql(sql) > 0;
    }

    public boolean selectUniqueAndRegPhoneAccount(AccountVO accountVO)
    {
        String sql =
                " select  count(0)  from m_user_info where tel='" + accountVO.getTel()
                        + "' and status = 1 and account_id is not null ";//
        return ((Integer) this.queryForColumn(sql, Integer.class)) == 1;
    }

    public List<AccountVO> selectAccountList(AccountVO accountVO)
    {
        String sql =
                " select IFNULL(m.username,s.user_name) user_name ,s.esn from sys_account_manage s left join m_user_info m on s.account_id = m.account_id order by s.create_time desc ";
        return this.query(sql.toLowerCase(), AccountVO.class);
    }

	public String selectAccountArea(AccountVO account) {
		StringBuffer sql = new StringBuffer();
		sql.append("	SELECT                                       ");
		sql.append(" ( select SUBSTRING_INDEX(GROUP_CONCAT(area), ',', -1)   from m_user_info m where m.account_id=a.account_id)  area  ");
		sql.append("	FROM                                         ");
		sql.append("		sys_account_manage a  where 1=1                    ");
		sql.append(" and a.account_id = :account_id 					");
		List<AccountVO> accounts = this.query(sql.toString(), AccountVO.class, account);
		if (!accounts.isEmpty()) {
			return  accounts.get(0).getArea();
		}
		return "未知区域";
	}
}

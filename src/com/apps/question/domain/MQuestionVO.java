package com.apps.question.domain;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company:易象
 * </p>
 * 
 * @author: yanxw
 * @version 1.0
 */
public class MQuestionVO implements Serializable
{

    private String question_id; // 注释：主键 类型：VARCHAR 长度/精度:37 / 0
    private String title; // 注释：问题标题 类型：VARCHAR 长度/精度:100 / 0
    private String content; // 注释：问题内容 类型：TEXT 长度/精度:65535 / 0
    private String user_id; // 注释：提问人 类型：VARCHAR 长度/精度:37 / 0
    private String userName;
    private String question_time; // 注释：提问时间 类型：DATETIME 长度/精度:19 / 0
    private String question_type; // 注释：提问类别 类型：CHAR 长度/精度:1 / 0
    private String question_type_name; // 注释：提问类别 类型：CHAR 长度/精度:1 / 0
    private String state; // 注释：提问状态 '0' 未回复 ,'1' 已回复 类型：CHAR 长度/精度:1 / 0
    private String stateStr;
    private String approval_id; // 注释：审批人 类型：VARCHAR 长度/精度:37 / 0
    private String approvalName;
    private String approval_time; // 注释：审批时间 类型：DATETIME 长度/精度:19 / 0
    private String role_id; // 注释：所属区域 类型：VARCHAR 长度/精度:37 / 0
    private String app_id; // 注释：区域ID 类型：VARCHAR 长度/精度:10 / 0
    private String device_id; // 注释：移动设备标识号 类型：VARCHAR 长度/精度:50 / 0
    private String approval_status; // 注释：移动设备标识号 类型：VARCHAR 长度/精度:50 / 0
    private String sdate;
    private String edate;
	private String Spread1; // 注释：Spread1 类型：VARCHAR 长度/精度:50 / 0
    private String Spread2; // 注释：Spread2 类型：VARCHAR 长度/精度:50 / 0
    private String Spread3; // 注释：Spread3 类型：VARCHAR 长度/精度:50 / 0
    private String Spread4; // 注释：Spread4 类型：VARCHAR 长度/精度:50 / 0
    private String Spread5; // 注释：Spread5 类型：VARCHAR 长度/精度:50 / 0
    private List<MQuestionReplyVO> mqrList;
    public String getQuestion_type_name() {
		return question_type_name;
	}

	public void setQuestion_type_name(String question_type_name) {
		this.question_type_name = question_type_name;
	}

	public String getSdate() {
  		return sdate;
  	}

  	public void setSdate(String sdate) {
  		this.sdate = sdate;
  	}

  	public String getEdate() {
  		return edate;
  	}

  	public void setEdate(String edate) {
  		this.edate = edate;
  	}
    public String getApproval_status()
    {
        return approval_status;
    }

    public void setApproval_status(String approval_status)
    {
        this.approval_status = approval_status;
    }

    public List<MQuestionReplyVO> getMqrList()
    {
        return mqrList;
    }

    public void setMqrList(List<MQuestionReplyVO> mqrList)
    {
        this.mqrList = mqrList;
    }

    public String getQuestion_id()
    {
        return question_id;
    }

    public void setQuestion_id(String question_id)
    {
        this.question_id = question_id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getQuestion_time()
    {
        return question_time;
    }

    public void setQuestion_time(String question_time)
    {
        this.question_time = question_time;
    }

    public String getQuestion_type()
    {
        return question_type;
    }

    public void setQuestion_type(String question_type)
    {
        this.question_type = question_type;
    }

    public String getState()
    {

        return state;
    }

    public void setState(String state)
    {

        this.state = state;
    }

    public String getApproval_id()
    {
        return approval_id;
    }

    public void setApproval_id(String approval_id)
    {
        this.approval_id = approval_id;
    }

    public String getApproval_time()
    {
        return approval_time;
    }

    public void setApproval_time(String approval_time)
    {
        this.approval_time = approval_time;
    }

    public String getRole_id()
    {
        return role_id;
    }

    public void setRole_id(String role_id)
    {
        this.role_id = role_id;
    }

    public String getApp_id()
    {
        return app_id;
    }

    public void setApp_id(String app_id)
    {
        this.app_id = app_id;
    }

    public String getDevice_id()
    {
        return device_id;
    }

    public void setDevice_id(String device_id)
    {
        this.device_id = device_id;
    }

    public String getSpread1()
    {
        return Spread1;
    }

    public void setSpread1(String Spread1)
    {
        this.Spread1 = Spread1;
    }

    public String getSpread2()
    {
        return Spread2;
    }

    public void setSpread2(String Spread2)
    {
        this.Spread2 = Spread2;
    }

    public String getSpread3()
    {
        return Spread3;
    }

    public void setSpread3(String Spread3)
    {
        this.Spread3 = Spread3;
    }

    public String getSpread4()
    {
        return Spread4;
    }

    public void setSpread4(String Spread4)
    {
        this.Spread4 = Spread4;
    }

    public String getSpread5()
    {
        return Spread5;
    }

    public void setSpread5(String Spread5)
    {
        this.Spread5 = Spread5;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getApprovalName()
    {
        return approvalName;
    }

    public void setApprovalName(String approvalName)
    {
        this.approvalName = approvalName;
    }

    public String getStateStr()
    {
        if ("0".equals(state))
        {
            state = "未回复";
        }
        if ("1".equals(state))
        {
            state = "已回复";
        }
        return state;
    }

}

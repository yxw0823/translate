package com.apps.question.domain;

import java.io.Serializable;

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
public class MQuestionReplyVO implements Serializable
{

	private String reply_id; // 注释：回复表ID 类型：VARCHAR 长度/精度:37 / 0
	private String question_id; // 注释：问题表ID 类型：VARCHAR 长度/精度:37 / 0
	private String reply_content; // 注释：回复内容 类型：TEXT 长度/精度:65535 / 0
	private String user_id; // 注释：回复人ID 类型：VARCHAR 长度/精度:37 / 0
	private String userName;
	private String reply_user_id; // 注释：被回复人ID 类型：VARCHAR 长度/精度:37 / 0
	private String replyUserName;
	private String reply_time; // 注释：回复时间 类型：DATETIME 长度/精度:19 / 0
	private String reply_state; // 注释：消息状态 类型：CHAR 长度/精度:1 / 0
	private String Spread1; // 注释：Spread1 类型：VARCHAR 长度/精度:50 / 0
	private String Spread2; // 注释：Spread2 类型：VARCHAR 长度/精度:50 / 0
	private String Spread3; // 注释：Spread3 类型：VARCHAR 长度/精度:50 / 0
	private String Spread4; // 注释：Spread4 类型：VARCHAR 长度/精度:50 / 0
	private String Spread5; // 注释：Spread5 类型：VARCHAR 长度/精度:50 / 0

	public String getReply_id()
	{
		return reply_id;
	}

	public void setReply_id(String reply_id)
	{
		this.reply_id = reply_id;
	}

	public String getQuestion_id()
	{
		return question_id;
	}

	public void setQuestion_id(String question_id)
	{
		this.question_id = question_id;
	}

	public String getReply_content()
	{
		return reply_content;
	}

	public void setReply_content(String reply_content)
	{
		this.reply_content = reply_content;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getReply_user_id()
	{
		return reply_user_id;
	}

	public void setReply_user_id(String reply_user_id)
	{
		this.reply_user_id = reply_user_id;
	}

	public String getReply_time()
	{
		return reply_time;
	}

	public void setReply_time(String reply_time)
	{
		this.reply_time = reply_time;
	}

	public String getReply_state()
	{
		return reply_state;
	}

	public void setReply_state(String reply_state)
	{
		this.reply_state = reply_state;
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

	public String getReplyUserName()
	{
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName)
	{
		this.replyUserName = replyUserName;
	}

}

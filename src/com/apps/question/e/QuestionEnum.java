package com.apps.question.e;

/**
 * 订单状态
 * 
 * 实现带有抽象方法的枚举
 * 
 * @author jiqinlin
 *
 */
public enum QuestionEnum
{

    GJZ("关键字", "QKFY_GZJ"), WTLX("问题类型", "questionType"), NOT_APPROVAL_STATUS("未审批", "1"), APPROVAL_STATUS("已审批", "0");

    // 成员变量
    private String name;
    private String type;

    // 构造方法
    private QuestionEnum(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    // 普通方法
    public static String getName(String type)
    {
        for (QuestionEnum a : QuestionEnum.values())
        {
            if (a.getType().equals(type))
            {
                return a.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

}
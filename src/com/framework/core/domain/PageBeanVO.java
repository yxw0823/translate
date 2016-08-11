package com.framework.core.domain;

import java.io.Serializable;
import java.util.List;
import com.framework.core.utils.Tools;
@SuppressWarnings("unchecked")
public class PageBeanVO<E> implements Serializable
{
    private static final long serialVersionUID = 2965287428306185005L;
    private List<E> resList; // 结果集
    private int resTotal; // 总记录数
    private int pageNo; // 当前页数
    private int pageSize; // 每页显示条数
    private String page; // 每页显示条数
    private String rows; // 当前页数
    private String sort;
    private String order;
    private int offset;
    private int countpage;
    private String start; // Ext当前页
    private String limit;// Ext每页显示条数
    private String dir;// Ext排序规则
    private int currpage;

    private int first;// 首页索引
    private int last;// 尾页索引
    private int prev;// 上一页索引
    private int next;// 下一页索引
    private boolean firstPage;// 是否是第一页
    private boolean lastPage;// 是否是最后一页
    private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。
    private int length = 10;// 显示页面长度
    private int slider = 1;// 前后显示页面长度

    private String pageString;

    public String getPageString()
    {
        setPageString(toString());
        return pageString;
    }

    public void setPageString(String pageString)
    {
        this.pageString = pageString;
    }

    public boolean isFirstPage()
    {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage)
    {
        this.firstPage = firstPage;
    }

    public boolean isLastPage()
    {
        return lastPage;
    }

    public void setLastPage(boolean lastPage)
    {
        this.lastPage = lastPage;
    }


    public int getCurrpage()
    {
        return currpage;
    }

    public void setCurrpage(int currpage)
    {
        this.currpage = currpage;
    }

    public int getCountpage()
    {
        return countpage;
    }

    public void setCountpage(int countpage)
    {
        this.countpage = countpage;
    }

    public PageBeanVO()
    {
        resTotal = Integer.valueOf(Tools.InitNnmForNUll(String.valueOf(resTotal)));
        pageNo = Integer.valueOf(Tools.InitNnmForNUll(String.valueOf(pageNo)));
        countpage = Integer.valueOf(Tools.InitNnmForNUll(String.valueOf(countpage)));
        pageSize = Integer.valueOf(Tools.InitNnmForNUll(String.valueOf(pageSize)));
        offset = Integer.valueOf(Tools.InitNnmForNUll(String.valueOf(offset)));
        page = Tools.InitNnmForNUll(page);
        page = page.equals("0") ? "1" : page;
        rows = Tools.InitNnmForNUll(rows);
        start = null == start ? "0" : start;
        limit = null == limit ? "10" : limit;
        // toString();
    }

    public String getStart()
    {
        return start;
    }

    public void setStart(String start)
    {
        this.start = start;
    }

    public String getLimit()
    {
        return limit;
    }

    public void setLimit(String limit)
    {
        this.limit = limit;
    }

    public String getDir()
    {
        return dir;
    }

    public void setDir(String dir)
    {
        this.dir = dir;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }

    public String getRows()
    {
        return rows;
    }

    public void setRows(String rows)
    {
        this.rows = rows;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public List getResList()
    {
        return resList;
    }

    public void setResList(List resList)
    {
        this.resList = resList;
    }

    public int getResTotal()
    {
        return resTotal;
    }

    public void setResTotal(int resTotal)
    {
        this.resTotal = resTotal;
    }

    /**
     * 默认输出当前分页标签 <div class="page">${page}</div>
     */
    @Override
    public String toString()
    {

        initialize();

        StringBuilder sb = new StringBuilder();

        if (currpage == first)
        {// 如果是首页
            sb.append("<li class=\"disabled\"><a onclick=\"javascript:\">&#171; 上一页</a></li>\n");
        }
        else
        {
            sb.append("<li><a onclick=\"javascript:" + funcName + "(" + prev + "," + pageSize
                    + ");\">&#171; 上一页</a></li>\n");
        }

        int begin = currpage - (length / 2);

        if (begin < first)
        {
            begin = first;
        }

        int end = begin + length - 1;

        if (end >= last)
        {
            end = last;
            begin = end - length + 1;
            if (begin < first)
            {
                begin = first;
            }
        }

        if (begin > first)
        {
            int i = 0;
            for (i = first; i < first + slider && i < begin; i++)
            {
                sb.append("<li><a onclick=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">"
                        + (i + 1 - first) + "</a></li>\n");
            }
            if (i < begin)
            {
                sb.append("<li class=\"disabled\"><a onclick=\"javascript:\">...</a></li>\n");
            }
        }

        for (int i = begin; i <= end; i++)
        {
            if (i == currpage)
            {
                sb.append("<li class=\"active\"><a onclick=\"javascript:\">" + (i + 1 - first) + "</a></li>\n");
            }
            else
            {
                sb.append("<li><a onclick=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">"
                        + (i + 1 - first) + "</a></li>\n");
            }
        }

        if (last - end > slider)
        {
            sb.append("<li class=\"disabled\"><a onclick=\"javascript:\">...</a></li>\n");
            end = last - slider;
        }

        for (int i = end + 1; i <= last; i++)
        {
            sb.append("<li><a onclick=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first)
                    + "</a></li>\n");
        }

        if (currpage == last)
        {
            sb.append("<li class=\"disabled\"><a onclick=\"javascript:\">下一页 &#187;</a></li>\n");
        }
        else
        {
            sb.append("<li><a onclick=\"javascript:" + funcName + "(" + next + "," + pageSize + ");\">"
                    + "下一页 &#187;</a></li>\n");
        }

        // sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前
        // ");
        // sb.append("<input type=\"text\" value=\""+currpage+"\"
        // onkeypress=\"var
        // e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
        // sb.append(funcName+"(this.value,"+pageSize+");\"
        // onclick=\"this.select();\"/> / ");
        // sb.append("<input type=\"text\" value=\""+pageSize+"\"
        // onkeypress=\"var e=window.event||this;var
        // c=e.keyCode||e.which;if(c==13)");
        // sb.append(funcName+"("+currpage+",this.value);\"
        // onclick=\"this.select();\"/> 条，");
        sb.append("<li class=\"disabled controls\"><a onclick=\"javascript:\">共 " + resTotal + " 条记录</a><li>\n");

        sb.insert(0, "<ul class=\"pagination\" style=\"width: auto;float: none\">\n").append("</ul>\n");

        sb.append("<div style=\"clear:both;\"></div>");
        setPageString(sb.toString());
        return sb.toString();
    }

    /**
     * 初始化参数
     */
    public void initialize()
    {

        try
        {
            // 1
            this.first = 1;

            this.last = (int) (resTotal / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

            if (this.resTotal % this.pageSize != 0 || this.last == 0)
            {
                this.last++;
            }

            if (this.last < this.first)
            {
                this.last = this.first;
            }

            if (this.currpage <= 1)
            {
                this.currpage = this.first;
                this.firstPage = true;
            }

            if (this.currpage >= this.last)
            {
                this.currpage = this.last;
                this.lastPage = true;
            }

            if (this.currpage < this.last - 1)
            {
                this.next = this.currpage + 1;
            }
            else
            {
                this.next = this.last;
            }

            if (this.currpage > 1)
            {
                this.prev = this.currpage - 1;
            }
            else
            {
                this.prev = this.first;
            }

            // 2
            if (this.currpage < this.first)
            {// 如果当前页小于首页
                this.currpage = this.first;
            }

            if (this.currpage > this.last)
            {// 如果当前页大于尾页
                this.currpage = this.last;
            }
        }
        catch (RuntimeException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

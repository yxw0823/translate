package com.apps.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.sys.domain.HotWord;
import com.apps.sys.service.IHotWordService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.StringUtils;

@Service
public class HotWordService extends BaseService implements IHotWordService
{
    public PageBeanVO<HotWord> selectHotWordPage(PageBeanVO<HotWord> page, HotWord word)
    {
        StringBuffer sql = new StringBuffer();
        // sql.append(" select w.* from m_hotword w ");
        // if (!StringUtils.isEmpty(word.getHot_name())) {
        // word.setHot_name(StringUtils.encodeLike(word.getHot_name(), 3));
        // sql.append(" and w.hot_name like :hot_name");
        // }
        sql.append(" select w.hot_name,sum(w.hot_rows) hot_rows,count(w.hot_name) hot_count, ");
        sql.append(" max(hot_time) hot_time");
        sql.append("  from m_hotword  w where 1=1 ");
        if (!StringUtils.isEmpty(word.getHot_source()))
        {
            sql.append(" and w.hot_source like :hot_source  ");
        }
        sql.append("  group by w.hot_name ");
        return this.query4PageExt(page, sql.toString(), word);
    }

    public void saveHotWord(HotWord word)
    {
        word.setHot_id(GuidUtils.getRandomGUID(true));
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into m_hotword ( hot_id,hot_name,hot_rows,hot_time,hot_source ) ");
        sql.append(" values ( :hot_id,:hot_name,:hot_rows,now(),:hot_source )");
        this.execSql(sql.toString(), word);
    }

    public void deleteHotWord(HotWord word)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from  m_hotword where  hot_name = :hot_name  ");
        this.batchExecSql4List(sql.toString(), extractToList("hot_name", word.getHot_id(), HotWord.class));
    }

    public List<HotWord> selectTopHotWord(HotWord word, Integer topNum, Integer source)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" select  w.hot_name,count(w.hot_rows) as n from m_hotword  w where 1=1");
        if (!StringUtils.isEmpty(word.getHot_source()))
        {
            sql.append(" and w.hot_source like :hot_source  ");
        }
        sql.append("  group by w.hot_name");
        sql.append(" order by  n desc LIMIT 0,10");
        // sql.append(" order by w.hot_time");
        return this.query(sql.toString(), HotWord.class, word);
    }
}

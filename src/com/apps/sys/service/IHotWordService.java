package com.apps.sys.service;

import java.util.List;

import com.apps.sys.domain.HotWord;
import com.framework.core.domain.PageBeanVO;

public interface IHotWordService {
	//分页
	public PageBeanVO<HotWord> selectHotWordPage(PageBeanVO<HotWord> page,HotWord word);
	//保存
	public void saveHotWord(HotWord word);
	//查询
	public void deleteHotWord(HotWord word);
	//取前多少条  指定来源
	public List<HotWord> selectTopHotWord(HotWord word,Integer topNum,Integer source);
	
}

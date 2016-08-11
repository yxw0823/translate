package com.framework.core.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"unchecked","deprecation"})
public class SqlData {

	@Autowired
    private SimpleJdbcTemplate sjdbcTemplate;
    public static Map<Integer, List<CodesSqlVO>> sqlVOMap = new HashMap<Integer, List<CodesSqlVO>>();
/*    static {
        ArrayList<CodesSqlVO> al = new ArrayList<CodesSqlVO>();    //广东
        al.add(new CodesSqlVO(Integer.valueOf(1),"广州市","","",""));    
        al.add(new CodesSqlVO(Integer.valueOf(2),"梅州市","","",""));
        sqlVOMap.put(1, al);
        
        al = new ArrayList<CodesSqlVO>();    //上海
        al.add(new CodesSqlVO(Integer.valueOf(1),"青浦区","","",""));    
        al.add(new CodesSqlVO(Integer.valueOf(2),"闵行区","","",""));
        sqlVOMap.put(2, al);
    }
    */
    /**
     * 此方法可以换成从数据库得到数据
     * @param 
     * @return
     */
	protected static ParameterizedBeanPropertyRowMapper resultBeanMapper(Class clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	protected static BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		return new BeanPropertySqlParameterSource(object);
	}
	
	public List<CodesSqlVO> getSqlByCode(Integer Id) {
    	CodesSqlVO codesSqlVO = new CodesSqlVO();
    	codesSqlVO.setId(Id);
		String sql = "select * from tb_sys_codes_sql t";
		List<CodesSqlVO> lt= sjdbcTemplate.query(sql, resultBeanMapper(CodesSqlVO.class), paramBeanMapper(codesSqlVO));
        sqlVOMap.put(Id, lt);
        return sqlVOMap.get(Id);
    }


}
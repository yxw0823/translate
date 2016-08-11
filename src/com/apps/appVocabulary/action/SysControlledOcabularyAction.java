package com.apps.appVocabulary.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.appVocabulary.domain.SysControlledOcabularyVO;
import com.apps.appVocabulary.service.ISysControlledOcabularyService;
import com.framework.core.action.BaseToolAction;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.OfficeUtils;

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
 * @author: yanxuewen
 * @version 1.0
 */
@Controller
@RequestMapping("/apps/appVocabulary/SysControlledOcabularyAction.do")
public class SysControlledOcabularyAction extends BaseToolAction
{
    @Autowired
    private ISysControlledOcabularyService sysControlledOcabularyService;

    @RequestMapping(params = "method=gotoView")
    public String gotoView(String type, PageBeanVO<SysControlledOcabularyVO> page,
            SysControlledOcabularyVO sysControlledOcabulary, HttpServletRequest request, HttpServletResponse response,
            HttpSession session, ModelMap map)
    {
        if (type.equals("handler"))
        {
            return "/jsp/backmng/appVocabulary/sysControlledOcabulary_handler";
        }
        if (type.equals("update"))
        {
            sysControlledOcabulary = this.sysControlledOcabularyService
                    .selectSysControlledOcabularyVOById(sysControlledOcabulary);
            map.put("sysControlledOcabulary", sysControlledOcabulary);
            // 通过ID查询出词表对象
            return "/jsp/backmng/appVocabulary/sysControlledOcabulary_update";
        }
        if (type.equals("manage"))
        {
            page = this.sysControlledOcabularyService.selectSysControlledOcabularyVOPage(page, sysControlledOcabulary);
            map.put("page", page);
            map.put("sysControlledOcabulary", sysControlledOcabulary);
            /*
             * if ("5".equals(map.get("infotype"))) { return
             * "/jsp/backmng/informationMessage/mMessage"; }
             */
            return "/jsp/backmng/appVocabulary/sysControlledOcabulary_grid";
        }
        return "";
    }

    /**
     * 分页查询
     * 
     * @param page
     *            分页参数
     * @param sysControlledOcabulary
     * @return
     */
    @RequestMapping(params = "method=selectSysControlledOcabularyVOPage")
    public void selectSysControlledOcabularyVOPage(PageBeanVO<SysControlledOcabularyVO> page,
            SysControlledOcabularyVO sysControlledOcabulary, HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
    {
        this.pagerToJsonString(response,
                this.sysControlledOcabularyService.selectSysControlledOcabularyVOPage(page, sysControlledOcabulary));
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(params = "method=selectSysControlledOcabularyVOById")
    public void selectSysControlledOcabularyVOById(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        sysControlledOcabulary = this.sysControlledOcabularyService
                .selectSysControlledOcabularyVOById(sysControlledOcabulary);
        this.responseJsonExtForm(response, sysControlledOcabulary);
    }

    /**
     * 根据conid返回list
     */
    @RequestMapping(params = "method=selectSysControlledOcabularyVOListToJson")
    public void selectSysControlledOcabularyVOListToJson(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        this.responseJsonArray(response,
                this.sysControlledOcabularyService.selectSysControlledOcabularyVOBycoding(sysControlledOcabulary));
    }

    /**
     * 修改单条数据
     */
    @RequestMapping(params = "method=updateSysControlledOcabularyVO")
    public String updateSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map)
    {
        // 更新
        this.sysControlledOcabularyService.updateSysControlledOcabularyVO(sysControlledOcabulary);
        map.put("sort", "[{property:'coding',direction:'DESC'}]");
        return "redirect:/apps/appVocabulary/SysControlledOcabularyAction.do?method=gotoView&type=manage&start=0&limit=10";
    }

    /**
     * 新增单条数据
     */
    @RequestMapping(params = "method=saveSysControlledOcabularyVO")
    public String saveSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map)
    {
        this.sysControlledOcabularyService.saveSysControlledOcabularyVO(sysControlledOcabulary);
        map.put("sort", "[{property:'coding',direction:'DESC'}]");
        return "redirect:/apps/appVocabulary/SysControlledOcabularyAction.do?method=gotoView&type=manage&start=0&limit=10";
    }

    @RequestMapping(params = "method=saveSysControlledOcabularyVOToJson")
    public void saveSysControlledOcabularyVOToJson(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map)
    {

        this.sysControlledOcabularyService.saveSysControlledOcabularyVO(sysControlledOcabulary);
        this.responseJsonArray(response, this.sysControlledOcabularyService
                .selectSysControlledOcabularyVOBycoding(sysControlledOcabulary.getCoding()));

    }

    /**
     * 批量删除多条数据
     */
    @RequestMapping(params = "method=delSysControlledOcabularyVO")
    public String delSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map)
    {
        this.sysControlledOcabularyService.delSysControlledOcabularyVO(sysControlledOcabulary);
        map.put("sort", "[{property:'coding',direction:'DESC'}]");
        return "redirect:/apps/appVocabulary/SysControlledOcabularyAction.do?method=gotoView&type=manage&start=0&limit=10";
    }

    /**
     * 批量删除多条数据
     */
    @RequestMapping(params = "method=delSysControlledOcabularyVOToJson")
    public void delSysControlledOcabularyVOToJson(SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map)
    {
        this.sysControlledOcabularyService.delSysControlledOcabularyVO(sysControlledOcabulary);
        this.responseText(response, "true");
    }

    @RequestMapping(params = "method=updateImport")
    public String updateImport(ModelMap map, SysControlledOcabularyVO sysControlledOcabulary,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String path = request.getRealPath("/") + request.getParameter("filePath");
        String fields = "operatetype_id,content,coding";
        List<SysControlledOcabularyVO> list = OfficeUtils.parseExcel(path, SysControlledOcabularyVO.class, fields);
        if (!list.isEmpty())
        {
            list = list.subList(1, list.size());
        }
        for (SysControlledOcabularyVO u : list)
        {
            u.setOcabulary_id(GuidUtils.getRandomGUID(true));
            u.setState("1");
        }
        if (!list.isEmpty())
        {
            this.sysControlledOcabularyService.saveSysControlledOcabularyBatch(list);
        }
        map.put("msg", "导入成功");
        map.put("sort", "[{property:'coding',direction:'DESC'}]");
        return "redirect:/apps/appVocabulary/SysControlledOcabularyAction.do?method=gotoView&type=manage&start=0&limit=10";
    }
}

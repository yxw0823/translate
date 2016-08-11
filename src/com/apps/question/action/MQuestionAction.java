package com.apps.question.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apps.appVocabulary.domain.SysControlledOcabularyVO;
import com.apps.appVocabulary.service.ISysControlledOcabularyService;
import com.apps.guestInfo.domain.MGuestInfoVO;
import com.apps.question.domain.MQuestionReplyVO;
import com.apps.question.domain.MQuestionVO;
import com.apps.question.e.QuestionEnum;
import com.apps.question.service.IMQuestionReplyService;
import com.apps.question.service.IMQuestionService;
import com.apps.sys.domain.AccountVO;
import com.apps.sys.domain.RoleVO;
import com.apps.userinfo.domain.VoUserinforVO;
import com.framework.core.action.BaseToolAction;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.utils.Const;
import com.framework.core.utils.OfficeUtils;
import com.framework.core.utils.StringUtils;

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
 * @author: yxw
 * @version 1.0
 */
@Controller
@RequestMapping("/apps/question/MQuestionAction.do")
public class MQuestionAction extends BaseToolAction
{
    @Autowired
    private IMQuestionService mQuestionService;
    // 数字字典
    @Autowired
    private ISysControlledOcabularyService sysControlledOcabularyService;
    @Autowired
    private IMQuestionReplyService mQuestionReplyService;

    @RequestMapping(params = "method=gotoView")
    public String gotoView(String type, PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion,
            HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap map)
    {
        if (type.equals("grid"))
        {
          
            if (StringUtils.isEmpty(page.getSort()))
            {
                page.setSort("[{property:'question_time',direction:'DESC'}]");
            }
            // 查询所有问题
            map.put("page", this.mQuestionService.selectMQuestionVOPage(page, mQuestion));
            // 查询数字字典 获取咨询类别
            map.put(QuestionEnum.WTLX.getType(), getQuestionType(QuestionEnum.WTLX.getType()));
            // 查询数据字典，获取关键字
            map.put(QuestionEnum.GJZ.getType(), getQuestionType(QuestionEnum.GJZ.getType()));
            //请求的mQuestion
            if(!StringUtils.isEmpty(mQuestion) && !StringUtils.isEmpty(mQuestion.getTitle())){
            	mQuestion.setTitle(mQuestion.getTitle().replaceAll("%", ""));
            }
            map.put("mQuestion", mQuestion);
            return "/jsp/backmng/question/questionList";
        }

        return "";
    }

    /**
     * 分页查询
     * 
     * @param page
     *            分页参数
     * @param mQuestion
     * @return
     */
    @RequestMapping(params = "method=selectMQuestionVODatebasePage")
    public void selectMQuestionVODatebasePage(PageBeanVO<MQuestionVO> page, String sEcho,String iDisplayStart,String iDisplayLength, MQuestionVO mQuestion, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
    {
    	 String jsondata=request.getParameter("aoData");  
    	 JSONArray jsonarray= JSONArray.parseArray(jsondata);  
    	
         for(int i=0;i<jsonarray.size();i++) //从传递参数里面选出待用的参数  
         {  
             JSONObject obj=(JSONObject)jsonarray.get(i);  
             if(obj.get("name").equals("sEcho"))  
                 sEcho=obj.get("value").toString();  
             if(obj.get("name").equals("iDisplayStart"))  
                 iDisplayStart=obj.get("value").toString();  
             if(obj.get("name").equals("iDisplayLength"))  
                 iDisplayLength=obj.get("value").toString();  
             //System.out.println("name:"+obj.get("name")+";value:"+obj.get("value"));  
         }  
    	   if (StringUtils.isEmpty(page.getSort()))
           {
               page.setSort("[{property:'question_time',direction:'DESC'}]");
           }
    	  page.setStart(iDisplayStart);
        this.pagerToDataTablesJsonString(response, this.mQuestionService.selectMQuestionVOPage(page, mQuestion));
    }

    /**
     * 政务热线 手机端人口方法
     * 
     * @param page
     * @param mQuestion
     * @param request
     * @param response
     * @param session
     * @param map
     * @return 所有热线和热线分类
     */
    @RequestMapping(params = "method=selectQuestionPage")
    public void selectQuestionPage(PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
       
        if (StringUtils.isEmpty(page.getSort()))
        {
            page.setSort("[{property:'question_time',direction:'DESC'}]");
        }
     
        // 查询所有审批通过的问题
    	//  mQuestion.setApproval_status(QuestionEnum.APPROVAL_STATUS.getType());
    	//  map.put("page", this.mQuestionService.selectMQuestionVOPage(page, mQuestion));
        // 查询数字字典 获取咨询类别
        map.put(QuestionEnum.WTLX.getType(), getQuestionType(QuestionEnum.WTLX.getType()));
        this.responseJson(response, map);
    }

    /**
     * 查询数字字典 获取咨询类别
     * 
     * @return
     */
    public List<SysControlledOcabularyVO> getQuestionType(String type)
    {

        // 查询数字字典 questionType 获取
        List<SysControlledOcabularyVO> listSco = sysControlledOcabularyService
                .selectSysControlledOcabularyVOBycoding(type);
        return listSco;
    }
    /**
     * 分页查询 手机端
     * 
     * @param page
     *            分页参数
     * @param mQuestion
     * @return
     */
    @RequestMapping(params = "method=selectMQuestionVOPage")
    public void selectMQuestionVOPage(PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
       // mQuestion.setApproval_status(QuestionEnum.APPROVAL_STATUS.getType());
        this.pagerToJsonString(response, this.mQuestionService.selectMQuestionVOPage(page, mQuestion));
    }
    /**
     * 分页查询 手机端
     * 
     * @param page
     *            分页参数
     * @param mQuestion
     * @return
     */
    @RequestMapping(params = "method=selectMQuestionVOPageToMobile")
    public void selectMQuestionVOPageToMobile(PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
       //mQuestion.setApproval_status(QuestionEnum.APPROVAL_STATUS.getType());
        this.pagerToJsonString(response, this.mQuestionService.selectMQuestionVOPage(page, mQuestion));
    }

    /**
     * 
     * 根据ID查询 热线详细信息
     */
    @RequestMapping(params = "method=selectMQuestionVOById")
    public void selectMQuestionVOById(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
    {
        // 进入政务热线详情界面
        mQuestion = this.mQuestionService.selectMQuestionVOById(mQuestion);
        MQuestionReplyVO mqr = new MQuestionReplyVO();
        mqr.setQuestion_id(mQuestion.getQuestion_id());
        mQuestion.setMqrList(mQuestionReplyService.selectMQuestionReplyVOByQid(mqr));
        this.responseJson(response, mQuestion);
    }

    /**
     * 
     * 根据ID查询 热线详细信息
     */
    @RequestMapping(params = "method=selectMQuestionById")
    public String selectMQuestionById(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session, ModelMap map)
    {
        // 进入政务热线详情界面
        mQuestion = this.mQuestionService.selectMQuestionVOById(mQuestion);
        MQuestionReplyVO mqr = new MQuestionReplyVO();
        mqr.setQuestion_id(mQuestion.getQuestion_id());
        mQuestion.setMqrList(mQuestionReplyService.selectMQuestionReplyVOByQid(mqr));
        map.put("mQuestion", mQuestion);
        return "/jsp/backmng/question/questionDetail";
    }

    /**
     * 修改单条数据
     */
    @RequestMapping(params = "method=updateMQuestionVO")
    public void updateMQuestionVO(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
    {

        this.responseHtml(response, String.valueOf(this.mQuestionService.updateMQuestionVO(mQuestion)));
    }

    /**
     * 修改单条数据
     */
    @RequestMapping(params = "method=updateApprovalStatus")
    public void updateApprovalStatus(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
    {

        this.responseHtml(response, String.valueOf(this.mQuestionService.updateApprovalStatus(mQuestion)));
    }

    /**
     * 管理员回复
     * 
     * @param mQuestion
     * @param request
     * @param response
     * @param session
     */

    @RequestMapping(params = "method=replyRontent")
    public void replyRontent(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session, MQuestionReplyVO mqr)
    {
        // 获取当前登陆人。
        AccountVO account = (AccountVO) request.getSession().getAttribute(Const.SESSION_USER);
        if (account == null)
        {
            // 如果没有登陆信息则不可以进行回复
            this.responseText(response, "false");
            return;
        }
        // 查询出详情
        mQuestion = this.mQuestionService.selectMQuestionVOById(mQuestion);
        // 设置当前回复状态为已回复
        mQuestion.setState("1");
        // 设置回复人信息
        mQuestion.setApproval_id(account.getAccount_id());
        // 统一往问题表中插入回复数据
        mqr.setQuestion_id(mQuestion.getQuestion_id());
        // 当前回复人ID
        mqr.setUser_id(account.getAccount_id());
        // 被回复人ID
        mqr.setReply_user_id(mQuestion.getUser_id());
        // 标识为栏目管理员回复
        mqr.setSpread1("Admin");

        // 保存到回复表
        mQuestionReplyService.saveMQuestionReplyVO(mqr);
        this.responseHtml(response, String.valueOf(this.mQuestionService.updateMQuestionVO(mQuestion)));
    }

    /**
     * 手机回复
     * 
     * @param mQuestion
     * @param request
     * @param response
     * @param session
     */

    @RequestMapping(params = "method=moblieReplyRontent")
    public void moblieReplyRontent(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session, MQuestionReplyVO mqr)
    {
        // 获取当前登陆人。
    	VoUserinforVO guestInfo = (VoUserinforVO) request.getSession().getAttribute(Const.SESSION_GUEST_USER);
        if (guestInfo == null)
        {
            // 如果没有登陆信息则不可以进行回复
            this.responseText(response, "false");
            return;
        }
        // 统一往问题表中插入回复数据
        mqr.setQuestion_id(mQuestion.getQuestion_id());
        // 当前回复人ID
        mqr.setUser_id(guestInfo.getUserid());
        mqr.setUserName(guestInfo.getShortname());
        // 被回复人ID
        mqr.setReply_user_id(mQuestion.getUser_id());
        // 保存到回复表
        this.responseText(response, String.valueOf(mQuestionReplyService.saveMQuestionReplyVO(mqr)));
    }

    /**
     * 新增单条数据
     */
    @RequestMapping(params = "method=saveMQuestionVO")
    public void saveMQuestionVO(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
    {
        this.responseHtml(response, String.valueOf(this.mQuestionService.saveMQuestionVO(mQuestion)));
    }

    /**
     * 新增单条数据手机端数据插入
     */
    @RequestMapping(params = "method=saveMQuestionVOFToMbolie")
    public void saveMQuestionVOFToMbolie(MQuestionVO mQuestion, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
    {
    	VoUserinforVO guestInfo = (VoUserinforVO) request.getSession().getAttribute(Const.SESSION_GUEST_USER);
        if (guestInfo == null)
        {
            this.responseText(response, "false");
            return;
        }
        mQuestion.setUser_id(guestInfo.getUserid());
        mQuestion.setUserName(guestInfo.getShortname());
       // List<SysControlledOcabularyVO> list = getQuestionType(QuestionEnum.GJZ.getType());
        // 获取默认为未回复
        mQuestion.setState("0");
        mQuestion.setApproval_status(QuestionEnum.APPROVAL_STATUS.getType());
      /*  for (SysControlledOcabularyVO cOcabularyVO : list)
        {
            if (mQuestion.getContent().indexOf(cOcabularyVO.getContent()) != -1
                    || mQuestion.getTitle().indexOf(cOcabularyVO.getContent()) != -1)
            {
                // 设置为需要审批状态
                mQuestion.setApproval_status(QuestionEnum.NOT_APPROVAL_STATUS.getType());
                break;
            }
        }*/

        this.responseText(response, String.valueOf(this.mQuestionService.saveMQuestionVO(mQuestion)));
    }

    /**
     * 批量删除多条数据
     */
    @RequestMapping(params = "method=delMQuestionVO")
    public void delMQuestionVO(MQuestionVO mQuestion, HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
    {
        MQuestionReplyVO mqr = new MQuestionReplyVO();
        // 删除关联的所有回复
        mqr.setQuestion_id(mQuestion.getQuestion_id());
        mQuestionReplyService.delMQuestionReplyVOByQuestionId(mqr);
        this.responseText(response, String.valueOf(this.mQuestionService.delMQuestionVO(mQuestion)));

    }

    /**
     * 删除回复
     */
    @RequestMapping(params = "method=delReply")
    public String delReply(MQuestionReplyVO mqr, MQuestionVO mQuestion, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
    {
        mQuestionReplyService.delMQuestionReplyVOByQuestionId(mqr);
        return "redirect:/apps/question/MQuestionAction.do?method=selectMQuestionById&question_id="
                + mQuestion.getQuestion_id();
    }
    
    /**
     * 批量导出提案
     */
    @RequestMapping(params = "method=downExport")
    public void downExport(PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion,HttpServletRequest request, HttpServletResponse response,
            HttpSession session){
    	 page.setStart("0");
         page.setLimit("5000");
         page.setSort("[{property:'question_time',direction:'DESC'}]");
         page= this.mQuestionService.selectMQuestionVOPage(page, mQuestion);
         String serverPath = request.getRealPath("/");
         String folder = Const.QUESTION_EXPORT;
         String filename ="预提案.xls";
         String columnNames = "提案ID,提案类型,提案标题,提案内容,提案时间,发表人";
         String valueNames = "question_id,question_type_name,title,content,question_time,userName";
         String savePath = OfficeUtils.createExcel(serverPath, folder, filename, columnNames.split(","),
                 page.getResList(), valueNames.split(","), null);
         this.responseHtml(response, savePath);
    }
}

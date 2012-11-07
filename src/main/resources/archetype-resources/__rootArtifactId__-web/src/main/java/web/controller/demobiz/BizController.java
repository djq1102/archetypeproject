package ${package}.web.controller.demobiz;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ${package}.busi.demobiz.BizManagent;
import ${package}.core.orm.Page;
import ${package}.core.web.BaseController;
import ${package}.domain.option.DemoBizOption;
import ${package}.domain.pojo.DemoBiz;

/**
 * 
 * 商户列表controller
 * 包括商户的增删改查
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: BizController.java 3 2012-05-30 03:27:37Z djq $ 
 * @since 2.0
 */
@Controller
@RequestMapping("/demobiz")
public class BizController extends BaseController{
    
    @Autowired
    private BizManagent bizManagent;
    
    @Autowired 
    private Validator bizValidator;
    
    private  final String bizList = "demobiz/list"; 
    
    private  final String bizEdit = "demobiz/edit";
    
    private final String bizView = "demobiz/info";
    
    private final String success = "demobiz/success";
    
    private final String bizCreate = "demobiz/create";
    
    @InitBinder("demoBiz")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(bizValidator);
    }
    
    /**
     * 根据查询条件获取商户列表
     * @param biz
     *          查询条件封装
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView getBizList(DemoBizOption option){
        if(option.getPageNo() == 0){
            option.setPageNo(1);
        }
        option.setPageSize(10);
        final Page<DemoBiz> bizPage = bizManagent.getBizListByOption(option);
        final ModelAndView mav = new ModelAndView();
        mav.setViewName(bizList);
        mav.addObject("page",bizPage);
        return mav;
    }
    
    @RequestMapping("/createForm")
    public String creatInfo(Map model){
        DemoBiz validationForm = new DemoBiz();
        model.put("demoBiz", validationForm);
        return bizCreate;
    }
    /**
     * 创建商户信息
     * @param biz
     * @return
     */
    @RequestMapping("/create")
    public String ModelAndView(@Valid @ModelAttribute("demoBiz")DemoBiz demoBiz,BindingResult bizBindingResult){
        if(bizBindingResult.hasErrors()){
            return bizCreate;
        }
        bizManagent.insertBiz(demoBiz);
        return success;
    }
    
    @RequestMapping("/update")
    public String updateBiz(@Valid DemoBiz biz,BindingResult bizBindingResult){
        if(bizBindingResult.hasErrors()){
            return bizEdit;
        }
        bizManagent.updateBiz(biz);
        final ModelAndView mav = new ModelAndView();
        mav.setViewName(success);
        return success;
    }
    
    @RequestMapping("/edit")
    public ModelAndView editBiz(BigDecimal id){
        final DemoBiz biz = bizManagent.getBizByBizId(id);
        final ModelAndView mav = new ModelAndView();
        mav.setViewName(bizEdit);
        mav.addObject("demoBiz",biz);
        return mav;
    }
    
    @RequestMapping("/info")
    public ModelAndView viewBiz(BigDecimal id){
        final DemoBiz biz = bizManagent.getBizByBizId(id);
        final ModelAndView mav = new ModelAndView();
        mav.setViewName(bizView);
        mav.addObject("demoBiz",biz);
        return mav;
    }
    
}

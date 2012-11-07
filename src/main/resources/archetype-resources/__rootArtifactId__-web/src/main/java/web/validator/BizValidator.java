package ${package}.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ${package}.busi.demobiz.BizManagent;
import ${package}.domain.option.DemoBizOption;
import ${package}.domain.pojo.DemoBiz;

public class BizValidator implements Validator {

    @Autowired 
    private BizManagent bizManagent;
    @Override
    public boolean supports(Class<?> clazz) {
        return DemoBiz.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty.demoBiz.name");
        ValidationUtils.rejectIfEmpty(errors, "mobile", "NotEmpty.demoBiz.mobile");
        DemoBiz demoBiz = (DemoBiz)target;
       
        if(!StringUtils.isEmpty(demoBiz.getName())){
            DemoBizOption option = new DemoBizOption();
            option.setName(demoBiz.getName());
            if(bizManagent.getBizListByOption(option).getTotalCount() > 0){
                errors.rejectValue("name", "NameExists.demoBiz.name");
            }
        }
    }

}

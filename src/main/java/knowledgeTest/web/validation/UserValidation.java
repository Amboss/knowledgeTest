package knowledgeTest.web.validation;

import knowledgeTest.model.User;
import knowledgeTest.util.CustomUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validation of user
 */
@Component
public class UserValidation implements Validator {

    protected static Logger loger = Logger.getLogger(UserValidation.class);

    private final String RU_ENG_PATTERN = "([a-zA-Zа-яА-Я\\s.-]+)*";

    private User user;

    private CustomUtil util;

    @Autowired
    public void setUtil(CustomUtil util) {
        this.util = util;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the user instances
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        user = (User) target;

        /**
         *  check for empty employee_name
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
                "user_name.required", "Field name is required.");

        if (user.getUserName() != null) {
            /*
             *  check for userName length
             */
            if (user.getUserName().length() > 128) {
                errors.rejectValue("userName", "user_name.length");
            }

            /*
             *  check userName content
             */
            if (!util.getPatternMatch(user.getUserName(), RU_ENG_PATTERN)) {
                errors.rejectValue("userName", "user_name.content");
            }
        }
    }
}

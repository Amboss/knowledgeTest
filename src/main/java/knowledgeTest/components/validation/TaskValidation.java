package knowledgeTest.components.validation;

import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validation of Task
 */
@Component
public class TaskValidation implements Validator {

    protected static Logger loger = Logger.getLogger(TaskValidation.class);

    private Task task;

    @Override
    public boolean supports(Class clazz) {
        // validate the user instances
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        task = (Task) target;

        /**
         *  check for empty question
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question",
                "question.required", "Field question is required.");
        /**
         *  check for empty answer1
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer1",
                "answer.required", "Field answer1 is required.");
        /**
         *  check for empty answer2
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer2",
                "answer.required", "Field answer2 is required.");
        /**
         *  check for empty answer3
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer3",
                "answer.required", "Field answer3 is required.");
        /**
         *  check for empty answer4
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer4",
                "answer.required", "Field answer4 is required.");
        /**
         *  check for empty correct
         */
        ValidationUtils.rejectIfEmpty(errors, "correct",
                "correct.required", "Field correct is required.");

        /**
         *  check for length
         */
        if (task.getQuestion() != null) {
            if (task.getQuestion().length() > 255) {
                errors.rejectValue("question", "question.length", "question to long");
            }
        }

        if (task.getAnswer1() != null) {
            if (task.getAnswer1().length() > 255) {
                errors.rejectValue("answer", "answer.length", "answer to long");
            }
        }

        if (task.getAnswer2() != null) {
            if (task.getAnswer2().length() > 255) {
                errors.rejectValue("answer", "answer.length", "answer to long");
            }
        }
        if (task.getAnswer3() != null) {
            if (task.getAnswer3().length() > 255) {
                errors.rejectValue("answer", "answer.length", "answer to long");
            }
        }
        if (task.getAnswer4() != null) {
            if (task.getAnswer4().length() > 255) {
                errors.rejectValue("answer", "answer.length", "answer to long");
            }
        }
    }
}

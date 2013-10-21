package knowledgeTest.bean;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Class represents model for JSON object, made to hold current task for Ajax request
 */
@JsonAutoDetect
public class TaskModel {

    private Long userId;

    private Integer taskNum;

    private String question;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    public TaskModel() {}

    public TaskModel(Long userId, Integer taskNum, String question, String answer1,
                     String answer2, String answer3, String answer4) {
        this.userId = userId;
        this.taskNum = taskNum;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}
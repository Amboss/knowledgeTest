package knowledgeTest.bean;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Class represents model to process JSON Ajax request
 */
@JsonAutoDetect
public class RequestTask {

    private Long userId;

    private Integer taskNum;

    private Integer answerNum;

    public RequestTask() {}

    public RequestTask(Long userId, Integer taskNum, Integer answerNum) {
        this.userId = userId;
        this.taskNum = taskNum;
        this.answerNum = answerNum;
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

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }
}

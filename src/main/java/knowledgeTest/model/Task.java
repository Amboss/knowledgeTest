package knowledgeTest.model;

import javax.persistence.*;

/**
 * Class represents task model with question and few answers
 */
@Entity
@Table(name = "TASK", catalog = "KNOWLEDGE_TEST_DB")
public class Task {

    private long taskId;

    private String question;

    private String answer1;

    private String answer2;

    private String answer3;

    private Integer correct;

    /**
     * Task model constructor
     */
    public Task() {}

    public Task(long taskId, String question, String answer1,
                String answer2, String answer3, Integer correct) {
        this.taskId = taskId;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correct = correct;
    }
    /**
     * Task model getter/setter with Hibernate config annotations
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID", unique = true, nullable = false)
    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Column(name = "QUESTION", unique = false, nullable = false)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "ANSWER1", nullable = true)
    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    @Column(name = "ANSWER2", nullable = true)
    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    @Column(name = "ANSWER3", nullable = true)
    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    @Column(name = "CORRECT", nullable = false)
    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }
}
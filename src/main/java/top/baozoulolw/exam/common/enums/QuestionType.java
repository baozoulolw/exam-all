package top.baozoulolw.exam.common.enums;

public enum QuestionType {

    MULTIPLE_CHOICE("单选题",0),
    MULTIPLE_CHOICE_QUESTIONS("多选题",1),
    TRUE_OR_FALSE("判断题",1),
    FILL_IN_THE_BLANK("填空题",2);

    private String name;
    private int value;

    // 构造方法
    private QuestionType(String name, int value) {
        this.name = name;
        this.value = value;
    }
}

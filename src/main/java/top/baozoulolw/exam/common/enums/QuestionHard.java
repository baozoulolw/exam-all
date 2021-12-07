package top.baozoulolw.exam.common.enums;

public enum QuestionHard {

    EASY("简单",0),MEDIUM("中等",1),DIFFICULTY("困难",1);

    private String name;
    private int value;

    // 构造方法
    private QuestionHard(String name, int value) {
        this.name = name;
        this.value = value;
    }
}

package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.service.QuestionService;
import top.baozoulolw.exam.vo.QuestionParamVO;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping(value = "/add")
    public Result insertQuestion(@RequestBody Question question){
        return questionService.insertQuestion(question);
    }

    @PostMapping(value = "/edit")
    public Result editQuestion(@RequestBody Question question){
        return questionService.editQuestion(question);
    }

    @PostMapping(value = "/page")
    public Result<PageResult> pageQueryQuestion(@RequestBody PageSearch<QuestionParamVO> param){
        return questionService.pageQueryQuestion(param);
    }

    @GetMapping(value = "/getById/{id}")
    public Result<Question> getQuestionById(@PathVariable(value = "id")Long id){
        return questionService.getQuestionById(id);
    }

    @GetMapping(value = "/delById/{id}")
    public Result delQuestionById(@PathVariable(value = "id")Long id){
        return questionService.delQuestionById(id);
    }
}

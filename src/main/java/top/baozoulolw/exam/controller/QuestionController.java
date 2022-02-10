package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.service.QuestionService;
import top.baozoulolw.exam.vo.QuestionParamVO;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/question")
public class  QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 新增试题
     * @param question
     * @return
     */
    @PostMapping(value = "/add")
    public Result insertQuestion(@RequestBody Question question){
        return questionService.insertQuestion(question);
    }

    /**
     * 编辑试题
     * @param question
     * @return
     */
    @PostMapping(value = "/edit")
    public Result editQuestion(@RequestBody Question question){
        return questionService.editQuestion(question);
    }

    /**
     * 分页查询试题
     * @param param
     * @return
     */
    @PostMapping(value = "/page")
    public Result<PageResult> pageQueryQuestion(@RequestBody PageSearch<QuestionParamVO> param){
        return questionService.pageQueryQuestion(param);
    }

    /**
     * 根据id获取试题详细信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getById/{id}")
    public Result<Question> getQuestionById(@PathVariable(value = "id")Long id){
        return questionService.getQuestionById(id);
    }

    /**
     * 根据id删除试题
     * @param id
     * @return
     */
    @GetMapping(value = "/delById/{id}")
    public Result delQuestionById(@PathVariable(value = "id")Long id){
        return questionService.delQuestionById(id);
    }

    /**
     * 获取试题分类列表
     * @return
     */
    @GetMapping(value = "/group/list")
    public Result<List<QuestionGroup>> getGroupList(){
        return questionService.getGroupList();
    }

    /**
     * 编辑试题分类
     * @param questionGroup
     * @return
     */
    @PostMapping(value = "/group/edit")
    public Result editGroup(@RequestBody QuestionGroup questionGroup){
        return questionService.editGroup(questionGroup);
    }

    /**
     * 添加试题分类
     * @param groupName
     * @return
     */
    @GetMapping(value = "/group/add/{groupName}")
    public Result addGroup(@PathVariable("groupName")String groupName){
        return questionService.addGroup(groupName);
    }

    /**
     * 根据id删除试题分类
     * @param id
     * @return
     */
    @GetMapping(value = "/group/del/{id}")
    public Result delGroup(@PathVariable("id")Long id){
        return questionService.delGroup(id);
    }

    /**
     * 分类下的试题进行转移
     * @param from
     * @param to
     * @return
     */
    @GetMapping(value = "/group/trans")
    public Result transGroup(@RequestParam("from")Long from,@RequestParam("to")Long to){
        return questionService.transGroup(from,to);
    }
}

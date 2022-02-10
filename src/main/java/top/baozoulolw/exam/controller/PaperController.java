package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.entity.PaperQuestion;
import top.baozoulolw.exam.service.PaperService;
import top.baozoulolw.exam.vo.ChangeSortPaperParamVO;
import top.baozoulolw.exam.vo.PaperParamVO;
import top.baozoulolw.exam.vo.QuestionParamVO;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    /**
     * 分页获取试卷列表
     * @param param
     * @return
     */
    @PostMapping(value = "/page")
    public Result<PageResult> queryPaperPage(@RequestBody PageSearch<PaperParamVO> param){
        return paperService.queryPage(param);
    }

    /**
     * 新增试卷
     * @param paper
     * @return
     */
    @PostMapping(value = "/add")
    public Result addPaper(@RequestBody Paper paper){
        return paperService.addPaper(paper);
    }

    /**
     * 更新试卷信息
     * @param paper
     * @return
     */
    @PostMapping(value = "/update")
    public Result updatePaper(@RequestBody Paper paper){
        return paperService.updatePaper(paper);
    }

    /**
     * 根据id删除试卷
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Result delPaper(@PathVariable(value = "id") Long id){
        return paperService.delPaper(id);
    }

    /**
     * 获取试卷详细信息（包括包含的试题）
     * @param id
     * @return
     */
    @GetMapping(value = "/one/{id}")
    public Result<Paper> getPaperById(@PathVariable(value = "id")Long id){
        return paperService.getPaperById(id);
    }

    /**
     * 获取试卷还未选择的试题列表
     * @param param
     * @return
     */
    @PostMapping(value = "/select")
    public Result<PageResult> getSelectQuestion(@RequestBody PageSearch<QuestionParamVO> param){
        return paperService.getSelectQuestion(param);
    }

    /**
     * 往试卷添加试题
     * @param param
     * @return
     */
    @PostMapping(value = "/addQue")
    public Result addQuestion(@RequestBody List<PaperQuestion> param){
        return paperService.addQuestion(param);
    }

    /**
     * 试题排序
     * @param param
     * @return
     */
    @PostMapping(value = "/sort")
    public Result changeSort(@RequestBody ChangeSortPaperParamVO param){
        return paperService.changeSort(param);
    }

    /**
     * 修改试题所占分数
     * @param param
     * @return
     */
    @PostMapping(value = "/score")
    public Result changeScore(@RequestBody PaperQuestion param){
        return paperService.changeScore(param);
    }
}

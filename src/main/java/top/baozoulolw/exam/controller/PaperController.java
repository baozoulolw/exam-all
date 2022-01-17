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

    @PostMapping(value = "/page")
    public Result<PageResult> queryPaperPage(@RequestBody PageSearch<PaperParamVO> param){
        return paperService.queryPage(param);
    }

    @PostMapping(value = "/add")
    public Result addPaper(@RequestBody Paper paper){
        return paperService.addPaper(paper);
    }

    @PostMapping(value = "/update")
    public Result updatePaper(@RequestBody Paper paper){
        return paperService.updatePaper(paper);
    }

    @GetMapping(value = "/del/{id}")
    public Result delPaper(@PathVariable(value = "id") Long id){
        return paperService.delPaper(id);
    }

    @GetMapping(value = "/one/{id}")
    public Result<Paper> getPaperById(@PathVariable(value = "id")Long id){
        return paperService.getPaperById(id);
    }

    @PostMapping(value = "/select")
    public Result<PageResult> getSelectQuestion(@RequestBody PageSearch<QuestionParamVO> param){
        return paperService.getSelectQuestion(param);
    }

    @PostMapping(value = "/addQue")
    public Result addQuestion(@RequestBody List<PaperQuestion> param){
        return paperService.addQuestion(param);
    }

    @PostMapping(value = "/sort")
    public Result changeSort(@RequestBody ChangeSortPaperParamVO param){
        return paperService.changeSort(param);
    }

    @PostMapping(value = "/score")
    public Result changeScore(@RequestBody PaperQuestion param){
        return paperService.changeScore(param);
    }
}

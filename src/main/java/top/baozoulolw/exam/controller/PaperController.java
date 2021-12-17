package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.service.PaperService;
import top.baozoulolw.exam.vo.PaperParamVO;

import javax.annotation.Resource;

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
}

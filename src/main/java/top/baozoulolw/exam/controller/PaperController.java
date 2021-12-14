package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Result queryPaperPage(@RequestBody Paper paper){
        return paperService.addPaper(paper);
    }
}

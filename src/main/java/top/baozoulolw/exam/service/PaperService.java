package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.vo.PaperParamVO;

public interface PaperService {
    Result<PageResult> queryPage(PageSearch<PaperParamVO> param);

    Result<PageResult> addPaper(Paper paper);
}

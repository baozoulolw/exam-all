package top.baozoulolw.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.entity.PaperQuestion;
import top.baozoulolw.exam.vo.ChangeSortPaperParamVO;
import top.baozoulolw.exam.vo.PaperParamVO;
import top.baozoulolw.exam.vo.QuestionParamVO;

import java.util.List;

public interface PaperService extends IService<Paper> {
    Result<PageResult> queryPage(PageSearch<PaperParamVO> param);

    Result<PageResult> addPaper(Paper paper);

    Result<Paper> getPaperById(Long id);

    Result updatePaper(Paper paper);

    Result delPaper(Long id);

    Result<PageResult> getSelectQuestion(PageSearch<QuestionParamVO> param);

    Result addQuestion(List<PaperQuestion> param);

    Result changeSort(ChangeSortPaperParamVO param);

    Result changeScore(PaperQuestion param);
}

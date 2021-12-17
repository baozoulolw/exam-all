package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.PaperDao;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.service.PaperService;
import top.baozoulolw.exam.utils.UserUtils;
import top.baozoulolw.exam.vo.PaperParamVO;

import javax.annotation.Resource;

@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperDao paperDao;

    @Resource
    private UserDao userDao;

    @Override
    public Result<PageResult> queryPage(PageSearch<PaperParamVO> param) {
        Page<Paper> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<User> result = paperDao.getPaperList(page, param.getParam());
        return Result.success(new PageResult(result));
    }

    @Override
    public Result<PageResult> addPaper(Paper paper) {
        paper.setDrawer(UserUtils.getUserId());
        paperDao.insert(paper);
        return Result.success();
    }

    @Override
    public Result<Paper> getPaperById(Long id) {
        Paper paper = paperDao.selectById(id);
        User drawer = userDao.selectById(paper.getDrawer());
        paper.setDrawerUser(drawer.getRealName());
        paper.setTeacherGender(drawer.getGender());
        paper.setChangeUser(userDao.selectById(paper.getOperUser()).getRealName());
        return Result.success(paper);
    }

    @Override
    public Result updatePaper(Paper paper) {
        int i = paperDao.updateById(paper);
        if(i == 0){
            return Result.fail("更新失败");
        }
        return Result.success();
    }

    @Override
    public Result delPaper(Long id) {
        int i = paperDao.deleteById(id);
        if(i == 0){
            return Result.fail("删除失败");
        }
        return Result.success();
    }
}

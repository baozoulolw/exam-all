package top.baozoulolw.exam.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.vo.UserLIstParamVO;

public interface UserService extends UserDetailsService {
    Result<PageResult> queryUserList(PageSearch<UserLIstParamVO> param);
}

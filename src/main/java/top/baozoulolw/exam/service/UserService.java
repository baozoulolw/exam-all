package top.baozoulolw.exam.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.vo.UserLIstParamVO;

public interface UserService extends UserDetailsService {

    Result<PageResult> queryUserList(PageSearch<UserLIstParamVO> param);

    Result checkUsername(String username);

    Result addUser(User user);

    Result uploadAvatar(MultipartFile file);

    Result delFile(String fileName);
}

package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.entity.UserGroup;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 分页条件查询出userList
     * @param page 分页参数
     * @param upVo 查询条件参数
     * @return 查询结果
     */
    IPage<User> getUserList(Page<User> page, @Param("upVo") UserLIstParamVO upVo);


    List<UserGroup> getUserGroupList();
}

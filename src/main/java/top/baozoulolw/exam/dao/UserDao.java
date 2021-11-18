package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.User;

@Repository
public interface UserDao extends BaseMapper<User> {
}

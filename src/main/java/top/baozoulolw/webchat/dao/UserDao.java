package top.baozoulolw.webchat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.baozoulolw.webchat.entity.User;

@Repository
public interface UserDao extends BaseMapper<User> {
}

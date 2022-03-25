package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.dao.UserGroupDao;
import top.baozoulolw.exam.entity.UserGroup;
import top.baozoulolw.exam.service.UserGroupService;

@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupDao, UserGroup> implements UserGroupService {
}

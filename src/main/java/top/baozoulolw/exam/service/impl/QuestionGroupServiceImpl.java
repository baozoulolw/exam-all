package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.dao.QuestionGroupDao;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.service.QuestionGroupService;

@Service
public class QuestionGroupServiceImpl extends ServiceImpl<QuestionGroupDao, QuestionGroup> implements QuestionGroupService {
}

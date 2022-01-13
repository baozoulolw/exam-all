package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.dao.PaperQuestionDao;
import top.baozoulolw.exam.entity.PaperQuestion;
import top.baozoulolw.exam.service.PaperQuestionService;

@Service
public class PaperQuestionImpl extends ServiceImpl<PaperQuestionDao, PaperQuestion> implements PaperQuestionService {
}

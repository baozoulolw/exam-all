package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.dao.ExamPlanDao;
import top.baozoulolw.exam.entity.ExamPlan;
import top.baozoulolw.exam.service.ExamPlanService;

@Service
public class ExamPlanServiceImpl extends ServiceImpl<ExamPlanDao, ExamPlan> implements ExamPlanService {
}

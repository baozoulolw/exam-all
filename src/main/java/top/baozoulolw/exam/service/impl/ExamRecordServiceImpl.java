package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.dao.ExamRecordDao;
import top.baozoulolw.exam.entity.ExamRecord;
import top.baozoulolw.exam.service.ExamRecordService;

@Service
public class ExamRecordServiceImpl extends ServiceImpl<ExamRecordDao, ExamRecord> implements ExamRecordService {
}

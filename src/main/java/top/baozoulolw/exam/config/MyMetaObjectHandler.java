package top.baozoulolw.exam.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.baozoulolw.exam.utils.UserUtils;


import java.util.Date;

/**
 * 数据据库进行操作时自动增添数据
 *
 * @author Baozoulolw
 * @version 1.0
 * @date 2021-03-13 15:17
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";

    private static final String OPER_TIME = "changeTime";

    private static final String OPER_USER_ID = "operUser";

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean createTime = metaObject.hasSetter(CREATE_TIME);
        boolean hasSetter = metaObject.hasSetter(OPER_USER_ID);
        boolean hasUpdate = metaObject.hasSetter(OPER_TIME);
        // 判断是否有该字段
        if (createTime) {
            Object time = metaObject.getValue(CREATE_TIME);
            if (null != time) {
                setFieldValByName(CREATE_TIME, time, metaObject);
            } else {
                setFieldValByName(CREATE_TIME, new Date(), metaObject);
            }
        }
        if (hasSetter) {
            Object operId= metaObject.getValue(OPER_USER_ID);
            if (null != operId) {
                setFieldValByName(OPER_USER_ID, operId, metaObject);
            } else {
                setFieldValByName(OPER_USER_ID, UserUtils.getUserId(), metaObject);
            }
        }
        if (hasUpdate) {
            Object updateTime= metaObject.getValue(OPER_TIME);
            if (null != updateTime) {
                setFieldValByName(OPER_TIME, updateTime, metaObject);
            } else {
                setFieldValByName(OPER_TIME, new Date(), metaObject);
            }
        }
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("update执行了");
        System.out.println("GetUserUtils.getUserId()");
        boolean hasUpdate = metaObject.hasSetter(OPER_TIME);
        boolean hasSetter = metaObject.hasSetter(OPER_USER_ID);
        if (hasUpdate) {
            Object updateTime= metaObject.getValue(OPER_TIME);
            if (null != updateTime) {
                setFieldValByName(OPER_TIME, updateTime, metaObject);
            } else {
                setFieldValByName(OPER_TIME, new Date(), metaObject);
            }
        }
        if (hasSetter) {
            Object operId= metaObject.getValue(OPER_USER_ID);
            if (null != operId) {
                setFieldValByName(OPER_USER_ID, operId, metaObject);
            } else {
                setFieldValByName(OPER_USER_ID, UserUtils.getUserId(), metaObject);
            }
        }
    }
}

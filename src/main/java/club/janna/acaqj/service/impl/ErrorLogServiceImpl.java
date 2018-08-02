package club.janna.acaqj.service.impl;

import club.janna.acaqj.mapper.ErrorLogMapper;
import club.janna.acaqj.pojo.ErrorLog;
import club.janna.acaqj.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 20:36
 * @Description:
 */
@Service("errorLogService")
public class ErrorLogServiceImpl implements ErrorLogService {

    @Autowired
    private ErrorLogMapper errorLogMapper;

    public int insert(ErrorLog entity) {
        return errorLogMapper.insert(entity);
    }
}

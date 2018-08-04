package club.janna.acaqj.service.impl;

import club.janna.acaqj.mapper.ErrorLogMapper;
import club.janna.acaqj.pojo.ErrorLog;
import club.janna.acaqj.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<ErrorLog> getByTotal(Integer total) {
        return errorLogMapper.getByTotal(total);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        if(ids == null || ids.size() == 0)
            return;
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        errorLogMapper.deleteByIds(params);
    }
}

package club.janna.acaqj.service;

import club.janna.acaqj.pojo.ErrorLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 20:35
 * @Description:
 */
public interface ErrorLogService {
    int insert(ErrorLog entity);

    List<ErrorLog> getByTotal(@Param("total") Integer total);

    void deleteByIds(List<Integer> ids);
}

package club.janna.acaqj.mapper;

import club.janna.acaqj.pojo.ErrorLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrorLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrorLog record);

    int insertSelective(ErrorLog record);

    ErrorLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrorLog record);

    int updateByPrimaryKey(ErrorLog record);

    List<ErrorLog> getByTotal(@Param("total") Integer total);

    void deleteByIds(Map<String, Object> params);
}
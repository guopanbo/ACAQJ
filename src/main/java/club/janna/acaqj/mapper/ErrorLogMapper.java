package club.janna.acaqj.mapper;

import club.janna.acaqj.pojo.ErrorLog;

public interface ErrorLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrorLog record);

    int insertSelective(ErrorLog record);

    ErrorLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrorLog record);

    int updateByPrimaryKey(ErrorLog record);
}
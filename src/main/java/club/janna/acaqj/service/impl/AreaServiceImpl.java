package club.janna.acaqj.service.impl;

import club.janna.acaqj.mapper.AreaMapper;
import club.janna.acaqj.pojo.Area;
import club.janna.acaqj.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 20:36
 * @Description:
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    public int insert(Area entity) {
        return areaMapper.insert(entity);
    }
}

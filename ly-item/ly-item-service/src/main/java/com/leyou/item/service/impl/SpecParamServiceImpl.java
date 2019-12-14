package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.service.SpecParamService;
import com.leyou.service.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecParamServiceImpl implements SpecParamService {

    @Autowired
    SpecParamMapper specParamMapper;

    @Override
    public List<SpecParam> querySpecParamByGid(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> list = specParamMapper.select(specParam);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return list;
    }

    @Transactional
    @Override
    public void saveSpecParam(SpecParam specParam) {
        specParamMapper.insertSelective(specParam);
    }

    @Transactional
    @Override
    public void updateSpecParam(SpecParam specParam) {
        specParamMapper.updateByPrimaryKeySelective(specParam);
    }

    @Transactional
    @Override
    public void deleteSpecParam(Long paramId) {
        specParamMapper.deleteByPrimaryKey(paramId);

    }
}

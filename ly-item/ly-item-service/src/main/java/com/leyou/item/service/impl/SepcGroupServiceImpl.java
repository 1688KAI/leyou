package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.service.pojo.SpecGroup;
import com.leyou.item.service.SepcGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SepcGroupServiceImpl implements SepcGroupService {

    @Autowired
    SpecGroupMapper specGroupMapper;

    @Override
    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(specGroup);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return list;
    }

    @Transactional
    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        specGroupMapper.insertSelective(specGroup);

    }

    @Transactional
    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        specGroupMapper.updateByPrimaryKeySelective(specGroup);
    }

    @Transactional
    @Override
    public void deleteSpecGroup(Long id) {
        specGroupMapper.deleteByPrimaryKey(id);
    }
}

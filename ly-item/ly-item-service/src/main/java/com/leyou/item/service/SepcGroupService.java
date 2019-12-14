package com.leyou.item.service;

import com.leyou.service.pojo.SpecGroup;

import java.util.List;

public interface SepcGroupService {
    List<SpecGroup> querySpecGroupByCid(Long cid);

    void saveSpecGroup(SpecGroup specGroup);

    void updateSpecGroup(SpecGroup specGroup);

    void deleteSpecGroup(Long id);
}

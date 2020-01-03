package com.leyou.item.service;

import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecParamService {
    List<SpecParam> querySpecParamByGid(Long gid);

    void saveSpecParam(SpecParam specParam);

    void updateSpecParam(SpecParam specParam);

    void deleteSpecParam(Long paramId);


    List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching);
}

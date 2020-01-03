package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SpecParamApi {
    /**
     * @param gid       组ID
     * @param cid       分类ID
     * @param searching 是否搜索
     * @param generic
     * @return
     */
    @GetMapping("spec/params")
    List<SpecParam> queryParamList(@RequestParam(value = "gid", required = false) Long gid,
                                   @RequestParam(value = "cid", required = false) Long cid,
                                   @RequestParam(value = "searching", required = false) Boolean searching);


    @GetMapping("spec/group")
    List<SpecGroup> queryGroupByCid(@RequestParam("cid") Long cid);


}

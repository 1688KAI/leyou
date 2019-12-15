package com.leyou.item.web;

import com.leyou.item.service.SpecParamService;
import com.leyou.service.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecParamController {


    @Autowired
    SpecParamService specParamService;

    /**
     *
     * @param gid  规格组di
     * @param cid   分类id
     * @param searching 是否搜索
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParamList(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching){
        return ResponseEntity.ok(specParamService.querySpecParamList(gid));
    }


    /**
     * 新增规格模板中某一组的规格参数
     *
     * @param specParam
     * @return
     */
    //http://api.leyou.com/api/item/spec/param
    @PostMapping("param")
    public ResponseEntity<Void> saveSpecParam(@RequestBody SpecParam specParam) {
        specParamService.saveSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 修改规格参数中某一分组的某个规格参数
     *
     * @param specParam
     * @return
     */
    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam specParam) {
        specParamService.updateSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 删除规格参数中某一分组中的某个模板参数
     *
     * @param paramId
     * @return
     */
    @DeleteMapping("param/{paramId}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable("paramId") Long paramId) {
        specParamService.deleteSpecParam(paramId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

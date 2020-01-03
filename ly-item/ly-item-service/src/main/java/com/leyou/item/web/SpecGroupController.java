package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.service.SepcGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecGroupController {


    @Autowired
    SepcGroupService sepcGroupService;


    /**
     * 查询某一分类对应的规格模板
     *
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupByCid(@PathVariable Long cid){
        return ResponseEntity.ok(sepcGroupService.querySpecGroupByCid(cid));
    }

    /**
     * 新增规格模板的分组
     *
     * @param specGroup
     * @return
     */
    @PostMapping("group")
    public ResponseEntity<Void> saveSpecGroup(@RequestBody SpecGroup specGroup) {
        sepcGroupService.saveSpecGroup(specGroup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 修改规格模板中某一分组信息
     *
     * @param specGroup
     * @return
     */
    @PutMapping("group")
    public ResponseEntity<Void> updateSpecGroup(SpecGroup specGroup) {
        sepcGroupService.updateSpecGroup(specGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 删除规格模板中的某一分组
     *
     * @param id
     * @return
     */
    //http://api.leyou.com/api/item/spec/group/15
    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteSpecGroup(@PathVariable("id") Long id) {
        sepcGroupService.deleteSpecGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/group")
   ResponseEntity< List<SpecGroup> > queryListByCid(@RequestParam("cid") Long cid){
        return ResponseEntity.ok(this.sepcGroupService.queryListByCid(cid));
    }

}

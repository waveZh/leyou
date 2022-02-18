package com.leyou.item.controller;

import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.service.SpecificationService;
import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/spec")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据cid 查询组
     * @param cid
     * @return
     */
    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List groups = this.specificationService.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> querySpecParam(@RequestParam("gid")Long gid ){
        List params = this.specificationService.querySpecParam(gid);
        if(CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
}


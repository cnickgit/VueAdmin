package com.markerhub.controller;

import com.markerhub.common.lang.Result;
import com.markerhub.service.DsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zyj/ds")
public class DsController {

    @Autowired
    private DsService dsService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('zyj:ds:list')")
    public Result getDsData(@RequestParam("searchName") String searchName, @RequestParam("code") String code){
        return  dsService.getDsData(searchName,code);
    }
}

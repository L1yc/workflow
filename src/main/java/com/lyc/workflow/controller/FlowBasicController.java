package com.lyc.workflow.controller;

import com.lyc.workflow.advice.ResAdvice;
import com.lyc.workflow.service.FlowBasicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author liyc
 */
@RestController
@RequestMapping("/api/flow")
@ResAdvice
public class FlowBasicController {

    @Autowired
    FlowBasicServices flowBasicServices;

}

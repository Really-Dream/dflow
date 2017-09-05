package com.dream.dflow.controller;

import com.dream.dflow.entity.Node;
import com.dream.dflow.mapper.NodeInfoMapper;
import com.dream.dflow.mapper.NodeMapper;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Dream on 2017/9/4.
 */
@RequestMapping(value = "bpm")
@Controller
public class BpmStartController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    NodeMapper nodeMapper;

    /**
     * 启动流程
     * @param id 流程定义ID  first:1:60004
     * @return
     */
    @RequestMapping(value = "startProcess")
    public String startProcess(String id, Model model){
        List<Node> nodeList = nodeMapper.getStartUser(id);
        model.addAttribute("nodeList",nodeList);
        return nodeMapper.getStartFormKey(id);
    }

    @RequestMapping(value = "start")
    public String start(String id){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult();
        runtimeService.startProcessInstanceById(processDefinition.getId());
        return null;
    }


}

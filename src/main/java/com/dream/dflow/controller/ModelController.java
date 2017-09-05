package com.dream.dflow.controller;

import com.dream.dflow.mapper.NodeInfoMapper;
import com.dream.dflow.mapper.NodeMapper;
import com.dream.dflow.service.ModelService;
import com.dream.dflow.util.BpmUtils;
import com.dream.dflow.util.FindFiles;
import com.google.gson.Gson;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件与部署流程管理
 */
@Controller
public class ModelController {

    @Value("${d-Address.processLocation}")
    private String processLoation;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    private Gson gson;

    @Autowired
    ModelService modelService;

    @Autowired
    BpmUtils bpmUtils;

    @Autowired
    NodeInfoMapper nodeInfoMapper;

    @Autowired
    NodeMapper nodeMapper;

    /**
     * 首页
     */
    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }

    /**
     * 查询所有processes文件夹下的文件
     */
    @RequestMapping(value = "processes")
    @ResponseBody
    public List<String> findModelList(){
        List<String> list = FindFiles.list(processLoation);
        return list;
    }

    /**
     * 部署流程
     */
    @RequestMapping(value = "deploy")
    @ResponseBody
    public String deploy(String fileName){
        try {
           modelService.deploy(processLoation+"/"+fileName);
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson("FALSE");
        }
        return gson.toJson("SUCCESS");
    }

    /**
     * 已部署流程列表
     */
    @RequestMapping(value = "deployed")
    @ResponseBody
    public List<String[]> deployed(){
        List<String[]> listS = new ArrayList<>();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().list();
        for(ProcessDefinition processDefinition : list){
            String[] s = {processDefinition.getId(),processDefinition.getName(),processDefinition.getDeploymentId()};
            listS.add(s);
        }
        return listS;
    }

    /**
     * 删除已部署流程
     */
    @RequestMapping(value = "deleteDeployed")
    @ResponseBody
    public String deleteDeployed(String id){
        try{
            repositoryService.deleteDeployment(id);
            nodeInfoMapper.delete(id);
            nodeMapper.delete(id);
        }catch (Exception e){
            return gson.toJson("FALSE");
        }
        return gson.toJson("SUCCESS");
    }

    /**
     * 启动流程
     * @param id 流程定义ID  first:1:60004
     * @return
     */
    @RequestMapping(value = "startProcess")
    public String startProcess(String id){
        return nodeInfoMapper.getStartFormKey(id);
    }

    /**
     * 已启动流程
     */
    @RequestMapping(value = "started")
    @ResponseBody
    public List<String[]> start(){
        List<String[]> listS = new ArrayList<>();
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for(ProcessInstance processInstance : list){
            String[] s = {processInstance.getId(),processInstance.getProcessDefinitionId(),processInstance.getProcessDefinitionName()};
            listS.add(s);
        }
        return listS;
    }

    /**
     * 删除流程实例
     * @param id 流程实例ID
     * @param reason 删除原因
     */
    @RequestMapping(value = "deleteInstance")
    @ResponseBody
    public String deleteInstance(String id,String reason){
        try{
            runtimeService.deleteProcessInstance(id,reason);
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson("FALSE");
        }
        return gson.toJson("SUCCESS");
    }

    /**
     * 任务状态
     */
    @RequestMapping(value = "task")
    @ResponseBody
    public List<String[]> task(){
        List<String[]> listS = new ArrayList<>();
        List<Task> list = taskService.createTaskQuery().list();
        for(Task task : list){
            String[] s = {task.getId(),task.getName(),task.getAssignee(),task.getProcessDefinitionId()};
            listS.add(s);
        }
        return listS;
    }

    @RequestMapping(value = "doTask")
    public String doTask(String id){
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        bpmUtils.getNextStepInfo(task);
        return "";
    }
}

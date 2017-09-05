package com.dream.dflow.service;

import com.dream.dflow.bpm.ProcessInfoCmd;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ModelService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ProcessEngine processEngine;

    public String deploy(String fileName) throws Exception{
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(fileName).deploy();
        List<ProcessDefinition> processDefinitions = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).list();

        for (ProcessDefinition pdf : processDefinitions) {
            if (pdf.getName() == null || "".equals(pdf.getName())) {
                throw new NullPointerException();
            }
            processEngine.getManagementService().executeCommand(new ProcessInfoCmd(pdf.getId()));
        }
        return null;
    }

}

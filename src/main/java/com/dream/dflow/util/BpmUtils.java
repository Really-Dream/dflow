package com.dream.dflow.util;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activiti基础功能
 */
@Service
public class BpmUtils {

    @Autowired
    RepositoryService repositoryService;

    public String getNextStepInfo(Task task){
        ProcessDefinitionEntity processDefinitionEntity =
                (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());

        List<ActivityImpl> list = processDefinitionEntity.getActivities();
        for(ActivityImpl activity : list){
            if(activity.getId().equals(task.getTaskDefinitionKey())){
                List<PvmTransition> pvmTransitionList = activity.getOutgoingTransitions();
                for(PvmTransition pvmTransition : pvmTransitionList){
                    pvmTransition.getDestination();
                }
            }
        }
        return "";
    }

    public Map<String,String> getUser(String processDefinitionId){
        Map<String,String> map = new HashMap<>();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for(FlowElement flowElement : flowElements){
            if(flowElement instanceof UserTask){
                map.put(flowElement.getId(),((UserTask) flowElement).getAssignee());
            }
        }
        return map;
    }


}

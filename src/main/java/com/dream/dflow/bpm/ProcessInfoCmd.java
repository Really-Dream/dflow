package com.dream.dflow.bpm;

import com.dream.dflow.entity.Node;
import com.dream.dflow.entity.NodeInfo;
import com.dream.dflow.mapper.NodeInfoMapper;
import com.dream.dflow.mapper.NodeMapper;
import com.dream.dflow.util.SpringContextHolder;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 流程详细信息
 */
public class ProcessInfoCmd implements Command<Void> {

    private String processDefinitionId;

    private ProcessDefinitionEntity processDefinitionEntity;

    private Map<String,String> userMap;

    private Map<String,String> formKeyMap;

    public ProcessInfoCmd(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public Void execute(CommandContext commandContext) {
        this.processDefinitionEntity = new GetDeploymentProcessDefinitionCmd(processDefinitionId).execute(commandContext);

        BpmnModel bpmnModel = new GetBpmnModelCmd(processDefinitionId).execute(commandContext);
        this.setUser(bpmnModel);

        List<ActivityImpl> activityList = processDefinitionEntity.getActivities();

        int index = 1;
        List<NodeInfo> infoList = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        this.setNodeInfoList(activityList,index,infoList,nodeList,"no");
        this.setOverAll(infoList,nodeList);

        getNodeInfoMapper().insertbatch(infoList);
        getNodeMapper().insertbatch(nodeList);
        return null;
    }

    public void setNodeInfoList(List<ActivityImpl> activityList, int index, List<NodeInfo> infoList,List<Node> nodeList,String isSub) {
        for (ActivityImpl activity : activityList) {

            Node node = new Node.Builder()
                    .Id(index)
                    .ProcessDefinitionId(processDefinitionEntity.getDeploymentId())
                    .NodeId(activity.getId())
                    .NodeType((String) activity.getProperties().get("type"))
                    .NodeName((String) activity.getProperties().get("name"))
                    .NextUser(userMap.get(activity.getId()))
                    .FormKey(formKeyMap.get(activity.getId()))
                    .build();
            nodeList.add(node);

            NodeInfo nodeInfo;
            int size = activity.getOutgoingTransitions().size();
            if (size == 0) {
                nodeInfo = new NodeInfo.Builder()
                        .Id(index)
                        .ProcessDefinitionId(processDefinitionEntity.getDeploymentId())
                        .NodeId(activity.getId())
                        .NodeType((String) activity.getProperties().get("type"))
                        .NodeName((String) activity.getProperties().get("name"))
                        .OutGoingId("")
                        .OutGoingName("")
                        .OutGoingCondition("")
                        .OutGoingNodeId("")
                        .OutGoingNodeType("")
                        .OutGoingNodeName("")
                        .OutGoingNodeUser("")
                        .IsSub(isSub)
                        .FormKey("")
                        .bulid();
                index++;
                infoList.add(nodeInfo);
            } else {
                for (int i = 0; i < size; i++) {
                    nodeInfo = new NodeInfo.Builder()
                            .Id(index)
                            .ProcessDefinitionId(processDefinitionEntity.getDeploymentId())
                            .NodeId(activity.getId())
                            .NodeType((String) activity.getProperties().get("type"))
                            .NodeName((String) activity.getProperties().get("name"))
                            .OutGoingId(activity.getOutgoingTransitions().get(i).getId())
                            .OutGoingName((String) activity.getOutgoingTransitions().get(i).getProperty("name"))
                            .OutGoingCondition((String) activity.getOutgoingTransitions().get(i).getProperty("conditionText"))
                            .OutGoingNodeId(activity.getOutgoingTransitions().get(i).getDestination().getId())
                            .OutGoingNodeType((String) activity.getOutgoingTransitions().get(i).getDestination().getProperty("type"))
                            .OutGoingNodeName((String) activity.getOutgoingTransitions().get(i).getDestination().getProperty("name"))
                            .OutGoingNodeUser(userMap.get(activity.getOutgoingTransitions().get(i).getDestination().getId()))
                            .IsSub(isSub)
                            .FormKey(formKeyMap.get(activity.getId()))
                            .bulid();
                    index++;
                    infoList.add(nodeInfo);
                }
            }

            if ("subProcess".equals(activity.getProperties().get("type"))) {
                setNodeInfoList(activity.getActivities(),index,infoList,nodeList,"yes");
            }
        }
    }

    private void setUser(BpmnModel bpmnModel){
        Map<String,String> map = new HashMap<>();
        Map<String,String> formKeyMap = new HashMap<>();
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for(FlowElement flowElement : flowElements){
            if(flowElement instanceof UserTask){
                map.put(flowElement.getId(),((UserTask) flowElement).getAssignee());
                formKeyMap.put(flowElement.getId(),((UserTask) flowElement).getFormKey());
            }else if(flowElement instanceof StartEvent){
                formKeyMap.put(flowElement.getId(),((StartEvent) flowElement).getFormKey());
            }
        }
        this.userMap = map;
        this.formKeyMap = formKeyMap;
    }

    private void setOverAll(List<NodeInfo> infoList,List<Node> nodeList){
        NodeInfo nodeInfo = new NodeInfo.Builder()
                .Id(0)
                .ProcessDefinitionId(processDefinitionEntity.getDeploymentId())
                .NodeId("all_")
                .NodeType("process")
                .NodeName("全局")
                .OutGoingId("")
                .OutGoingName("")
                .OutGoingCondition("")
                .OutGoingNodeId("")
                .OutGoingNodeType("")
                .OutGoingNodeName("")
                .OutGoingNodeUser("")
                .IsSub("no")
                .FormKey(processDefinitionEntity.getDescription())
                .bulid();
        infoList.add(nodeInfo);

        Node node = new Node.Builder()
                .Id(0)
                .ProcessDefinitionId(processDefinitionEntity.getDeploymentId())
                .NodeId("all_")
                .NodeType("process")
                .NodeName("全局")
                .NextUser("")
                .FormKey(processDefinitionEntity.getDescription())
                .build();
        nodeList.add(node);
    }

    private NodeInfoMapper getNodeInfoMapper() {
        return SpringContextHolder.getBean(NodeInfoMapper.class);
    }
    private NodeMapper getNodeMapper() {
        return SpringContextHolder.getBean(NodeMapper.class);
    }
}

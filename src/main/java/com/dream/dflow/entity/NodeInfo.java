package com.dream.dflow.entity;

/**
 * 节点信息
 */
public class NodeInfo {
    private int id;
    private String processDefinitionId;//流程定义ID
    private String nodeId;//节点ID
    private String nodeType;//节点类型
    private String nodeName;//节点名称
    private String outGoingId;//出线ID
    private String outGoingName;//出线Name
    private String outGoingCondition;//出线条件
    private String outGoingNodeId;//出线节点ID
    private String outGoingNodeType;//出线节点类型
    private String outGoingNodeName;//出线节点名称
    private String outGoingNodeUser;//出线节点处理人
    private String isSub;//是否是子任务
    private String formKey;//表单

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getOutGoingId() {
        return outGoingId;
    }

    public void setOutGoingId(String outGoingId) {
        this.outGoingId = outGoingId;
    }

    public String getOutGoingName() {
        return outGoingName;
    }

    public void setOutGoingName(String outGoingName) {
        this.outGoingName = outGoingName;
    }

    public String getOutGoingNodeId() {
        return outGoingNodeId;
    }

    public void setOutGoingNodeId(String outGoingNodeId) {
        this.outGoingNodeId = outGoingNodeId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getOutGoingNodeType() {
        return outGoingNodeType;
    }

    public void setOutGoingNodeType(String outGoingNodeType) {
        this.outGoingNodeType = outGoingNodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getOutGoingNodeName() {
        return outGoingNodeName;
    }

    public void setOutGoingNodeName(String outGoingNodeName) {
        this.outGoingNodeName = outGoingNodeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsSub() {
        return isSub;
    }

    public void setIsSub(String isSub) {
        this.isSub = isSub;
    }

    public String getOutGoingCondition() {
        return outGoingCondition;
    }

    public void setOutGoingCondition(String outGoingCondition) {
        this.outGoingCondition = outGoingCondition;
    }

    public String getOutGoingNodeUser() {
        return outGoingNodeUser;
    }

    public void setOutGoingNodeUser(String outGoingNodeUser) {
        this.outGoingNodeUser = outGoingNodeUser;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public static class Builder{
        private int id;
        private String processDefinitionId;//流程定义ID
        private String nodeId;//节点ID
        private String nodeType;//节点类型
        private String nodeName;//节点名称
        private String outGoingId;//出线ID
        private String outGoingName;//出线Name
        private String outGoingCondition;//出线条件
        private String outGoingNodeId;//出线节点ID
        private String outGoingNodeType;//出线节点类型
        private String outGoingNodeName;//出线节点名称
        private String outGoingNodeUser;//出线节点处理人
        private String isSub;
        private String formKey;

        public Builder Id(int id){
            this.id = id;
            return this;
        }

        public Builder ProcessDefinitionId(String processDefinitionId) {
            this.processDefinitionId = processDefinitionId;
            return this;
        }

        public Builder NodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public Builder NodeType(String nodeType) {
            this.nodeType = nodeType;
            return this;
        }

        public Builder NodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public Builder OutGoingId(String outGoingId) {
            this.outGoingId = outGoingId;
            return this;
        }

        public Builder OutGoingName(String outGoingName) {
            this.outGoingName = outGoingName;
            return this;
        }

        public Builder OutGoingCondition(String outGoingCondition) {
            this.outGoingCondition = outGoingCondition;
            return this;
        }

        public Builder OutGoingNodeId(String outGoingNodeId) {
            this.outGoingNodeId = outGoingNodeId;
            return this;
        }

        public Builder OutGoingNodeType(String outGoingNodeType) {
            this.outGoingNodeType = outGoingNodeType;
            return this;
        }

        public Builder OutGoingNodeName(String outGoingNodeName) {
            this.outGoingNodeName = outGoingNodeName;
            return this;
        }
        public Builder OutGoingNodeUser(String outGoingNodeUser) {
            this.outGoingNodeUser = outGoingNodeUser;
            return this;
        }

        public Builder IsSub(String isSub) {
            this.isSub = isSub;
            return this;
        }

        public Builder FormKey(String formKey) {
            this.formKey = formKey;
            return this;
        }

        public NodeInfo bulid(){
            return new NodeInfo(this);
        }
    }

    public NodeInfo(Builder builder){
        id = builder.id;
        processDefinitionId = builder.processDefinitionId;
        nodeId = builder.nodeId;
        nodeType = builder.nodeType;
        nodeName = builder.nodeName;
        outGoingId = builder.outGoingId;
        outGoingName = builder.outGoingName;
        outGoingCondition = builder.outGoingCondition;
        outGoingNodeId = builder.outGoingNodeId;
        outGoingNodeType = builder.outGoingNodeType;
        outGoingNodeName = builder.outGoingNodeName;
        outGoingNodeUser = builder.outGoingNodeUser;
        isSub = builder.isSub;
        formKey = builder.formKey;
    }

}

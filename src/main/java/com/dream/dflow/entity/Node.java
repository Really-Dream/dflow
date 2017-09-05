package com.dream.dflow.entity;

/**
 * Created by Dream on 2017/9/5.
 */
public class Node {
    private int id;
    private String processDefinitionId;
    private String nodeId;
    private String nodeType;
    private String nodeName;
    private String nextUser;
    private String userExpression;
    private String formKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNextUser() {
        return nextUser;
    }

    public void setNextUser(String nextUser) {
        this.nextUser = nextUser;
    }

    public String getUserExpression() {
        return userExpression;
    }

    public void setUserExpression(String userExpression) {
        this.userExpression = userExpression;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public static class Builder{
        private int id;
        private String processDefinitionId;
        private String nodeId;
        private String nodeType;
        private String nodeName;
        private String nextUser;
        private String userExpression;
        private String formKey;

        public Builder Id(int id){
            this.id = id;
            return this;
        }

        public Builder ProcessDefinitionId(String processDefinitionId){
            this.processDefinitionId = processDefinitionId;
            return this;
        }public Builder NodeId(String nodeId){
            this.nodeId = nodeId;
            return this;
        }public Builder NodeType(String nodeType){
            this.nodeType = nodeType;
            return this;
        }public Builder NodeName(String nodeName){
            this.nodeName = nodeName;
            return this;
        }public Builder NextUser(String nextUser){
            this.nextUser = nextUser;
            return this;
        }public Builder UserExpression(String userExpression){
            this.userExpression = userExpression;
            return this;
        }public Builder FormKey(String formKey){
            this.formKey = formKey;
            return this;
        }

        public Node build(){
            return new Node(this);
        }
    }

    public Node(Builder builder){
        id = builder.id;
        processDefinitionId = builder.processDefinitionId;
        nodeId = builder.nodeId;
        nodeType = builder.nodeType;
        nodeName = builder.nodeName;
        nextUser = builder.nextUser;
        userExpression = builder.userExpression;
        formKey = builder.formKey;
    }

    public Node(){
        super();
    }
}

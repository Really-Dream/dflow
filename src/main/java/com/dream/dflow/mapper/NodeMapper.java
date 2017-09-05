package com.dream.dflow.mapper;

import com.dream.dflow.entity.Node;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Dream on 2017/9/5.
 */
@Mapper
public interface NodeMapper {
    int insertbatch(List<Node> list);
    int delete(String processDefinitionId);
    String getStartFormKey(String processDefinitionId);
    String getAllFormKey(String processDefinitionId);
    List<Node> getStartUser(String processDefinitionId);
    List<Node> getNextUser(String processDefinitionId,String nodeId);
}

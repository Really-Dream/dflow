package com.dream.dflow.mapper;

import com.dream.dflow.entity.NodeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Dream on 2017/9/4.
 */
@Mapper
public interface NodeInfoMapper {

    int insertbatch(List<NodeInfo> list);
    int delete(String processDefinitionId);
    String getStartFormKey(String processDefinitionId);
    String getAllFormKey(String processDefinitionId);
    List<String> getStartUser(String processDefinitionId);
    List<String> getNextUser(String processDefinitionId,String nodeId);
}

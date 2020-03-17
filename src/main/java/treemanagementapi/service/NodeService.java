package treemanagementapi.service;

import java.util.List;

import treemanagementapi.model.rest.Node;

public interface NodeService {

    public List<Node> getTree();
    
    public void saveTree(List<Node> nodeTreeList);

}
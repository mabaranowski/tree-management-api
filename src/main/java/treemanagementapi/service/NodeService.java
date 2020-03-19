package treemanagementapi.service;

import java.util.List;

import treemanagementapi.model.db.DbNode;
import treemanagementapi.model.rest.Node;

public interface NodeService {

    public List<Node> getTree();
    
    public List<DbNode> saveTree(List<Node> nodeTreeList);

}
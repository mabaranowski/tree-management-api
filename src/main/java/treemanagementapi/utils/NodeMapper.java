package treemanagementapi.utils;

import org.springframework.stereotype.Component;

import treemanagementapi.model.db.DbNode;
import treemanagementapi.model.rest.Node;

@Component
public class NodeMapper {

    public Node mapDbNodeToNode(DbNode input) {
        Node result = new Node();
        result.setKey(input.getKey());
        result.setValue(input.getValue());
        result.setParent(input.getParent());
        return result;
    }

    public DbNode mapNodeToDbNode(Node input) {
        DbNode result = new DbNode();
        result.setKey(input.getKey());
        result.setValue(input.getValue());
        result.setParent(input.getParent());
        return result;
    }
    
}

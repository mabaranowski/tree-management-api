package treemanagementapi.model.rest;

import java.util.List;

import lombok.Data;

@Data
public class Node {

    private String key;
    private Long value;
    private List<Node> children;
    private Node parentNode;
    private Boolean expanded;

}

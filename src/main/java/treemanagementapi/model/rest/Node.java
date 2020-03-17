package treemanagementapi.model.rest;

import lombok.Data;

@Data
public class Node {

    private String key;
    private Long value;
    private String parent;

}

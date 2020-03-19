package treemanagementapi.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import treemanagementapi.model.rest.Node;

@RestController
public interface TreeApi {

    @CrossOrigin
    public List<Node> getTreeStructure();

    @CrossOrigin
    public void saveTreeStructure(@RequestBody List<Node> nodeToSave);

}
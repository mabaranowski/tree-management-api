package treemanagementapi.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import treemanagementapi.model.rest.Node;

@RestController
public interface TreeApi {

    @CrossOrigin
    @GetMapping("retrieve")
    public Node getTreeStructure();

    @CrossOrigin
    @PostMapping("/save")
    public void saveTreeStructure(@RequestBody Node nodeToSave);

}
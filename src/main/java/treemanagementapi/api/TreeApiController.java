package treemanagementapi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import treemanagementapi.model.rest.Node;
import treemanagementapi.service.NodeService;

@Log4j2
@RestController
public class TreeApiController implements TreeApi {

    private final NodeService nodeService;

    @Autowired
    public TreeApiController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @Override
    public List<Node> getTreeStructure() {
        log.info("GET_getTreeStructure");
        return this.nodeService.getTree();
    }

    @Override
    public void saveTreeStructure(List<Node> nodeToSave) {
      log.info("POST_saveTreeStructure");
      this.nodeService.saveTree(nodeToSave);
    }

}
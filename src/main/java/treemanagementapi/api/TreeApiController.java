package treemanagementapi.api;

import lombok.extern.log4j.Log4j2;
import treemanagementapi.model.rest.Node;

@Log4j2
public class TreeApiController implements TreeApi {

    @Override
    public Node getTreeStructure() {

        return null;
    }

    @Override
    public void saveTreeStructure(Node nodeToSave) {
      log.info("x");

    }

}
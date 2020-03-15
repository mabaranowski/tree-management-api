package treemanagementapi.service;

import treemanagementapi.model.db.DbNode;
import treemanagementapi.model.rest.Node;
import treemanagementapi.repo.NodeRepository;
import treemanagementapi.utils.NodeMapper;

public class NodeServiceImpl implements NodeService {

    private final NodeMapper nodeMapper;
    private final NodeRepository nodeRepo;

    public NodeServiceImpl(NodeMapper nodeMapper, NodeRepository nodeRepo) {
        this.nodeMapper = nodeMapper;
        this.nodeRepo = nodeRepo;
    }

    @Override
    public void saveTree(Node nodeTree) {
        DbNode nodeToSave = this.nodeMapper.mapNodeToDbNode(nodeTree);
        this.nodeRepo.save(nodeToSave);
    }

}
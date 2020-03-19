package treemanagementapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import treemanagementapi.model.db.DbNode;
import treemanagementapi.model.rest.Node;
import treemanagementapi.repo.NodeRepository;
import treemanagementapi.utils.NodeMapper;

@Service
public class NodeServiceImpl implements NodeService {

    private final NodeMapper nodeMapper;
    private final NodeRepository nodeRepo;

    @Autowired
    public NodeServiceImpl(final NodeMapper nodeMapper, final NodeRepository nodeRepo) {
        this.nodeMapper = nodeMapper;
        this.nodeRepo = nodeRepo;
    }

    @Override
    public List<Node> getTree() {
        return this.nodeRepo.findAll()
        .stream()
        .map(t -> this.nodeMapper.mapDbNodeToNode(t))
        .collect(Collectors.toList());
    }

    @Override
    public List<DbNode> saveTree(final List<Node> nodeTreeList) {
        final List<DbNode> inputNodeList = nodeTreeList
        .stream()
        .map(t -> this.nodeMapper.mapNodeToDbNode(t))
        .collect(Collectors.toList());

        this.nodeRepo.deleteAll();
        return this.nodeRepo.saveAll(inputNodeList);
    }

}
package treemanagementapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import treemanagementapi.model.db.DbNode;
import treemanagementapi.model.rest.Node;
import treemanagementapi.repo.NodeRepository;
import treemanagementapi.utils.NodeMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NodeServiceTest {

    @Autowired
    private NodeService nodeService;

    @MockBean
    private NodeRepository nodeRepository;

    @Test
    public void whenGetTree_thenReturnNotNull() {
        List<DbNode> mockList = new ArrayList<>();
        mockList.add(createDbNode("key123", 123L, null));
        when(nodeRepository.findAll()).thenReturn(mockList);

        List<Node> nodeList = nodeService.getTree();

        assertThat(nodeList).isNotNull();
    }
    
    @Test
    public void whenGetTree_thenReturnNotEmpty() {
        List<DbNode> mockList = new ArrayList<>();
        mockList.add(createDbNode("key123", 123L, null));
        when(nodeRepository.findAll()).thenReturn(mockList);

        List<Node> nodeList = nodeService.getTree();

        assertThat(nodeList).isNotEmpty();
    }

    @Test
    public void whenGetTree_thenReturnIfOneThanRoot() {
        List<DbNode> mockList = new ArrayList<>();
        mockList.add(createDbNode("key123", 123L, null));
        when(nodeRepository.findAll()).thenReturn(mockList);

        List<Node> nodeList = nodeService.getTree();

        assertThat(nodeList).size().isEqualTo(1);
        assertThat(nodeList.get(0).getParent()).isNull();
    }

    @Test
    public void whenGetTree_thenContainNode() {
        List<DbNode> mockList = new ArrayList<>();
        mockList.add(createDbNode("key123", 123L, null));
        mockList.add(createDbNode("key456", 456L, "key123"));
        mockList.add(createDbNode("key789", 789L, "key456"));
        when(nodeRepository.findAll()).thenReturn(mockList);

        List<Node> nodeList = nodeService.getTree();

        DbNode dbNode = createDbNode("key456", 456L, "key123");
        NodeMapper mapper = new NodeMapper();
        Node node = mapper.mapDbNodeToNode(dbNode);

        assertThat(nodeList).contains(node);
    }

    @Test
    public void whenGetTree_thenReturnProperLength() {
        List<DbNode> mockList = new ArrayList<>();
        mockList.add(createDbNode("key123", 123L, null));
        mockList.add(createDbNode("key456", 456L, "key123"));
        mockList.add(createDbNode("key789", 789L, "key456"));
        when(nodeRepository.findAll()).thenReturn(mockList);

        List<Node> nodeList = nodeService.getTree();

        assertThat(nodeList).size().isEqualTo(3);
    }

    @Test
    public void whenSaveTree_thenReturnNotNull() {
        when(nodeRepository.saveAll(anyIterable())).thenReturn(new ArrayList<>());

        List<Node> nodeList = new ArrayList<>();

        assertThat(nodeService.saveTree(nodeList)).isNotNull();
    }

    @Test
    public void whenSaveTree_thenReturnEmpty() {
        when(nodeRepository.saveAll(new ArrayList<>())).thenReturn(new ArrayList<>());

        assertThat(nodeService.saveTree(new ArrayList<>())).isEmpty();
    }

    @Test
    public void whenSaveTree_thenReturnNotEmpty() {        
        List<DbNode> dbNodeList = new ArrayList<>();
        dbNodeList.add(new DbNode());

        when(nodeRepository.saveAll(dbNodeList)).thenReturn(dbNodeList);

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node());
        
        assertThat(nodeService.saveTree(nodeList)).isNotEmpty();
    }
    
    @Test
    public void whenSaveTree_thenReturnRoot() {        
        final String key = "key123";
        final Long value = 123L;
        final String parent = null;

        List<DbNode> dbNodeList = new ArrayList<>();
        dbNodeList.add(createDbNode(key, value, parent));

        when(nodeRepository.saveAll(dbNodeList)).thenReturn(dbNodeList);

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(createNode(key, value, parent));

        assertThat(nodeService.saveTree(nodeList).get(0).getParent()).isNull();
    }

    @Test
    public void whenSaveTree_thenReturnProperCount() {        
        final String key = "key123";
        final Long value = 123L;
        final String parent = null;

        List<DbNode> dbNodeList = new ArrayList<>();
        dbNodeList.add(createDbNode(key, value, parent));

        when(nodeRepository.saveAll(dbNodeList)).thenReturn(dbNodeList);

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(createNode(key, value, parent));

        assertThat(nodeService.saveTree(nodeList)).size().isEqualTo(1);
    }

    @Test
    public void whenSaveTree_thenReturnNode() {        
        final String key = "key123";
        final Long value = 123L;
        final String parent = null;

        List<DbNode> dbNodeList = new ArrayList<>();
        dbNodeList.add(createDbNode(key, value, parent));

        when(nodeRepository.saveAll(dbNodeList)).thenReturn(dbNodeList);

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(createNode(key, value, parent));

        assertThat(nodeService.saveTree(nodeList))
        .contains(createDbNode(key, value, parent));
    }

    private DbNode createDbNode(String key, Long value, String parent) {
        DbNode node = new DbNode();
        node.setKey(key);
        node.setValue(value);
        node.setParent(parent);
        return node;
    }
    
    private Node createNode(String key, Long value, String parent) {
        Node node = new Node();
        node.setKey(key);
        node.setValue(value);
        node.setParent(parent);
        return node;
    }

}
package treemanagementapi.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import treemanagementapi.model.db.DbNode;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NodeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void whenFindAll_thenReturnNotNull() {
        persistDbNode("key123", 123L, null);

        List<DbNode> nodeList = nodeRepository.findAll();

        assertThat(nodeList).isNotNull();
    }
    
    @Test
    public void whenFindAll_thenReturnNotEmpty() {
        persistDbNode("key123", 123L, null);

        List<DbNode> nodeList = nodeRepository.findAll();

        assertThat(nodeList).isNotEmpty();
    }

    @Test
    public void whenFindAll_thenReturnIfOneThanRoot() {
        persistDbNode("key123", 123L, null);

        List<DbNode> nodeList = nodeRepository.findAll();

        assertThat(nodeList).size().isEqualTo(1);
        assertThat(nodeList.get(0).getParent()).isNull();
    }

    @Test
    public void whenFindAll_thenContainNode() {
        persistDbNode("key123", 123L, null);
        final DbNode node = persistDbNode("key456", 456L, "key123");
        persistDbNode("key789", 789L, "key456");

        List<DbNode> nodeList = nodeRepository.findAll();

        assertThat(nodeList).contains(node);
    }

    @Test
    public void whenFindAll_thenReturnProperLength() {
        persistDbNode("key123", 123L, null);
        persistDbNode("key456", 456L, "key123");
        persistDbNode("key789", 789L, "key456");

        List<DbNode> nodeList = nodeRepository.findAll();

        assertThat(nodeList).size().isEqualTo(3);
    }

    private DbNode persistDbNode(String key, Long value, String parent) {
        DbNode node = new DbNode();
        node.setKey(key);
        node.setValue(value);
        node.setParent(parent);
        return entityManager.persistAndFlush(node);
    }

}
package treemanagementapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import treemanagementapi.model.db.DbNode;

@Repository
public interface NodeRepository extends JpaRepository<DbNode, String> {

}
package treemanagementapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import treemanagementapi.model.db.DbNode;

public interface NodeRepository extends JpaRepository<DbNode, String> {

}
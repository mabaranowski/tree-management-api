package treemanagementapi.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "tb_node")
public class DbNode {

    @Id
    @Column(name = "keyid")
    private String key;

    @Column(name = "nodevalue")
    private Long value;

    @Column(name = "parentnodeid")
    private String parent;
    
}

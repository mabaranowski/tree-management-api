package treemanagementapi.model.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DbNode {

    @Id
    @Column(name = "keyid")
    private String key;

    @Column(name = "nodevalue")
    private Long value;

    @Column(name = "childnodeid")
    private List<DbNode> children;

    @Column(name = "parentnodeid")
    private DbNode parentNode;

    @Column(name = "expanded")
    private Boolean expanded;

}

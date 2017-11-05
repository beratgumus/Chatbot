import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import products.*;

/**
 * Simple node class for creating tree. It will be used in product selecting cycle.
 */
public class TreeNode {
//    protected List<TreeNode> children;
    protected HashMap<String, TreeNode> children;
    protected TreeNode parent;
    protected String nodeType; // for printing purposes ('Select a ' +nodeType+' from list:')
    protected List<Product> productList; //this will be set only on leaf nodes

    /**
     * Constructor for tree botAnchor node. Create a node, give list of products and it will
     * create whole tree.
     * @param products list of products
     */
    public TreeNode(List<Product> products){
        children = new HashMap<>();
        this.nodeType = "product category"; //botAnchor is always category type. we start selecting from category

        for (Product p : products){
            if (!this.children.containsKey(p.getCategory())) {
                // current product is first product in x category. x="Consumer Electronics", "Major Appliance" ...
                this.children.put(p.getCategory(), new TreeNode("product type", this));
            }

            TreeNode typeNode = this.children.get(p.getCategory()); //temp variable, like an alias

            if (!typeNode.children.containsKey(p.getType())){
                // current product is first product in y type. y="Phone", "Laptop", "Dishwasher", "Car" ...
                typeNode.children.put(p.getType(), new TreeNode("product", typeNode));
            }

            TreeNode productNode = typeNode.children.get(p.getType()); //another alias

            productNode.productList.add(p); //finally we are in leaf node. lets add our product
        }
    }

    /**
     * For internal purposes only.
     */
    private TreeNode(String nodeType, TreeNode parent){
        this.children = new HashMap<>();
        this.nodeType = nodeType;
        this.parent = parent;
        if (nodeType.equals("product")){
            //we are creating a leaf node. we will add producs here. lets create a list
            productList = new ArrayList<>();
        }
    }

    public List<Product> getProductList() {
        return  productList;
    }

    /**
     * Gets next node at given key index.
     * @param key index of next node
     * @return next node
     */
    public TreeNode getNextNode(String key){
        return children.get(key);
    }

    /**
     * Gets all keys in current node.
     * @return Set of key strings
     */
    public String[] getAllKeys(){
        Set<String> keys = children.keySet();
        return keys.toArray(new String[keys.size()]);
    }
}

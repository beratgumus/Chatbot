import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Simple node class for creating tree. It will be used in product selecting cycle.
 */
public class TreeNode {
//    protected List<TreeNode> childs;
    protected HashMap<String, TreeNode> childs;
    protected TreeNode parent;
    protected String nodeType; // for printing purposes ('Select a ' +nodeType+' from list:')
    protected List<Product> productList; //this will be set only on leaf nodes

    /**
     * Constructor for tree root node. Create a node, give list of producs and it will
     * create whole tree.
     * @param products list of producs
     */
    public TreeNode(List<Product> products){
        childs = new HashMap<>();
        this.nodeType = "category"; //root is always category type. we start selecting from category

        for (Product p : products){
            if (!this.childs.containsKey(p.getCategory())) {
                // current product is first product in x category. x="Consumer Electronics", "Major Appliance" ...
                this.childs.put(p.getCategory(), new TreeNode("type"));
            }

            TreeNode typeNode = this.childs.get(p.getCategory()); //temp variable, like an alias

            if (!typeNode.childs.containsKey(p.getMachineType())){
                // current product is first product in y type. y="Phone", "Laptop", "Dishwasher", "Car" ...
                typeNode.childs.put(p.getMachineType(), new TreeNode("product"));
            }

            TreeNode productNode = typeNode.childs.get(p.getMachineType()); //another alias

            productNode.productList.add(p); //finally we are in leaf node. lets add our product
        }
    }

    /**
     * For internal purposes only.
     */
    private TreeNode(String nodeType){
        childs = new HashMap<>();
        this.nodeType = nodeType;
        if (nodeType.equals("product")){
            //we are creating a leaf node. we will add producs here. lets create a list
            productList = new ArrayList<>();
        }
    }

    /**
     * Gets product at given index. index starts from 1?
     * @param index index of product
     * @return found product
     */
    public Product getProduct(int index){
        return productList.get(index);
    }

    /**
     * Gets next node at given index. index starts from 1 or 0???
     * @param index
     * @return
     */
    public TreeNode getNextNode(int index){
        return childs.get(index);
//        return childs.get(index - 1); //enable this if you want to give indexes starting from 1
    }
}

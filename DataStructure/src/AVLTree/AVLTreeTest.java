package AVLTree;

public class AVLTreeTest
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		AVLTree<Integer> avlTree=new AVLTree<Integer>();
		
		avlTree.insert(70);
		avlTree.insert(21);
		avlTree.insert(9);
		avlTree.insert(53);
		avlTree.insert(89);
		avlTree.insert(72);
		avlTree.insert(75);
		avlTree.insert(87);
		avlTree.insert(93);
		
		avlTree.remove(89);
		avlTree.remove(93);
		avlTree.remove(87);
		
		avlTree.insert(60);
		
		avlTree.remove(75);
		
		avlTree.printTree2();
		


	}

}

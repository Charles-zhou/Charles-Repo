import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner in= new Scanner(System.in);
        int n,m;
        n=Integer.parseInt(in.nextLine());
        
        String[] dictionary=new String[n];
        
        for(int i=0; i<n; i++)
        	dictionary[i]=in.nextLine();
        
        /*
        for(String s:dictionary)
        	System.out.println(s);
         */
        
        m=Integer.parseInt(in.nextLine());
        String[] prefixs=new String[m];
        
        for(int i=0; i<m; i++)
        	prefixs[i]=in.nextLine();        
                
        TrieTree dicTree=new TrieTree();
        for(String w:dictionary)
        	dicTree.addWord(w);
        
        for(String p:prefixs)
        	System.out.println(dicTree.prefixCount(p));
        	
        
    }
}


class TrieTree {
	private static class Node {
		public Node() {
			num=0;
			edges=new Node[26];
			for(int i=0; i<edges.length; i++)
				edges[i]=null;
			end=false;
		}
		public int num;
		public boolean end;
		public Node[] edges;
	}
	
	public TrieTree() {
		root=null;
	}
	
	public void addWord(String word) {
		if(root==null) {
			root=new Node();
			addWord(root, word);
		}
		else 
			addWord(root, word);
	}
	
	private void addWord(Node T, String word) {
		if (word=="") {
			T.num++;
			T.end=true;
		}
		else {
			int index=word.charAt(0)-'a';
			if(T.edges[index]==null) {
				Node nextNode=new Node();
				T.edges[index]=nextNode;
				if(word.length()!=1)
					addWord(nextNode, word.substring(1));
				else 
					addWord(nextNode, "");
				T.num++;
			}
			else {
				T.num++;
				Node nextNode=T.edges[index];
				if(word.length()!=1)
					addWord(nextNode, word.substring(1));
				else 
					addWord(nextNode, "");
			}
		}
	}
	
	public int prefixCount(String prefix) {
		Node T=root;
		for(int i=0; i<prefix.length(); i++) {
			
			T=findNextNode(T, prefix.charAt(i));
			if(T==null)
				break;			
		}
		
		return T==null?0:T.num;
	}
	
	private Node findNextNode(Node T, char c) {
		if(T==null)
			return null;
		else {
			int index=c-'a';
			if(T.edges[index]!=null) 
				return T.edges[index];
			else
				return null;
		}
	}
	
	
	
	private Node root;
}








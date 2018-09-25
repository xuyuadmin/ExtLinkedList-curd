package com.xuyu.list;


@SuppressWarnings("all")

public class ExtLinkedList<E> {

	//链表实际存储长度
	private int size;
	//第一个元素（头结点，查询开始位置）
	private Node first;
	//最后一个元素（尾结点，插入开始位置）
	private Node last;
	
	private class Node{
		//存储元素结点的值
		Object object;
		//上一个结点
		Node prev;
		//下一个结点
		Node next;
	}
	private boolean isElementIndex(int index) {
		return index>0 && index<size;
		
	}
	//验证添加元素下标是否合理
	private void checkElementIndex(int index) {
		if(!isElementIndex(index)) {
			throw new IndexOutOfBoundsException("查询越界啦！");
		}
	}
	//getNode()
	public Node getNode(int index) {
		//下标验证
		checkElementIndex(index);
		Node node=null;
		//如果有头结点，那么就从头结点开始循环遍历链表
		if(first!=null) {
			//默认从第0个开始
			node=first;
			//如果index大于0，那么就开始循环链表
			for (int i = 0; i < index; i++) {
				//找到下标为index位置的结点
				node=node.next;
			}
		}
		//返回node
		return node;
	}
	//add()
	public void add(E e) {
		//创建结点
		Node node=new Node();
		//给结点赋值
		node.object=e;
		if(first==null) {
			//添加第一个元素，给第一个元素结点赋值
			first=node;
		}else {
			node.prev=last;
			last.next=node;
		}
		//结束后，把last标志移动到结点最后的位置
		last=node;
		//链表实际长度加1
		size++;
	}
	//add(int index,E e)
	public void add(int index,E e) {
		//验证下标位置是否合理
		checkElementIndex(index);
		//获取要插入原先链表下标位置的node结点
		ExtLinkedList<E>.Node oldNode = getNode(index);
		if(oldNode!=null) {
			//获取oldNode的上一个结点
			ExtLinkedList<E>.Node oldNodePrev = oldNode.prev;
			//获取oldNode的下一个结点
			ExtLinkedList<E>.Node oldNodeNext = oldNode.next;
			//创建Node实例
			Node nodeToAdd = new Node();
			//给node结点赋值
			nodeToAdd.object=e;
			//nodeToAdd的下一个结点为
			nodeToAdd.next=oldNode;
			//判断如果上一个结点为空，就把first标志给nodeToAdd结点
			if(oldNodePrev==null) {
				first=nodeToAdd;
			}else {
				//否则，nodeToAdd的上一个结点为oldNodePrev
				nodeToAdd.prev=oldNodePrev;
				//oldNodePrev的下一个结点为nodeToAdd
				oldNodePrev.next=nodeToAdd;
			}
			//oldNode的上一个结点变为nodeToAdd
			oldNode.prev=nodeToAdd;
			size++;
		}
	}
	//get(int index)获取指定下标的元素
	public Object get(int index) {
		//进行下标验证
		checkElementIndex(index);
		//获取指定下标结点
		ExtLinkedList<E>.Node getNode = getNode(index);
		//获取结点值
		Object getNodeObj = getNode.object;
		//返回结点值
		return getNodeObj;
	}
	
	//指定下标删除
	public void remove(int index) {
		//进行下标验证
		checkElementIndex(index);
		//1.获取当前要删除结点
		ExtLinkedList<E>.Node oldNode = getNode(index);
		//判断要删除的oldNode是否为空
		if(oldNode!=null) {
			//获取要删除元素的下结点
			ExtLinkedList<E>.Node oldNodeNext = oldNode.next;
			//获取要删除元素的上结点
			ExtLinkedList<E>.Node oldNodePrev = oldNode.prev;
			//如果要删除结点的上一个结点为null
			if(oldNodePrev==null) {
				//把first指向要删除元素的下一个结点
				first=oldNodeNext;
			}else {
				//将oldNodePrev的下一个结点变为oldNodeNext
				oldNodePrev.next=oldNodeNext;
				//置为空，垃圾回收
				oldNodePrev=null;
			}
			//如果要删除结点的下一个结点为null
			if(oldNodeNext==null) {
				//把last标志指向要删除元素的上一个结点oldNodePrev
				last=oldNodePrev;
			}else {
				//将oldNodeNext的上一个结点变为oldNodePrev
				oldNodeNext.prev=oldNodePrev;
				size--;
			}
			
		}
	}
	public static void main(String[] args) {
		ExtLinkedList<String> extLinkedList = new ExtLinkedList<String>();
		extLinkedList.add("a");
		extLinkedList.add("b");
		extLinkedList.add("c");
		extLinkedList.add("e");
		 System.out.println("删除之前:" + extLinkedList.get(1)); // C
		extLinkedList.remove(1);
		 System.out.println("删除之后:" + extLinkedList.get(1));// E
		// 其实从头查到尾### 效率不高，查询算法#####折半查找
		for (int i = 0; i < extLinkedList.size; i++) {
			System.out.println(extLinkedList.get(2));
		}
		 System.out.println(extLinkedList.get(1));
	}
}



















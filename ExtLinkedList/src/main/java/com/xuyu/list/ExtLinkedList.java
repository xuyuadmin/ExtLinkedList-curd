package com.xuyu.list;


@SuppressWarnings("all")

public class ExtLinkedList<E> {

	//����ʵ�ʴ洢����
	private int size;
	//��һ��Ԫ�أ�ͷ��㣬��ѯ��ʼλ�ã�
	private Node first;
	//���һ��Ԫ�أ�β��㣬���뿪ʼλ�ã�
	private Node last;
	
	private class Node{
		//�洢Ԫ�ؽ���ֵ
		Object object;
		//��һ�����
		Node prev;
		//��һ�����
		Node next;
	}
	private boolean isElementIndex(int index) {
		return index>0 && index<size;
		
	}
	//��֤���Ԫ���±��Ƿ����
	private void checkElementIndex(int index) {
		if(!isElementIndex(index)) {
			throw new IndexOutOfBoundsException("��ѯԽ������");
		}
	}
	//getNode()
	public Node getNode(int index) {
		//�±���֤
		checkElementIndex(index);
		Node node=null;
		//�����ͷ��㣬��ô�ʹ�ͷ��㿪ʼѭ����������
		if(first!=null) {
			//Ĭ�ϴӵ�0����ʼ
			node=first;
			//���index����0����ô�Ϳ�ʼѭ������
			for (int i = 0; i < index; i++) {
				//�ҵ��±�Ϊindexλ�õĽ��
				node=node.next;
			}
		}
		//����node
		return node;
	}
	//add()
	public void add(E e) {
		//�������
		Node node=new Node();
		//����㸳ֵ
		node.object=e;
		if(first==null) {
			//��ӵ�һ��Ԫ�أ�����һ��Ԫ�ؽ�㸳ֵ
			first=node;
		}else {
			node.prev=last;
			last.next=node;
		}
		//�����󣬰�last��־�ƶ����������λ��
		last=node;
		//����ʵ�ʳ��ȼ�1
		size++;
	}
	//add(int index,E e)
	public void add(int index,E e) {
		//��֤�±�λ���Ƿ����
		checkElementIndex(index);
		//��ȡҪ����ԭ�������±�λ�õ�node���
		ExtLinkedList<E>.Node oldNode = getNode(index);
		if(oldNode!=null) {
			//��ȡoldNode����һ�����
			ExtLinkedList<E>.Node oldNodePrev = oldNode.prev;
			//��ȡoldNode����һ�����
			ExtLinkedList<E>.Node oldNodeNext = oldNode.next;
			//����Nodeʵ��
			Node nodeToAdd = new Node();
			//��node��㸳ֵ
			nodeToAdd.object=e;
			//nodeToAdd����һ�����Ϊ
			nodeToAdd.next=oldNode;
			//�ж������һ�����Ϊ�գ��Ͱ�first��־��nodeToAdd���
			if(oldNodePrev==null) {
				first=nodeToAdd;
			}else {
				//����nodeToAdd����һ�����ΪoldNodePrev
				nodeToAdd.prev=oldNodePrev;
				//oldNodePrev����һ�����ΪnodeToAdd
				oldNodePrev.next=nodeToAdd;
			}
			//oldNode����һ������ΪnodeToAdd
			oldNode.prev=nodeToAdd;
			size++;
		}
	}
	//get(int index)��ȡָ���±��Ԫ��
	public Object get(int index) {
		//�����±���֤
		checkElementIndex(index);
		//��ȡָ���±���
		ExtLinkedList<E>.Node getNode = getNode(index);
		//��ȡ���ֵ
		Object getNodeObj = getNode.object;
		//���ؽ��ֵ
		return getNodeObj;
	}
	
	//ָ���±�ɾ��
	public void remove(int index) {
		//�����±���֤
		checkElementIndex(index);
		//1.��ȡ��ǰҪɾ�����
		ExtLinkedList<E>.Node oldNode = getNode(index);
		//�ж�Ҫɾ����oldNode�Ƿ�Ϊ��
		if(oldNode!=null) {
			//��ȡҪɾ��Ԫ�ص��½��
			ExtLinkedList<E>.Node oldNodeNext = oldNode.next;
			//��ȡҪɾ��Ԫ�ص��Ͻ��
			ExtLinkedList<E>.Node oldNodePrev = oldNode.prev;
			//���Ҫɾ��������һ�����Ϊnull
			if(oldNodePrev==null) {
				//��firstָ��Ҫɾ��Ԫ�ص���һ�����
				first=oldNodeNext;
			}else {
				//��oldNodePrev����һ������ΪoldNodeNext
				oldNodePrev.next=oldNodeNext;
				//��Ϊ�գ���������
				oldNodePrev=null;
			}
			//���Ҫɾ��������һ�����Ϊnull
			if(oldNodeNext==null) {
				//��last��־ָ��Ҫɾ��Ԫ�ص���һ�����oldNodePrev
				last=oldNodePrev;
			}else {
				//��oldNodeNext����һ������ΪoldNodePrev
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
		 System.out.println("ɾ��֮ǰ:" + extLinkedList.get(1)); // C
		extLinkedList.remove(1);
		 System.out.println("ɾ��֮��:" + extLinkedList.get(1));// E
		// ��ʵ��ͷ�鵽β### Ч�ʲ��ߣ���ѯ�㷨#####�۰����
		for (int i = 0; i < extLinkedList.size; i++) {
			System.out.println(extLinkedList.get(2));
		}
		 System.out.println(extLinkedList.get(1));
	}
}



















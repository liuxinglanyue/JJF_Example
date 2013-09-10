package com.algorithm.huffman;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class HuffmanTextEncoder {
	private File srcFile;
	private File dstFile;
	private MinHeap<HuffmanTreeNode> nodeHeap;
	private HuffmanTreeNode root;
	private Map<Character, Stack<Byte>> char2StackMap;
	private Map<Character,HuffmanTreeNode> char2NodeMap;
	private int totalChars;
	private int totalIndexNodeCount;

	public HuffmanTextEncoder(File src, File dst) {
		this.srcFile = src;
		this.dstFile = dst;
		char2StackMap = new HashMap<Character, Stack<Byte>>();
	}

	private void buildHeap() throws Exception {
		char2NodeMap = new HashMap<Character,HuffmanTreeNode>();
		BufferedReader in = new BufferedReader(new FileReader(srcFile));
		int ch;
		while ((ch = in.read()) != -1) {
			if (char2NodeMap.containsKey((char)ch)) {
				HuffmanTreeNode node = char2NodeMap.get((char)ch);
				node.freq ++;
			} else {
				HuffmanTreeNode node = new HuffmanTreeNode();
				node.c = (char)ch;
				node.freq = 1;
				char2NodeMap.put(node.c, node);
			}
		}
		in.close();
		nodeHeap = new MinHeap<HuffmanTreeNode>();
		for (Iterator<Map.Entry<Character,HuffmanTreeNode>> iterator = char2NodeMap
				.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<Character,HuffmanTreeNode> entry = iterator.next();
			HuffmanTreeNode node = entry.getValue();
			nodeHeap.add((Comparable<HuffmanTreeNode>) node);
		}

	}

	private void buildTree() {
		if (nodeHeap != null && nodeHeap.size() > 0) {
			while (nodeHeap.size() >= 2) {
				HuffmanTreeNode node1 = nodeHeap.pop();
				HuffmanTreeNode node2 = nodeHeap.pop();
				HuffmanTreeNode newNode = new HuffmanTreeNode();
				newNode.freq = node1.freq + node2.freq;
				newNode.left = node1.compareTo(node2) > 0 ? node2 : node1;
				newNode.right = node1.compareTo(node2) > 0 ? node1 : node2;
				node1.parent = newNode;
				node2.parent = newNode;
				nodeHeap.add((Comparable<HuffmanTreeNode>) newNode);
				
			}
			root = nodeHeap.pop();
			nodeHeap = null;
			System.gc();
		}
	}


	private void InorderTraversal(ByteArrayOutputStream baos,HuffmanTreeNode node) throws Exception{
		Queue<HuffmanTreeNode> queue = new LinkedBlockingQueue<HuffmanTreeNode>();
		if(node != null){
			queue.add(node);
			//debugNodeChar("add:",node.c);
			while(!queue.isEmpty()){
				HuffmanTreeNode head = queue.poll();
				//debugNodeChar("poll:",head.c);
				
				baos.write(Util.int2Bytes(head.c, 2));
				totalIndexNodeCount ++;
				if(head.left != null){
					queue.add(head.left);
					//debugNodeChar("add:",head.left.c);
				}
				
				if(head.right != null){
					queue.add(head.right);
					//debugNodeChar("add:",head.right.c);
				}
				
			}
		}
	}

	private byte[] buildHufCodeTable() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// magic number
		baos.write('H');
		baos.write('U');
		baos.write('F');
		baos.write('F');
		// version
		baos.write(1);
		// code table length
		
		baos.write(Util.int2Bytes(char2NodeMap.size() * 2 - 1, 2));
		InorderTraversal(baos,root);
		Util.debug("totalIndexNodeCount = "+totalIndexNodeCount);
		baos.close();
		return baos.toByteArray();

	}

	@SuppressWarnings("unchecked")
	private Stack<Byte> getCharBits(char c) {

		Stack<Byte> stack = char2StackMap.get(c);
		if (stack != null) {
			return (Stack<Byte>)stack.clone();
		}
		HuffmanTreeNode node = char2NodeMap.get(c);
		if (node != null) {
			Stack<Byte> newStack = new Stack<Byte>();
			if(node == root){
				newStack.push((byte) 1);
			}else{
				while (node.parent != null) {
					HuffmanTreeNode parent = node.parent;
					if (node == parent.right) {
						newStack.push(new Byte((byte)1));
					} else {
						newStack.push(new Byte((byte)0));
					}
					node = parent;
				}
			}
			char2StackMap.put(c, newStack);
			return (Stack<Byte>)newStack.clone();
		}else{
			assert 1 == 0;
			return null;
		}
		

	}

	private byte[] getContentBytes() throws Exception {
		BitWriter bw = new BitWriter(128);
		BufferedReader in = new BufferedReader(new FileReader(srcFile));
		int ch = -1;
		totalChars = 0;
		while ((ch = in.read()) != -1) {
			Stack<Byte> bitsStack = getCharBits((char) ch);
			if (bitsStack != null) {
				totalChars++;
				while(!bitsStack.isEmpty()){
					byte b = bitsStack.pop();
					bw.write(b, 1);
				}
			}else{
				continue;
			}
		}
		in.close();
		return bw.toByteArray();
		
	
	}

	public void encode() throws Exception {
		buildHeap();
		buildTree();
		if(dstFile.exists()){
			dstFile.delete();
		}
//		
		byte[] head = buildHufCodeTable();
		FileOutputStream fos = new FileOutputStream(dstFile);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.write(head);
		Util.debug("head len="+head.length);
		byte[] content = getContentBytes();
		Util.debug("content len="+content.length);
		dos.write(Util.int2Bytes(content.length, 4));
		dos.write(Util.int2Bytes(totalChars, 4));
		dos.write(content);
		//reserved
		dos.write(Util.int2Bytes(0, 4));
		dos.close();
		fos.close();
	}

	public static void main(String[] args) throws Exception {
		 File src = new File("source/huffman/平凡的世界.txt");
		 File dst = new File("source/huffman/out.huff");
		 HuffmanTextEncoder Encoder = new HuffmanTextEncoder(src, dst);
		 Encoder.encode();
		
	}

}

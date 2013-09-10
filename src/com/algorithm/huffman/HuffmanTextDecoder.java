package com.algorithm.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class HuffmanTextDecoder {
	private File srcFile;
	private HuffmanTreeNode root;
	private static final int CUR_VERSION = 1;
	private int nodesCount;

	public HuffmanTextDecoder(File src) {
		srcFile = src;
	}

	private boolean testHead(byte[] head) {
		if (head[0] == 'H' && head[1] == 'U' && head[2] == 'F'
				&& head[3] == 'F') {
			if (head[4] == CUR_VERSION) {
				return true;
			}
		}
		return false;
	}

	private void parseFlatTree(byte[] ft) {
		Queue<HuffmanTreeNode> queue = new LinkedBlockingQueue<HuffmanTreeNode>();
		int curIndex = 0;
		root = new HuffmanTreeNode();
		root.c = (char) Util.bytes2Int(ft, curIndex * 2, 2);
		queue.add(root);

		while (!queue.isEmpty()) {
			HuffmanTreeNode node = queue.poll();
			if (node.c == 0xfffe) {
				int leftIndex = curIndex + 1;
				int rightIndex = curIndex + 2;
				if (leftIndex < ft.length / 2) {
					HuffmanTreeNode left = new HuffmanTreeNode();
					left.c = (char) Util.bytes2Int(ft, leftIndex * 2, 2);

					node.left = left;
					queue.add(left);
					curIndex++;
				}

				if (rightIndex < ft.length / 2) {
					HuffmanTreeNode right = new HuffmanTreeNode();
					right.c = (char) Util.bytes2Int(ft, rightIndex * 2, 2);

					node.right = right;
					queue.add(right);
					curIndex++;
				}
			}
		}

	}

	private char getCharFromTree(BitReader br) {
		HuffmanTreeNode node = root;
		while (!node.isLeaf()) {
			byte bit = br.readBit();
			assert bit >= 0;
			if (bit == 1) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return node.c;

	}

	public String decode2String() {
		return decode().toString();
	}

	public void decode2File(File dst) throws Exception {
		
		if(!dst.exists()){
			dst.createNewFile();
		}
		
		BufferedWriter out = new BufferedWriter(new FileWriter(dst));
		StringBuffer sb = decode();
		int chars = sb.length();
		for(int i = 0 ;i < chars; i++){
			char c = sb.charAt(i);
			out.write(c);
		}
		out.close();
	}

	private StringBuffer decode() {
		BufferedInputStream bis;
		StringBuffer sb = new StringBuffer();
		try {
			bis = new BufferedInputStream(new FileInputStream(srcFile));
			byte[] head = new byte[7];
			bis.read(head);
			if (testHead(head)) {
				nodesCount = Util.bytes2Int(head, 5, 2);
				byte[] codeTable = new byte[nodesCount * 2];
				bis.read(codeTable);
				parseFlatTree(codeTable);
				if (root != null) {
					byte[] data = new byte[4];
					bis.read(data);
					//int blockSize = Util.bytes2Int(data);
					bis.read(data);
					int charCount = Util.bytes2Int(data);
					int readCount = 0;
					BitReader br = new BitReader(bis);
					while (readCount < charCount) {
						char c = getCharFromTree(br);
						sb.append(c);
						readCount++;
					}

				}
			}
			bis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.delete(0, sb.length());
		}

		return sb;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		HuffmanTextDecoder decoder = new HuffmanTextDecoder(
				new File("source/huffman/out.huff"));
		decoder.decode2File(new File("source/huffman/decoded.txt"));
	
	}

}

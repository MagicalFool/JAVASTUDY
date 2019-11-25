package com.cc.ads.part001_AVL_Tree;

public class AVLTreeNode {


    private Integer value;

    private AVLTreeNode lChild;

    private AVLTreeNode rChild;

    private int height;

    AVLTreeNode() {
    }

    public AVLTreeNode(Integer value) {
        this.value = value;
    }

    // 右右型 左旋
    public AVLTreeNode RR(AVLTreeNode node){
        AVLTreeNode temp;
        temp = node.rChild;  // 左节点
        node.rChild = node.lChild;  // 右节点左移
        temp.lChild = node; //
        node = temp;
        return node;
    }

    // 左左型 右旋
    public AVLTreeNode LL(AVLTreeNode node){
        AVLTreeNode temp;
        temp = node.lChild;
        node.lChild = node.rChild;
        temp.rChild = node;
        node = temp;
        return node;
    }
    // 左右型 先右旋后左旋
    public AVLTreeNode LR(AVLTreeNode node){
        LL(node);
        RR(node);
        return node;
    }

    // 左右型 先右旋后左旋
    public AVLTreeNode RL(AVLTreeNode node){
        RR(node);
        LL(node);
        return node;
    }

    public boolean type(AVLTreeNode node){
        AVLTreeNode l = node.lChild;
        AVLTreeNode r = node.rChild;
        int li = 0,ri = 0;
        if (l != null){
            li ++ ;
            type(l);
        }
        if (r != null){
            ri ++;
            type(r);
        }
        return li-ri >=2;
    }
}

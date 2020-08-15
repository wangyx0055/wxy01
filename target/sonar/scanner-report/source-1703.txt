package com.longersec.blj.utils;

import com.longersec.blj.domain.DTO.DepartDTO;
import com.longersec.blj.domain.Department;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static List<DepartDTO> rebuildList2Tree(List<DepartDTO> treeNodes) {
        boolean existRootNode = false;
        List<DepartDTO> newTree = new ArrayList<DepartDTO>();//初始化一个新的列表
        for (DepartDTO treeNode : treeNodes) {
            if (isRootNode(treeNode, treeNodes)) {//选择根节点数据开始找儿子
                newTree.add(findChildren(treeNode, treeNodes));
                existRootNode = true;
            }
        }
        if(!existRootNode){//也可能大家都是根节点
            return treeNodes;
        }
        return newTree;
    }

    /**
     * 判断节点是否是根节点
     * @param checkNode
     * @param treeNodes
     * @return
     */
    private static boolean isRootNode(DepartDTO checkNode, List<DepartDTO> treeNodes) {
        for (DepartDTO treeNode : treeNodes) {
            if (checkNode.getParent_id().equals(treeNode.getId())) {//判断checkNode是不是有爸爸
                return  false;
            }
        }
        return true;
    }


    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static DepartDTO findChildren(DepartDTO parentNode, List<DepartDTO> treeNodes) {
        List<DepartDTO> children = parentNode.getNodes();
        for (DepartDTO it : treeNodes) {
            if (parentNode.getId().equals(it.getParent_id())) {//找儿子，判断parentNode是不是有儿子
                children.add(findChildren(it, treeNodes));
            }
        }
        return parentNode;
    }



    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
//    public static List<Department> rebuildListTree(List<Department> treeNodes) {
//        boolean existRootNode = false;
//        List<Department> newTree = new ArrayList<Department>();//初始化一个新的列表
//        for (Department treeNode : treeNodes) {
//            if (isRootNode1(treeNode, treeNodes)) {//选择根节点数据开始找儿子
//                newTree.add(findChildren1(treeNode, treeNodes));
//                existRootNode = true;
//            }
//        }
//        if(!existRootNode){//也可能大家都是根节点
//            return treeNodes;
//        }
//        return newTree;
//    }
//
//    /**
//     * 判断节点是否是根节点
//     * @param checkNode
//     * @param treeNodes
//     * @return
//     */
//    private static boolean isRootNode1(Department checkNode, List<Department> treeNodes) {
//        for (Department treeNode : treeNodes) {
//            if (checkNode.getParent_id().equals(treeNode.getId())) {//判断checkNode是不是有爸爸
//                return  false;
//            }
//        }
//        return true;
//    }
//
//
//    /**
//     * 递归查找子节点
//     *
//     * @param treeNodes
//     * @return
//     */
//    private static Department findChildren1(Department parentNode, List<Department> treeNodes) {
//        List<Department> children = parentNode.getChildren();
//        for (Department it : treeNodes) {
//            if (parentNode.getId().equals(it.getParent_id())) {//找儿子，判断parentNode是不是有儿子
//                children.add(findChildren1(it, treeNodes));
//            }
//        }
//        return parentNode;
//    }
}

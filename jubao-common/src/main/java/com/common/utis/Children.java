package com.common.utis;
/***
 * 树形菜单
 * @author 12146
 *
 */
public class Children {

  private ZTree  zTree;
  
public Children() {
	super();
}

public Children(ZTree zTree) {
	super();
	this.zTree = zTree;
}

public ZTree getzTree() {
	return zTree;
}

public void setzTree(ZTree zTree) {
	this.zTree = zTree;
} 

   
}

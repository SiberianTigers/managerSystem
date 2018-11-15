/***
 * 这个函数用来跳转分页的  
 * 
 *  obj：  代表表单对象
 *  num:   代表  第几页
 */
function pageToIndex(obj,num){
	obj.pageIndex.value=num;	 
	obj.submit();
}
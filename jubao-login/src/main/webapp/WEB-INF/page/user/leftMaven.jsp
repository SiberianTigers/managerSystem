<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<div class="m_left" style="color: black;">
        	<div class="left_n">管理中心</div>
        	   <div class="left_m">
            	<div class="left_m_t t_bg4">购物管理</div>
                <ul>
                	<li><a href="javaScript:void(0)"  url="/login/user/getAddress.html"  class="left_menu" onclick="select_menu(this)"><span>收货地址</span></a></li>
                    <li><a href="javaScript:void(0)"   class="left_menu" url="/login/user/findUserOrder.html?status=1" onclick="select_menu(this)"><span>已购买到的宝贝</span></a></li>
                    <li><a href="javaScript:void(0)" class="left_menu" url="/shop/shop/warehouseProduct.html" onclick="select_menu(this)"><span>我的订单</span></a></li>
                </ul>
            </div>
        	
            <div class="left_m">
            	<div class="left_m_t t_bg1">会员中心</div>
                <ul>
                	<li><a href="javaScript:void(0)" class="left_menu">个人修改</a></li>
                    <li><a href="javaScript:void(0)" class="left_menu">宝贝收藏</a></li>
                </ul>
            </div>
            
        </div>

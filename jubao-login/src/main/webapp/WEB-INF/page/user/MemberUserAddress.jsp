<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<p></p>
	<div class="mem_tit">收货地址</div>
	<div class="mem_tit" style="font-size: 12px">
		<p>
			<span style="color: orange;">新增收货地址</span>&nbsp;电话号码、手机号选填一项、其余均为必填项
		</p>
	</div>
	<form action="#" method="post" id="from1">
		<table border="0" class="add_tab" style="width:930px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<!-- 用户id -->
				<input type="hidden" name="userId" value="${user.userid }" />
				<td width="135" align="right">所在地区</td>
				<td colspan="3" style="font-family:'宋体';"><select class="jj"
					name="country" style="background-color:#f6f6f6;">
						<option value="1" selected="selected">中国</option>
				</select> <!--省级 --> <input type="hidden" name="sf" id="sf" /> <select
					class="jj" name="province" id="coutry1" onchange="Selectcheck()">

				</select> <!-- 城市 --> <input type="hidden" name="sq" id="sq" /> <select
					class="jj" name="city" id="city" onchange="Selectcheck1()">


				</select> <!--区 县级 --> <input type="hidden" name="qx" id="qx" /> <select
					class="jj" name="area" id="area">

				</select> （必填）<span id="Info" style="color: red;"></span></td>
			</tr>
			<tr>
				<td align="right">详细地址</td>
				<td style="font-family:'宋体';"><textarea rows="5" cols="60"
						name="addressInfo" id="addressInfo"></textarea><span id="Info1"
					style="color: red;"></span></td>
			</tr>
			<tr>
				<td align="right">邮政编码</td>
				<td style="font-family:'宋体';"><input type="text"
					class="add_ipt" name="zipCode" id="youbian" /><span id="Info2"
					style="color: red;"></span></td>
			</tr>
			<tr>
				<td align="right">收货人姓名</td>
				<td style="font-family:'宋体';"><input type="text"
					class="add_ipt" name="remark" id="getName" />（必填）<span id="Info3"
					style="color: red;"></span></td>
			</tr>

			<tr>
				<td align="right">手机</td>
				<td style="font-family:'宋体';"><input type="text"
					class="add_ipt" name="phone" id="iphone" />（必填）<span id="Info4"
					style="color: red;"></span></td>
			</tr>
			<tr>
				<td align="right"><input type="checkbox" name="isdefault"
					value="1" id="defaultAddress" /></td>
				<td style="font-family:'宋体';">设为默认收货地址</td>
			</tr>

			<tr>
				<td></td>
				<td><button type="button" class="btn btn-success"
						onclick="addUserAddress()">添 加</button></td>
			</tr>
		</table>
	</form>


	<!-- 用户地址信息 -->
	<div id="userAddressInfo">
	<%-- 	<%@include file="../userMenu/userAddress.jsp"%> --%>
	  
	</div>



<script type="text/javascript" src="/statics/locajs/address.js">	    
        

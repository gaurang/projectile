<%@ page isELIgnored="false" %>

<link href="css/diamond.css" rel="stylesheet" type="text/css"/>
<style media="all" type="text/css">@import "css/dimond.css";</style>
<style media="all" type="text/css">@import "css/pro_dropdown_2.css";</style>
<link href="css/contentslider.css" rel="stylesheet" type="text/css">
<script src="js/contentslider.js" type="text/javascript"></script>
	<!--[if lt IE 7]>
		<link rel="stylesheet" type="text/css" href="menu/includes/ie6.css" media="screen"/>
	<![endif]-->

<div class="header">
	  <p>&nbsp;</p>


	<span class="preload1"></span>
<span class="preload2"></span>
	<div id="tab">
			<ul id="nav">
				<li class="top">
					<a href="home.jsp" target="_self" class="top_link">
						<span >Home</span>
					</a>
				</li>

				<li class="top">
					<a href="#" target="_self" class="top_link">
						<span class="down">Search</span>
						</a>
		   				<ul class="sub">
		   					<li>
								<a href="webUserSearch.html" target="_self">Search Criteria</a>
							</li>
        			   		<li>
								<a href="invoiceReprint.html?OrderType=Request&orderId=0" target="_self">Search By Request</a>
							</li>
		   				</ul>
				</li>


				<li class="top">
					<a href="#" target="_self" class="top_link">
						<span class="down">Personal Panel</span>
					</a>
		   				<ul class="sub">
        			   			
							<li>
								<a href="myAccount.html" target="_self">Confirm Orders</a>
							</li>
        			   			<li>
								<a href="myCart.html" target="_self">Shopping Cart</a>
							</li>
							<li>
								<a href="orderList.html" target="_self">Orders Till date</a>
							</li>
		   				</ul>
				</li>
					<li class="top">
					<a href="#" target="_self" class="top_link">
						<span class="down">Add ons</span>
					</a>
		   				<ul class="sub">
        			   			<li>
								<a href="registrationView.html" target="_self">Edit Profile</a>
							</li>
        			   			<li>
								<a href="payment.html" target="_self">Payment Process</a>
							</li>
							<li>
								<a href="changepwd.html" target="_self">Reset password</a>
							</li>
		   				</ul>
				</li>
				<li class="top">
					<a href="diamond_edu.jsp" target="_self" class="top_link">
						<span> Diamond Education</span>
					</a>
				</li>
				<li class="top">
					<a href="logOut.html" target="_self" class="top_link">
						<span >Logout</span>
					</a>
				</li>


		   	</ul><!--
		   	<div align="right" style="padding: 2px;display: inline-table;margin-top: 45px;">
		   		<img src="images/facebook_icon.png" width="20" height="20" border="0" />
	        	<img src="images/twitter_icon.png" width="20" height="20" border="0" />
	        </div>
	
	   	-->
	   	</div>
		<div class="info" align="left" style="margin-left:135px;">
			${USER_SESSION.compnayName} >>  ${USER_SESSION.userName} 
		</div>
</div>

			<%--<div class="nav-right"></div> --%>

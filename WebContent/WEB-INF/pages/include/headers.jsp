<%@ page isELIgnored="false" %>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="90%"><table width="756" height="39" border="0" align="center" cellpadding="0" cellspacing="0" id="Table_01">
                  <tr>
                    <td>
                    	<a href="home.jsp">
                    		<img <c:if test="${param.page == 'home'}">src="images/menu_over_01.png"</c:if> <c:if test="${param.page != 'home'}">src="images/menu_01.png"</c:if> alt="" name="Image1" width="96" height="39" border="0" id="Image1" onmouseover="MM_swapImage('Image1','','images/menu_over_01.png',1)" onmouseout="MM_swapImgRestore()" />
                    	</a>
                    </td>
                    <td><a href="portfolio.jsp"><img <c:if test="${param.page == 'portfolio'}">src="images/menu_over_02.png"</c:if> <c:if test="${param.page != 'portfolio'}">src="images/menu_02.png"</c:if> alt="" name="Image2" width="199" height="39" border="0" id="Image2" onmouseover="MM_swapImage('Image2','','images/menu_over_02.png',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                    <td><a href="diamond_edu.jsp"><img <c:if test="${param.page == 'edu'}">src="images/menu_over_03.png"</c:if> <c:if test="${param.page != 'edu'}">src="images/menu_03.png"</c:if> alt="" name="Image3" width="200" height="39" border="0" id="Image3" onmouseover="MM_swapImage('Image3','','images/menu_over_03.png',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                    <td><a href="testimonials.jsp"><img <c:if test="${param.page == 'testimonials'}">src="images/menu_over_04.png"</c:if> <c:if test="${param.page != 'testimonials'}">src="images/menu_04.png"</c:if> alt="" name="Image4" width="145" height="39" border="0" id="Image4" onmouseover="MM_swapImage('Image4','','images/menu_over_04.png',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                    <td><a href="contact.jsp"><img <c:if test="${param.page == 'contact'}">src="images/menu_over_05.png"</c:if> <c:if test="${param.page != 'comtact'}">src="images/menu_05.png"</c:if> alt="" name="Image5" width="116" height="39" border="0" id="Image5" onmouseover="MM_swapImage('Image5','','images/menu_over_05.png',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                  </tr>
                </table></td>
                <td width="10%" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="52%"><div align="right" style="padding: 2px;"><img src="images/facebook_icon.png" width="20" height="20" border="0" /></div></td>
                    <td width="48%"><div align="left" style="padding: 2px;"><img src="images/twitter_icon.png" width="20" height="20" border="0" /></div></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
            </tr>
          
          <tr>
            <td width="854" height="232" style="background-image:url('images/banner.png');background-repeat: no-repeat;" ><div align="center">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="2%">&nbsp;</td>
                  <td width="98%"><div align="left"></div></td>
                </tr>
              </table>
              </div></td>
          </tr>
 </table>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>

<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
  <!-- <script src="http://maps.google.com/maps?file=api&v=1&key=ABQIAAAAGUoO7VI1_vsRuRacOGppYRRBxEDVIeLZxJDJPoO1RAFD9MmPwBR3ya3rxsB-RfygJCUv3bQxhPRrXA" type="text/javascript"></script> -->
  <script src="http://maps.google.com/maps?file=api&v=1&key=ABQIAAAAGUoO7VI1_vsRuRacOGppYRRBxEDVIeLZxJDJPoO1RAFD9MmPwBR3ya3rxsB-RfygJCUv3bQxhPRrXA" type="text/javascript"></script>

   <script type="text/javascript">
     <!--
     var markers = new Array();
     var lefts = [];
     var rights = [];
     var curtab = 0;
     update_tabs = function(a,b,c)
     {
      if (a == "page")
      {
       var text = gentab(curtab, parseFloat(b));
       markers[curtab].openInfoWindowHtml(text);
      }
     }
     dummy_function = function(a,b,c) {}
     function gentab(i,p)
     {
      if (p==1)
      {
       var text='<div style="white-space: nowrap;" page="1" label="Address" class="active">'+lefts[i]+'</div><div style="white-space: nowrap;" page="2" label="Directions"></div>'
      }
      else
      {
       var text='<div style="white-space: nowrap;" page="1" label="Address"></div><div style="white-space: nowrap;" page="2" label="Directions" class="active">'+rights[i]+'</div>'
      }
      return text;
     }
     function gennotab(i)
     {
      return lefts[i];
     }
     function makeColorIcon(num)
     {
      var cicons = new Array("mm_20_purple.png", "mm_20_yellow.png", "mm_20_blue.png", "mm_20_white.png", "mm_20_green.png", "mm_20_red.png", "mm_20_black.png", "mm_20_orange.png", "mm_20_gray.png", "mm_20_brown.png");
      var icon = new GIcon();
      icon.image = "http://labs.google.com/ridefinder/images/"+cicons[num-1]
      icon.shadow = "http://labs.google.com/ridefinder/images/mm_20_shadow.png"
      icon.iconSize = new GSize(12,20);
      icon.shadowSize = new GSize(22,20);
      icon.iconAnchor = new GPoint(6,20);
      icon.infoWindowAnchor = new GPoint(5,1);
      return icon;
     }
     function makeLetterIcon(num)
     {
      var icon = new GIcon(G_DEFAULT_ICON);
      icon.setImage("http://local.google.com/mapfiles/marker"+String.fromCharCode(num+64)+".png");
      return icon;
     }
     function makeMarker(id,text,x,y,icon,directions,showdir)
     {
      var ttext;
      point = new GPoint(x,y);
      marker = new GMarker(point,icon);
      markers[id]=marker;
      lefts[id]=text;
      rights[id]=unescape(directions);
      if (showdir==1)
      {
       ttext=gentab(id,1);
      }
      else
      {
       ttext=gennotab(id);
      }
      map.addOverlay(markers[id]);
      GEvent.addListener(marker,"click",function ()
      {
       curtab=id
       markers[id].openInfoWindowHtml(ttext);
      });
     };
     //-->
   </script>
   <div class="ref_detail" style="width:800px;">
   	<h2 align="center">Contact Us</h2>
       <table width="800px;" align="center" >
       <tr>
       	  <td valign="top">
       	  	<div class="details" >
       	  	<font style="font-weight: bold;">Mr. Kunal A. Nahar</font>
			<br/>
			<br/>
			12-A, Shreeji Plaza, 1st Floor,
			<br/>
			Next to Panchratna, Opera House,
			<br/>
			Mumbai - 400 004.
			<br/><br/>
			Tel: +91 22 23669440/ 23629350
			<br/><br/>
			Fax: +91 22 23633550
			<br/><br/>
			Cell: +91 9821257281/ 9320057281
			<br/><br/>
			Email: info@kbgems.com 
       	  	
       	  	</div>
       	  
       	  </td>	
          <td align="right">
          <br><div id="map1" style="width: 500px; height: 400px" align="center"></div><br>
        </td>
       </tr>
       </table>
   
   <script type="text/javascript">

    if (GBrowserIsCompatible()) {
    var map = new GMap(document.getElementById("map1"));
    map.centerAndZoom(new GPoint(72.817061, 18.955542), 1)
    map.addControl(new GLargeMapControl());
    map.addControl(new GMapTypeControl());
    var text="<div ><b>Office Address</b></div><div style='font-size:11px;' >12-A shreeji plaza</div><div style='font-size:11px;'></div><div style='font-size:11px;' >Opera house</div></form>";
    var icon = new GIcon(G_DEFAULT_ICON);
    makeMarker(0,text,72.817061,18.955485,icon,"<div style='margin-top:3px; font-size:11px;'>Enter Starting Address:</div><form target='_blank' method='get' action='http://maps.google.com/maps'><div><input type='hidden' name='daddr' value='12-A shreeji plaza, Opera house Churni road' /><div><input id='saddr' type='text' size='35' name='saddr' /></div><div><input type='submit' name='btnG' value='Get Directions' /></div>",1);
    markers[0].openInfoWindowHtml(gentab(0,1));
    }
    iw = map.getInfoWindow();
    //iw.addContext("iwstate",update_tabs);
    //iw.addContext("iwnavigate",dummy_function);
  
   </script>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
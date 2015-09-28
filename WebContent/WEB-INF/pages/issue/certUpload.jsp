<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Projectile:</title>
<meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar and preview images for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<link rel="stylesheet" href="css/certUpload/bootstrap.min.css">
<!-- <link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/certUpload/bootstrap-responsive.min.css">
<link rel="stylesheet" href="css/certUpload/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="css/certUpload/jquery.fileupload-ui.css">

<!-- <script src="js/certUpload/jquery.min.js"></script>  -->
<script src="js/certUpload/jquery.ui.widget.js"></script>
<script src="js/certUpload/tmpl.min.js"></script>
<script src="js/certUpload/load-image.min.js"></script>
<script src="js/certUpload/canvas-to-blob.min.js"></script>
<script src="js/certUpload/bootstrap.min.js"></script>
<script src="js/certUpload/bootstrap-image-gallery.min.js"></script>
<script src="js/certUpload/jquery.iframe-transport.js"></script>
<script src="js/certUpload/jquery.fileupload.js"></script>
<script src="js/certUpload/jquery.fileupload-fp.js"></script>
<script src="js/certUpload/jquery.fileupload-ui.js"></script>
<script src="js/certUpload/locale.js"></script>
<script src="js/certUpload/main.js"></script>

<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>{%=locale.fileupload.start%}</span>
                </button>
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="cancel">{% if (!i) { %}
            <button class="btn btn-warning">
                <i class="icon-ban-circle icon-white"></i>
                <span>{%=locale.fileupload.cancel%}</span>
            </button>
        {% } %}</td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else { %}
            <td class="preview">{% if (file.thumbnail_url) { %}
                <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}"><img src="{%=file.thumbnail_url%}"></a>
            {% } %}</td>
            <td class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
        {% } %}
        <td class="delete">
            <button class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}">
                <i class="icon-trash icon-white"></i>
                <span>{%=locale.fileupload.destroy%}</span>
            </button>
            <input type="checkbox" name="delete" value="1">
        </td>
    </tr>
{% } %}
</script>

<script type="text/javascript">
var data = new Array();
var filename=new Array();
function DeleteFunction(fileName){
	$.ajax({type:'POST',
        url:'deleteFile.html?fileName='+fileName,
        dataType: 'json',
        success: function(json){
               data = json;
               printFiles(data);
        },
        error: function(xmlHttpRequest, textStatus, errorThrown) {
            
        }
    });
}
function startFunction(){
    $.ajax({type:'POST',
        url:'getAllFileList.html',
        dataType: 'json',
        success: function(json){
               data = json;
               printFiles(data);
        },
        error: function(xmlHttpRequest, textStatus, errorThrown) {
            
        }
    });
}
function URLDecode(psEncodeString)
{
  return unescape(psEncodeString);
}
function URLEncode(psEncodeString)
{
  return escape(psEncodeString);
}
function printFiles(data){
    var html = '';
    if(data.length=='0'){
        var thumbList="<tr class='template-upload fade in'><td>No Previous Uploaded Data Available</td></tr>";
        html+=thumbList;
        $('#listofFiles').html(html);
    }
    for(i=0;i<data.length;i++){
        filename=data[i].filename;
        var thumbList="<tr class='template-upload fade in'>";
        thumbList+="<td class='preview'><span class='fade in'><img src='getImageThumb.html?path="+ URLEncode(data[i].path)+"' width='30' height='10' /></span></td>";
        thumbList+="<td class='name'><a download='"+data[i].filename+"' rel='gallery' title='"+data[i].filename+"' href='getImageThumb.html?path="+ URLEncode(data[i].path)+"'>"+data[i].filename+"</a></td>";
        thumbList+="<td class='size'><span>"+data[i].size+" kb<span></td>";
        thumbList+="<td class='delete'><button id='deleteButton' class='btn btn-danger deleteButton' type='button' data-url='"+data[i].commonUrl+"/deleteFile.html?fileName="+data[i].filename+"' data-type='POST'><i class='icon-trash icon-white'></i><span>Delete</span></button><input id='delete' type='checkbox' value='1' name='delete'></td><input type='hidden' name='fileName' value='"+data[i].filename+"' id='fileName'></input>";
        thumbList+="</tr>";
        html+=thumbList;
    }
    $('#listofFiles').html(html);
    
}
$(document).ready(function(){
	startFunction();
});
</script>
</head>
<body>
<jsp:include page="../inc/inc_header.jsp">
    <jsp:param name="page" value="purchase"/>
    <jsp:param name="subPage" value="certUpload"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Certification Upload</h1>
    </div>
    <!-- The file upload form used as target for the file upload widget -->
    <form id="fileupload" action="certUpload/FileUploadServlet" method="POST" enctype="multipart/form-data">
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="span7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="icon-plus icon-white"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="icon-upload icon-white"></i>
                    <span>Start upload</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="icon-ban-circle icon-white"></i>
                    <span>Cancel upload</span>
                </button>
                <button type="button"  class="btn btn-danger delete">
                    <i class="icon-trash icon-white"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" class="toggle">
            </div>
            <!-- The global progress information -->
            <div class="span5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div class="progress progress-success progress-striped active">
                    <div class="bar" style="width:0%;"></div>
                </div>
                <!-- The extended global progress information -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The loading indicator is shown during file processing -->
        <div class="fileupload-loading"></div>
        <br>
        
        <div id="listofpreviousFiles">
	        <table class="table table-striped">
	           <tbody id="listofFiles"  class="files" data-target="#modal-gallery" data-toggle="modal-gallery">
	            <thead><b>Previous Uploaded Data</b></thead>
	        </table>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody> <thead><b>Currently Uploaded Data</b></thead></table>
    </form>

</div>


</body> 
</html>

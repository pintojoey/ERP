<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>IIIT KOTA | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
  
  
  <script src="http://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>
  
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">
 
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
 

  
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@ include file="header.jsp" %>
 <!-- Left side column. contains the logo and sidebar -->
 <%@ include file="main-sidebar.jsp" %>
 
   <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Student
        <small>Home</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#" class="active"><i class="fa fa-dashboard"></i>Home</a></li>
        
      </ol>
    </section>

    <!-- Main content -->
	
	
	

   <%
   
   String formpara= request.getParameter("formname");
   

  
    String fnpara= request.getParameter("fn");
        if(fnpara==null){
        	fnpara="";
        }else{
        	fnpara=fnpara+",";
        }
    

    String lnpara= request.getParameter("ln");
    if(lnpara==null){
    	lnpara="";
    }else{
    	lnpara=lnpara+",";
    }
    
    String ftnpara=request.getParameter("ftn");
         
    if(ftnpara==null){
    	ftnpara="";
    }else{
    	ftnpara=ftnpara+",";
    }
    
	 String mnpara= request.getParameter("mn");
	 if(mnpara==null){
     	mnpara="";
     }else{
     	mnpara=mnpara+",";
     }
	 

	 String fields= request.getParameter("fields");
	 
	 String allFields;
	 
	 if(fields==""){
		 allFields=fnpara+lnpara+ftnpara+mnpara;
	    }else{
	    	allFields=fnpara+lnpara+ftnpara+mnpara+fields+',';
	    }
		 
	 
	// out.println(fields);
	 
	  // allFields=fnpara+lnpara+ftnpara+mnpara+fields;
	  
	  
	
	%>
	
	
	
	  <form  method="post" action="../RetrieveForms">
	  
	  <input style="display:none;" type="text" name="formname" value="<%=formpara%>"/>
	  
	  <input style="display:none;" type="text" name="allfield" value="<%=allFields%>"/>
	   
	  <textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>   	
		
        <script>
        CKEDITOR.config.height='420mm';
       CKEDITOR.config.width='297mm';
	   
	    CKEDITOR.on('instanceReady', function() {;
	    var iframe=document.getElementsByClassName("cke_wysiwyg_frame cke_reset")[0].contentDocument;
	    iframe.body.style.backgroundColor = "#b0b0b0";
	    iframe.body.style.width='297mm';
	    iframe.body.style.height='420mm';
	    iframe.body.style.margin='0mm';
	    iframe.body.style.padding='5mm';
	    
	
		
	});
	    CKEDITOR.replace( 'editor1' );
	   // CKEDITOR.instances['editor1'].resize( 900, 300 );
	    
	    
         </script>
			
		
		<input type="submit" value="submit" name="submit"/>
		
		</form>
		
		
	
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@ include file="footer.jsp" %>
  <!-- Control Sidebar -->
  <%@ include file="control-sidebar.jsp" %>
 </div>
<!-- ./wrapper -->

<!-- jQuery 2.1.4 -->
<script src="../plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="../bootstrap/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/app.min.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="../dist/js/demo.js"></script>
</body>
</html>
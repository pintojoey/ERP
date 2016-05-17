<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@page import="utilities.ServletUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="users.Student"%>
<%@page import="postgreSQLDatabase.registration.Query"%>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<title>IIITK Challan</title>
<style>
@page { size: A4 landscape;}
</style>
</head>
<body>
    <%Student student=Query.getStudentProfile(Long.parseLong(request.getParameter("erpId").toString())); 
    Date date=new Date();
							SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
							String current_date=format.format(date);
							%>
		
			<table border="1">
			<tbody>
			<tr>
				<td>
				<div class="col-md-4">
				
					<div class="row" style="font-size:80%;">
					<table><tr><td><img  alt="img" height="65px" width="65px"  src="<%=ServletUtils.getBaseUrl(request)%>/image/sbi.png" class="image pull-right"/>
						</td><td><p class="text-center" align="center"><b>Indian Institute Of Information Technology Kota(Rajasthan)</b><br/>(Bank Copy)</p></td>
						<td><img  alt="img"  height="65px" width="65px" src="<%=ServletUtils.getBaseUrl(request)%>/image/iiitkota-logo.png"/></td>
						</tr></table>
						
					</div>
					
					<div class="row invoice-info" style="font-size:80%;align-items:center;">
						<div class="invoice-col" align="center" >
							<b>Category:</b> <%=student.getCategory() %>
							<b>Gender(M/F):</b> <%=student.getGender() %>
							
							
								
                            
						</div>
						
						<div class="invoice-col" align="center">
							<b>Student ID:</b> <%=student.getStudent_id() %><br/>
							
							
							<b>Date:</b> <%=current_date %>
							<p align="center" style="text-decoration: underline;"><b>Institue Fee Detail for 2016-2017 Odd Semester</b></p>
						</div>
					</div>
					
					<div class="row">
						<div class="table-responsive">
							<table class="table table-striped" style="font-size:80%" >
								<tbody >
									<tr>
										<td><b>Account To Credit</b></td>
										<td>32896056885 </td>
									</tr>
									<tr>
										<td><b>Institute Code</b></td>
										<td>CSE</td>
									</tr>
									<tr>
										<td><b>Student Name</b></td>
										<td><%=student.getName() %></td>
									</tr>
									<tr>
										<td><b>Roll No.</b></td>
										<td><%=student.getStudent_id() %></td>
									</tr>
									<tr>
										<td><b>Class/Program</b></td>
										<td><%=student.getProgram_allocated() %></td>
									</tr>
									<tr>
										<td><b>Section/Semester</b></td>
										<td>6th</td>
									</tr>
									<tr>
										<td><b>Amount</b></td>
										<td>26300</td>
									</tr>
									<tr>
										<td><b>Amount In Words</b></td>
										<td>Twenty six thousand and three hundred</td>
									</tr>
									<tr>
										<td><b>DD/Cheque No.</b></td>
										<td>965456631321</td>
									</tr>
									<tr>
										<td><b>Bank</b></td>
										<td>32896056885 </td>
									</tr>
									<tr>
										<td><b>DD/Cheque Date</b></td>
										<td>26/12/2015</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="row" style="font-size:80%">
						<h4 class="text-center">(To be filled by bank)</h4>
						Branch SOL ID <span class="pull-right">____________________</span><br/>
						Branch Transaction ID <span class="pull-right">____________________</span><br/><br/><br/><br/>
						
						<table><tr><td>Seal &amp; Stamp of SBI Bank</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Signature of Depositor</td></tr>
						</table>
						<h6>If Incorrect fee is deposited, student Even Semester registration may be cancelled/terminated. Even Semester Registration is Subject to fulfilling eligibility criteria</h6>
					</div>
				</div>
				
				</td>
				
				<td>
				<div class="col-md-4">
				
					<div class="row" style="font-size:80%;">
					<table><tr><td><img height="65px" width="65px"  alt="img"  src="<%=ServletUtils.getBaseUrl(request)%>/image/sbi.png" class="image pull-right"/>
						</td><td><p class="text-center" align="center"><b>Indian Institute Of Information Technology Kota(Rajasthan)</b><br/>(Institute Copy)</p></td>
						<td><img height="65px" width="65px"  alt="img"  src="http://localhost:8080/erp/image/iiitkota-logo.png"/></td>
						</tr></table>
						
					</div>
					
					<div class="row invoice-info" style="font-size:80%;align-items:center;">
						<div class="invoice-col" align="center" >
							<b>Category:</b> <%=student.getCategory() %>
							<b>Gender(M/F):</b> <%=student.getGender() %>
							
							
								
                            
						</div>
						
						<div class="invoice-col" align="center">
							<b>Student ID:</b> <%=student.getStudent_id() %><br/>
							
							
							<b>Date:</b> <%=current_date %>
							<p align="center" style="text-decoration: underline;"><b>Institue Fee Detail for 2016-2017 Odd Semester</b></p>
						</div>
					</div>
					
					<div class="row">
						<div class="table-responsive">
							<table class="table table-striped" style="font-size:80%" >
								<tbody >
									<tr>
										<td><b>Account To Credit</b></td>
										<td>32896056885 </td>
									</tr>
									<tr>
										<td><b>Institute Code</b></td>
										<td>CSE</td>
									</tr>
									<tr>
										<td><b>Student Name</b></td>
										<td><%=student.getName() %></td>
									</tr>
									<tr>
										<td><b>Roll No.</b></td>
										<td><%=student.getStudent_id() %></td>
									</tr>
									<tr>
										<td><b>Class/Program</b></td>
										<td><%=student.getProgram_allocated() %></td>
									</tr>
									<tr>
										<td><b>Section/Semester</b></td>
										<td>6th</td>
									</tr>
									<tr>
										<td><b>Amount</b></td>
										<td>8800</td>
									</tr>
									<tr>
										<td><b>Amount In Words</b></td>
										<td>Eight thousand and eight hundred</td>
									</tr>
									<tr>
										<td><b>DD/Cheque No.</b></td>
										<td>965456631321</td>
									</tr>
									<tr>
										<td><b>Bank</b></td>
										<td>32896056885 </td>
									</tr>
									<tr>
										<td><b>DD/Cheque Date</b></td>
										<td>26/12/2015</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="row" style="font-size:80%">
						<h4 class="text-center">(To be filled by bank)</h4>
						Branch SOL ID <span class="pull-right">____________________</span><br/>
						Branch Transaction ID <span class="pull-right">____________________</span><br/><br/><br/><br/>
						
						<table><tr><td>Seal &amp; Stamp of SBI Bank</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Signature of Depositor</td></tr>
						</table>
						<h6>If Incorrect fee is deposited, student Even Semester registration may be cancelled/terminated. Even Semester Registration is Subject to fulfilling eligibility criteria</h6>
					</div>
				</div>
				
				</td>
				<td>
				<div class="col-md-4">
				
					<div class="row" style="font-size:80%;">
					<table><tr><td><img height="65px" width="65px"  alt="img"  src="<%=ServletUtils.getBaseUrl(request)%>/image/sbi.png" class="image pull-right"/>
						</td><td><p class="text-center" align="center"><b>Indian Institute Of Information Technology Kota(Rajasthan)</b><br/>(Student Copy)</p></td>
						<td><img height="65px" width="65px"  alt="img"  src="http://localhost:8080/erp/image/iiitkota-logo.png"/></td>
						</tr></table>
						
					</div>
					
					<div class="row invoice-info" style="font-size:80%;align-items:center;">
						<div class="invoice-col" align="center" >
							<b>Category:</b> <%=student.getCategory() %>
							<b>Gender(M/F):</b> <%=student.getGender() %>
							
							
								
                            
						</div>
						
						<div class="invoice-col" align="center">
							<b>Student ID:</b> <%=student.getStudent_id() %><br/>
							
							
							<b>Date:</b> <%=current_date %>
							<p align="center" style="text-decoration: underline;"><b>Institue Fee Detail for 2016-2017 Odd Semester</b></p>
						</div>
					</div>
					
					<div class="row">
						<div class="table-responsive">
							<table class="table table-striped" style="font-size:80%" >
								<tbody >
									<tr>
										<td><b>Account To Credit</b></td>
										<td>32896056885 </td>
									</tr>
									<tr>
										<td><b>Institute Code</b></td>
										<td>CSE</td>
									</tr>
									<tr>
										<td><b>Student Name</b></td>
										<td><%=student.getName() %></td>
									</tr>
									<tr>
										<td><b>Roll No.</b></td>
										<td><%=student.getStudent_id() %></td>
									</tr>
									<tr>
										<td><b>Class/Program</b></td>
										<td><%=student.getProgram_allocated() %></td>
									</tr>
									<tr>
										<td><b>Section/Semester</b></td>
										<td>6th</td>
									</tr>
									<tr>
										<td><b>Amount</b></td>
										<td>8800</td>
									</tr>
									<tr>
										<td><b>Amount In Words</b></td>
										<td>Eight thousand and eight hundred</td>
									</tr>
									<tr>
										<td><b>DD/Cheque No.</b></td>
										<td>965456631321</td>
									</tr>
									<tr>
										<td><b>Bank</b></td>
										<td>32896056885 </td>
									</tr>
									<tr>
										<td><b>DD/Cheque Date</b></td>
										<td>26/12/2015</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="row" style="font-size:80%">
						<h4 class="text-center">(To be filled by bank)</h4>
						Branch SOL ID <span class="pull-right">____________________</span><br/>
						Branch Transaction ID <span class="pull-right">____________________</span><br/><br/><br/><br/>
						
						<table><tr><td>Seal &amp; Stamp of SBI Bank</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Signature of Depositor</td></tr>
						</table>
						<h6>If Incorrect fee is deposited, student Even Semester registration may be cancelled/terminated. Even Semester Registration is Subject to fulfilling eligibility criteria</h6>
					</div>
				</div>
				
				</td>
				</tr></tbody></table>	
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.util.*, lecture1.jdbc1.*" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %> 

<% 
int currentPage = 1; 
int pageSize = 10;
String pg = request.getParameter("pg");
if (pg != null) currentPage = Integer.parseInt(pg); 

String srchText = request.getParameter("srchText"); 
if (srchText == null) srchText = "";
List<User> list = UserDAO.findByName(srchText, currentPage, pageSize); 
int recordCount = UserDAO.count(srchText);

%> 
<!DOCTYPE html> 
<html> 
<head>   
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">   
<meta name="viewport" content="width=device-width, initial-scale=1">   
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
     <style>      
      body { font-family: 굴림체; }     
        thead th { background-color: #eee; }     
          table.table { width: 700px; }  
           </style> 
           </head> 
           <body> 
           <div class="container"> 
           <h1>사용자 목록</h1> 
           
           <form class="form-inline">   
           <div class="form-group">     
           <label>이름</label>     
           <input type="text" class="form-control" name="srchText" value="<%= srchText %>"             
           placeholder="검색조건" />   
           </div>   
           <button type="submit" class="btn btn-primary">조회</button> 
           </form>
           
           <table class="table table-bordered table-condensed">     
           <thead>        
            <tr>            
             <th>id</th>        
             <th>이름</th>            
             <th>학과</th>             
             <th>사용자유형</th>         
</tr>    
 </thead>     
 <tbody>         
 <% for (User user : list) { %>           
   <tr>                 
   <td><%= user.getId() %></td>                
   <td><%= user.getName() %></td>           
   <td><%= user.getDepartmentName() %></td>           
   <td><%= user.getUserType() %></td>           
         </tr>         
            <% } %>     
                         </tbody> 
                         </table> 
    <my:pagination pageSize="<%= pageSize %>" recordCount="<%= recordCount %>" queryStringName="pg" />
           
                         </div> 
                         </body> 
                         </html>



<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Simple GSP page</title><meta name="layout" content="main" />
  <script type="text/javascript">
    $(document).ready(function(){
      $("#loginForm").validate();
    })

  </script>

    <style>
   .error{
     color:red;
     border-color:red    ;


    }
   </style>


</head>
  <body>

    <div class="logout"></div>
    <div style="margin:auto;width:700px">


   </div>
    <div style="float:left; margin-right:-80px;">


  <form method = POST   action="${createLink(controller:'login',action:'loginHandler')}" id="loginForm">

    <div style="margin:auto;width:400px">
     <table style="border:2px solid blue;height:110px;width:250px;padding-left:10px;padding-top:15px;
                margin-left:15px;margin-top:2px">
       <div style="padding-left:20px;padding-bottom:5px ">
    <strong><g:if test="${flash.message}">${flash.message}</g:if>
            <g:else> ${flash.message="Please Login to access the Application !!"} </g:else>
    </strong>
   </div>
       <tr>
           <td style="font-weight:bold">  Email </td><td><input type = 'text' name ="email" class="required email"/> </td>
        </tr>
       <tr>
         <td style="font-weight:bold">  Password   </td><td><input type ='password' name = "password" class="required"/></td>

       </tr>
       <tr>
           <td style="text-align:right;">  <input type = 'submit' name = "submit" style="background-color:ThreeDHighlight;"/> </td>
           <td style="text-align:center;"> <input type = 'reset' name = "reset" style="background-color:ThreeDHighlight;"/></td>
     </tr>
   </table>

  </form>
  <g:form action="register">
      <br><span style="margin-left:60px;"><strong>New User? &nbsp;   Click to Register</strong> </span> &nbsp;
      <input type = 'submit' name = "submit" value="Register" style="background-color:ThreeDHighlight;"/>

    </g:form>
    </div>
  </div>

  </body>
</html>
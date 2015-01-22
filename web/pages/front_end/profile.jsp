<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Profile - TeamChat</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">

    <!-- Custom scripts for this template -->
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="/js/jquery.scrollTo-1.4.3.1-min.js" type="text/javascript"></script>
    <script src="/js/jquery.easing-1.3.min.js" type="text/javascript"></script>
    <script src="/js/default.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/pages/js/signin.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
</head>

<body>

  <!-- Header Section -->
  <div id="headerSection">
    <div class="container">
      <div class="span3 logo"><img src="../../img/logo1.png" alt="" /></div>
      <div class="navbar">
        <div class="nav-collapse">
          <ul class="nav nav-pills">
            <li class="dropdown all-camera-dropdown">
              <a class="dropdown-toggle username" data-toggle="dropdown" href="#">
                <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" style="width: 35px; height: 35px;">
                <% username %>
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a data-toggle="tab" href="/profile"><span class="glyphicon glyphicon-user"> Profile</span></a></li>
                <li><a data-toggle="tab" href="/settings"><span class="glyphicon glyphicon-cog"> Settings</span></a></li>
                <li><a data-toggle="tab" href="/quit"><span class="glyphicon glyphicon-log-out"> Quit</span></a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>

<div class="desk-container">
    <h1>Edit Profile</h1>
    <hr>
  <div class="row">
      <!-- left column -->
      <div class="col-md-3">
        <div class="text-center">
          <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" class="avatar img-circle" alt="avatar" style="width: 100px; height: 100px;">
          <h6>Upload a different photo...</h6>
          
          <input type="file" class="form-control">
        </div>
      </div>
      
      <!-- edit form column -->
      <div class="col-md-7 personal-info">
        <h4>Personal info</h4>
        
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-lg-3 control-label">First name:</label>
            <div class="col-lg-8">
              <input class="form-control" type="text" placeholder="firstname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Last name:</label>
            <div class="col-lg-8">
              <input class="form-control" type="text" placeholder="lastname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
              <input class="form-control" type="text" placeholder="example@gmail.com">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Username:</label>
            <div class="col-md-8">
              <input class="form-control" type="text" placeholder="username">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Password:</label>
            <div class="col-md-8">
              <input class="form-control" type="password" placeholder="******">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Confirm password:</label>
            <div class="col-md-8">
              <input class="form-control" type="password" placeholder="******">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label"></label>
            <div class="col-md-8">
              <input type="button" class="btn btn-primary" value="Save Changes">
              <span></span>
              <input type="reset" class="btn btn-default" value="Cancel">
            </div>
          </div>
        </form>
      </div>
  </div>
<hr>
  </div>
</body>


</html>
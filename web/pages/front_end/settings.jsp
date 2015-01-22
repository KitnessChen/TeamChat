<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Settings - TeamChat</title>

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

</body>


</html>
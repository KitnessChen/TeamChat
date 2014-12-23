<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign in - TeamChat</title>

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
<div id="headerSection">
    <div class="container">
        <div class="span3 logo"><img src="/img/logo.png" alt=""/></div>
        <div class="navbar">
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="index.html">About Us ?</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container main-container">
        <form class="form-signin" role="form">
            <h4 class="form-signin-heading">Please Sign In</h4>
            <input class="form-control" type="text" placeholder="Username" id="username" required autofocus>
            <input class="form-control" type="text" placeholder="Password" id="password" required>

            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me(Doing)
                </label>
            </div>
            <button class="btn btn-lg btn-block btn-primary" type="button">Sign in</button>
            <div class="action-wrapper clearfix">
                <a href="/forgot" class="forget">Forgot Password��</a>
                <a href="/signup" class="signup pull-right">Sign up</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>

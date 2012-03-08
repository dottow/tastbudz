<%@ include file="taglibs.jsp"%>

<head>
    <meta charset="utf-8">
    <title><fmt:message key="webapp.name"/></title>
    <meta name="home" content="home"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="webapp.description"/>">
    <meta name="author" content="<fmt:message key="webapp.author"/>">
    <!-- Styles -->
    <link href="styles/tastbudz.css" rel="stylesheet">
    <link href="styles/bootstrap.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <link href="styles/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- icons -->
    <link rel="shortcut icon" href="images/favicon.ico">
</head>

<body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/" id="tastbudz_logo"></a>
          <ul class="nav pull-right">
            <li><a href="signup">Join</a></li>
            <li><a href="signin">Sign in</a></li>
            <li>
            <form name="fbsignin" action="connect/facebook" method="post">
	        <input type="hidden" name="scope" value="email,publish_stream,user_photos,offline_access" />
                <a href="javascript:document.fbsignin.submit()" id="fb_connect"></a>
            </form>
          </ul>
        </div>
      </div>
    </div>

    <div class="container">
 
    <div class="search-main">
        <form action="/search" method="post">
            <input type="text" name="query" placeholder="<fmt:message key="search.placeholder"/>">
            <input type="submit" style="display:none">
        </form>
    </div>

    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap-transition.js"></script>
    <script src="js/bootstrap-alert.js"></script>
    <script src="js/bootstrap-modal.js"></script>
    <script src="js/bootstrap-dropdown.js"></script>
    <script src="js/bootstrap-scrollspy.js"></script>
    <script src="js/bootstrap-tab.js"></script>
    <script src="js/bootstrap-tooltip.js"></script>
    <script src="js/bootstrap-popover.js"></script>
    <script src="js/bootstrap-button.js"></script>
    <script src="js/bootstrap-collapse.js"></script>
    <script src="js/bootstrap-carousel.js"></script>
    <script src="js/bootstrap-typeahead.js"></script>
</body>


<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails Event Planner App"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="zelifcamicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <div class="container-fluid">
        <a class="navbar-brand" href="/#"><asset:image src="Zelifcam.webp" alt="Zelifcam Logo"/></a>
    </div>
</nav>

<g:layoutBody/>

<div class="footer" role="contentinfo">

</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>

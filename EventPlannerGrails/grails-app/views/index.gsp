<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Event Planner</title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <h1>Welcome!</h1>
            <div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <div class="my-3">
                        <g:link controller="event" action="create" class="btn btn-success">Create New Event</g:link>
                        <g:link controller="event" action="index" class="btn btn-outline-primary ml-2">View All Events</g:link>
                    </div>
                </ul>
            </div>
        </section>
    </div>
</div>

</body>
</html>

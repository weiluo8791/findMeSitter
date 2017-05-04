<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">Home </a>
        </div>
        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
            <ul class="nav navbar-nav">
                <li>
                    <g:link controller="dayCareCenter">Day Care Center</g:link>
                </li>
                <li>
                    <g:link controller="review">Reviews</g:link>
                </li>
                <li>
                    <g:link controller="reviewer">Reviewer</g:link>
                </li>


                <sec:ifLoggedIn>
                    <li>
                        <a>Logged in as <sec:username/></a>
                    </li>
                    <li><a>
                        <g:form controller="logout" type="POST"><input class="btn btn-link" style="color: white !important;" type="submit" value="logout" /></g:form>
                    </a>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li>
                        <g:link controller="login" action="index">Login</g:link>
                    </li>
                </sec:ifNotLoggedIn>

            </ul>
        </div>
    </div>
</nav>
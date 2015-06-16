<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Dashboard</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <spring:url value="/resources/styles/main.css" var="mainCss" />
    <spring:url value="/resources/styles/bootstrap.min.css" var="bootstrapCss" />
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="${mainCss}">
    <link type="text/css" rel="stylesheet" href="${bootstrapCss}">
</head>
<body>
    <div class="col-lg-8">
		<div class="panel">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-8">
						<h4 class="mbs">
							Network Performance</h4>
						<p class="help-block">
							Sed ut perspiciatis unde omnis iste natus error sit voluptatem...</p>
						<div id="area-chart-spline" style="width: 100%; height: 300px">
						</div>
					</div>
					<div class="col-md-4">
						<h4 class="mbm">
							Server Status</h4>
						<span class="task-item">CPU Usage (25 - 32 cpus)<small class="pull-right text-muted">40%</small><div
							class="progress progress-sm">
							<div role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 40%;" class="progress-bar progress-bar-orange">
								<span class="sr-only">40% Complete (success)</span></div>
						</div>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<spring:url value="/resources/script/jquery-1.10.2.min.js" var="jqueryMinJs" />
    <spring:url value="/resources/script/jquery-ui.js" var="jqueryUiJs" />
    <spring:url value="/resources/script/bootstrap.min.js" var="bootstrapMinJs" />
    <spring:url value="/resources/script/jquery.slimscroll.js" var="jquerySlimscrollJs" />
    <spring:url value="/resources/script/custom.min.js" var="customMinJs" />
    <spring:url value="/resources/script/jquery.flot.js" var="jqueryFlotJs" />
    <spring:url value="/resources/script/jquery.flot.categories.js" var="jqueryFlotCategoriesJs" />
    <spring:url value="/resources/script/jquery.flot.tooltip.js" var="jqueryFlotTooltipJs" />
    <spring:url value="/resources/script/jquery.flot.spline.js" var="jqueryFlotSplineJs" />
    <spring:url value="/resources/script/zabuto_calendar.min.js" var="zabutoCalendarMinJs" />
    <spring:url value="/resources/script/index.js" var="indexJs" />
    <spring:url value="/resources/script/main.js" var="mainJs" />

	<script src="${jqueryMinJs}"></script>
    <script src="${jqueryUiJs}"></script>
    <script src="${bootstrapMinJs}"></script>
    <script src="${jquerySlimscrollJs}"></script>
    <script src="${customMinJs}"></script>
    <!--label-->
    <script src="${jqueryFlotJs}"></script>
    <script src="${jqueryFlotCategoriesJs}"></script>
    <script src="${jqueryFlotTooltipJs}"></script>
    <!--line-->
    <script src="${jqueryFlotSplineJs}"></script>
    <!--dot-->
    <script src="${zabutoCalendarMinJs}"></script>
    <script src="${indexJs}"></script>
    <!--CORE JAVASCRIPT-->
    <script src="${mainJs}"></script>
</body>
</html>

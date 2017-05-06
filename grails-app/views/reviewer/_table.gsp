<asset:stylesheet src="daycare.css"></asset:stylesheet>
<asset:javascript src="application.js"></asset:javascript>

<table id="reviewerTable" class="table  table-striped table-hover">
    <thead>
    <tr>
        <th>Name</th>
        <th>Location</th>
        <th>Last Review</th>
        <th>Total Review</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${reviewerList}" var="reviewer">
        <tr>
            <td>
                <a href="#" data-reviewer-id="${reviewer.id}" data-toggle="modal" data-target="#reviewerModal">${reviewer.fullName}</a>
            </td>
            <td>${reviewer.city}, ${reviewer.state}</td>
            <td>${reviewer.dateOfLatestReview}</td>
            <td>${reviewer.totalReviewCount}</td>
        </tr>
    </g:each>
    </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="reviewerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="reviewerModalLabel">Day Care Center</h4>
            </div>
            <div class="modal-body">
                <div id="reviewerModalContent">
                    <g:render template="show" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<asset:javascript src="reviewer/reviewerTable.js"></asset:javascript>


<asset:javascript src="application.js"></asset:javascript>
<table id="dayCareTable" class="table  table-striped table-hover">
    <thead>
    <tr>
        <th>Name</th>
        <th>City</th>
        <th>State</th>
        <th>Phone</th>
        <th>Capcity</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${dayCareCenterList}" var="daycare">
        <tr>
            %{--<td>${daycare.name}</td>--}%
            <td>
                <a href="#" data-daycare-id="${daycare.id}" data-toggle="modal" data-target="#daycareModal">${daycare.name}</a>
            </td>
            <td>${daycare.city}</td>
            <td>${daycare.state}</td>
            <td>${daycare.phoneNumber}</td>
            <td>${daycare.centerCapcity}</td>
        </tr>
    </g:each>
    </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="daycareModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="daycareModalLabel">Day Care Center</h4>
            </div>
            <div class="modal-body">
                <div id="daycareModalContent">
                    <g:render template="show" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<asset:javascript src="daycarecenter/daycareTable.js"></asset:javascript>



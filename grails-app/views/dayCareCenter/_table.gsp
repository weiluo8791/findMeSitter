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
            <td>${daycare.name}</td>
            <td>${daycare.city}</td>
            <td>${daycare.state}</td>
            <td>${daycare.phoneNumber}</td>
            <td>${daycare.centerCapcity}</td>
        </tr>
    </g:each>
    </tbody>
</table>



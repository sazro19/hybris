<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<title>Tour Details</title>
<body>
<h1>Tour Details</h1>
<p>Producer: ${tour.producer.firstName} ${tour.producer.lastName}</p>
<p>Schedule:</p>
<table>
    <tr><th>Venue</th><th></th><th>Date</th><th>Days Until</th></tr>
    <c:forEach var="concert" items="${tour.concerts}">
        <tr><td>${concert.venue}</td><td>${concert.type}</td><td><fmt:formatDate pattern="dd MMM yyyy" value="${concert.date}" /></td><td>${concert.countDown}</td></tr>
    </c:forEach>
</table>
<a href="../bands">Back to Band List</a>
</body>
</html>
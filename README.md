<h1>Mediscreen App & Microservices</h1>

Backend : Spring Boot Java 1.8
Frontend : React.JS
Maven
Docker
MySQL / MongoDB!

<h2> Diagram </h2>

https://github.com/Nico-GS/Mediscreen/blob/rapport-sprint3/Sch%C3%A9maApp.PNG



<h2> Docker </h2>

Microservice "Patient" : <code>docker push lornmalvo/ms-patient:tagname</code>

Microservice "Notes" :   <code>docker push lornmalvo/ms-notes:tagname</code>

Microservice "Report" :  <code>docker push lornmalvo/ms-rapport:tagname</code>

Microservice "front" :   <code>docker push lornmalvo/front:tagname</code>

Link Docker Hub :

https://hub.docker.com/repository/docker/lornmalvo/ms-patient
<br/>
https://hub.docker.com/repository/docker/lornmalvo/ms-notes
<br/>
https://hub.docker.com/repository/docker/lornmalvo/ms-rapport
<br/>
https://hub.docker.com/repository/docker/lornmalvo/front

<h2> MySQL </h2>

https://github.com/Nico-GS/Mediscreen/blob/rapport-sprint3/mediscreen_patient.sql

<h2>Launch project in local with IntelliJ / Eclipse and MySQL & MongoDB</h2>

<code>
docker build -t ms-patient .
</code>
<br/>
<code>
docker build -t ms-notes .
</code>
<br/>
<code>
docker build -t ms-rapport .
</code>
<br/>
<code>
docker build -t ms-front .
</code> 
<br/>
<br/>

And for launch Docker Compose, at the root of project :

<code>docker compose up</code>

<h2>Endpoints Microservices & Documentations Swagger UI</h2>

Patient : http://localhost:8081/swagger-ui/index.html#
<br/>
Notes : http://localhost:8082/swagger-ui/index.html#
<br/>
Rapport : http://localhost:8083/swagger-ui/index.html#

The UI is available at : http://localhost



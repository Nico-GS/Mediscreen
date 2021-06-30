<h1>Mediscreen App & Microservices</h1>

Medical management application: patients, notes and health report.

Java / Spring Boot / ReactJS / Docker


<h2> Diagram </h2>

<p align="center">
  <img src="https://github.com/Nico-GS/Mediscreen/blob/rapport-sprint3/Sch%C3%A9maApp.PNG" />
</p>

<h2>Launch project in local with IntelliJ / Eclipse and MySQL & MongoDB</h2>

Requirements : <br/>
  ![Java Version](https://img.shields.io/badge/Java-1.8.x-red)
  ![Maven Version](https://img.shields.io/badge/React.JS-17.0.2-blue)
  ![ReactJS](https://img.shields.io/badge/Maven-3.6.3-blue)
  ![Maven Version](https://img.shields.io/badge/Maven-3.6.3-blue)
  ![MySQL Version](https://img.shields.io/badge/MySQL-8.x-cyan)
  ![MongoDB Version](https://img.shields.io/badge/MongoDB-4.x-green)
  ![Docker Version](https://img.shields.io/badge/Docker-20.10.2-cyan)
  ![J-Unit Version](https://img.shields.io/badge/JUnit-5.7.0-orange)
  ![TomCat](https://img.shields.io/badge/TomCat-9.0.41-brightgreen)
  ![Maven Version](https://img.shields.io/badge/Maven-4.0.0-blue)

1. Download the project or import it with git
2. Open Mediscreen with IntelliJ or Eclipse

<h4> MySQL </h4>

Before, create a MySQL database called "mediscreen".
To import the database into MySQL you can use the MySQL Workbench tool or by command line :
<code>mysql -u [user] -p mediscreen < mediscreen_patient.sql</code>

https://github.com/Nico-GS/Mediscreen/blob/rapport-sprint3/mediscreen_patient.sql
 
  
1 - Build each image using the DockerFile included in each micro-service folder : If you want to avoid editing the docker-compose.yml, use those commands, or edit the compose file according the name you gave to the images. Don't forget to be in the right folder to launch the command : 
  
```bash
docker build -t ms-patient .
docker build -t ms-notes .
docker build -t ms-rapport .
docker build -t ms-front .
```

2 - And for launch Docker Compose, at the root of project, this file inject using environment variable and the cross origin url. It also launch mysql 8 and mongoDb 4 :

```bash
docker compose up
```
  
3 - Once the microservices with Docker are launched, the application is accessible at: http://localhost
  
  
<h2> Docker Hub </h2>
  
Microservices are also available on Docker Hub

For MySQL : <code>docker pull mysql</code>
<br/>
For MongoDB : <code>docker pull mongo</code>

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

<h2>Endpoints Microservices & Documentations Swagger UI</h2>

Patient : http://localhost:8081/swagger-ui/index.html#
<br/>
Notes : http://localhost:8082/swagger-ui/index.html#
<br/>
Rapport : http://localhost:8083/swagger-ui/index.html#

The UI is available at : http://localhost
  
  



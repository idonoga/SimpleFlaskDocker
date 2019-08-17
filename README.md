# simpleflask-docker-v1
Simple Flask Docker 
<BR>
The jenkins job is pulling the repository ,building and running 2 containers.
<BR>
The first one is a simple flask container that returns the active containers list
<BR>
The second container is a ngnix container used as a reversed proxy to the flask website.
<BR>
After the containers are running, the job checks if the website is accessible via ngnix


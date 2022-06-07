## Docker
Basic commands to run a container

###build
"docker build -t inrikys/imagename . " 
###run
"docker run --name imagename -p 8081:8080 inrikys/imagename "
###stop 
"docker stop imagename"

###upload to dockerhub
"docker login" <br>
"docker push imagename"

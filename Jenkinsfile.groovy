
environment {
    registry = "idonoga/flask"
    registryCredential = 'dockerhub'
    dockerImage = ''
}

pipeline {
    agent any
    
    stages {
       stage('initializing-workspace') {
            steps{
                echo "Installing Dependencies"
                cleanWs()
                sh """
                git clone -b master https://idonoga:${GIT_PASSWORD}@github.com/idonoga/simpleflask-docker-v1.git .
                """
                
            }
        }
        
        stage('flask-container-build') {
            steps{
               echo "Building flask container"
               sh """
                docker build -t flask:latest ./mydockerflask/
                IMAGE_ID=\$(docker images --filter=reference="flask" --format "{{.ID}}")
                docker tag \$IMAGE_ID idonoga/flask:firsttry
                docker push idonoga/flask
                """

            }
        }
        stage('ngnix-container-build') {
            steps{
                echo "Building ngnix container"
            sh """
                docker build -t flask:latest ./ngnixdocker/
                IMAGE_ID=\$(docker images --filter=reference="flask" --format "{{.ID}}")
                docker tag \$IMAGE_ID idonoga/flask:ngnixserver
                docker push idonoga/flask
                """
                
            }
        }
        stage('run-containers') {
            steps{
                echo "Running the two containers"
            sh """
                docker run -d --name flaskcontainer -v /var/run/docker.sock:/var/run/docker.sock idonoga/flask:firsttry
                docker run -d --name ngnixproxy -p 80:80 --link=flaskcontainer idonoga/flask:ngnixserver
                
                """
                script{
                def RESPONSE = sh(script: "curl -s --head  --request GET http://localhost | grep '200 OK'", returnStdout: true)
                        echo "${RESPONSE}"
                    if(${RESPONSE} == 'HTTP/1.1 200 OK')
                    {
                        echo "WEBSITE IS RUNNING"
                    }
                    else
                    {
                        echo "not working"
                    }
                  
                }
                  
                
                
            }
        }
    }
}


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
                docker run -v /var/run/docker.sock:/var/run/docker.sock flask:latest
                """
                def response = sh(script: 'curl -s --head  --request GET http://localhost | grep '200 OK'', returnStdout: true)
                if(response != 'HTTP/1.1 200 ok')
                {
                    exit 1
                }
                
            }
        }
    }
}


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
                IMAGE_ID=%24(docker images --filter=reference="flask" --format "{{.ID}}")
                docker tag %24IMAGE_ID idonoga/flask:firsttry
                docker push idonoga/flask
                """

            }
        }
        stage('ngnix-container-build') {
            steps{
            sh """
                
                """
                
            }
        }
        stage('run-containers') {
            steps{
            sh """
                
                """
                
            }
        }
    }
}

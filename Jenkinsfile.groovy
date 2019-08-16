
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
                
                script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
                 }
                
               
                
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


pipeline {
    agent any
    
    stages {
       stage('initializing-workspace') {
            steps{
                cleanWs()
                sh """
                echo Installing Dependencies
                yum install -y docker git
                service docker start
                docker stop $(docker ps -q)
                docker rm $(docker ps -q -a)
                git clone -b master https://idonoga:${GIT_PASSWORD}@github.com/idonoga/simpleflask-docker-v1.git .
                """
                
            }
        }
        
        stage('flask-container-build') {
            steps{
                cleanWs()
                echo "Building flask container"
                sh """
                docker build -t flask:latest mydockerflask/Dockerfile
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

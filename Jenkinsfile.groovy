
pipeline {
    agent any
    
    stages {
       stage('initializing-workspace') {
            steps{
                echo "Installing Dependencies"
                cleanWs()
                sh """
                
                #yum install -y docker git
                #service docker start
                docker stop %24%28docker ps -q%29
                docker rm %24%28docker ps -q -a%29
                git clone -b master https://idonoga:${GIT_PASSWORD}@github.com/idonoga/simpleflask-docker-v1.git .
                """
                
            }
        }
        
        stage('flask-container-build') {
            steps{
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

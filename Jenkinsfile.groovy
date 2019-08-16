
pipeline {
    agent any
    cleanWs()
    stages {
        
        stage('flask-container-build') {
            steps{
                
                echo "Building flask container"
                sh """
                git clone -b master https://idonoga:${GIT_PASSWORD}@github.com/idonoga/simpleflask-docker-v1.git .
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

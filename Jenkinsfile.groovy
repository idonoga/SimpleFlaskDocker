
pipeline {
    agent any
    
    stages {
        
        stage('flask-container-build') {
            steps{
                cleanWs()
                echo "Building flask container"
                sh """
                git url : "https://github.com/idonoga/simpleflask-docker-v1.git",branch: ${BRANCH_NAME},credentialsId: "idonoga"
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

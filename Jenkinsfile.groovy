
pipeline {
    agent any
    
    stages {
        
        stage('flask-container-build') {
            steps{
                cleanWs()
                echo "Building flask container"
                echo "${BRANCH_NAME}"
                sh """
                
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

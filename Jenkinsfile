pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Test Pipeline') {
            steps {
                echo 'Pipeline started'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
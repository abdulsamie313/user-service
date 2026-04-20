pipeline {
    agent any

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
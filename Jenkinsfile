pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = 'user-service'
        IMAGE_TAG = '1.0'
    }

    stages {
        stage('Build Jar') {
            steps {
                echo 'Building application with Maven'
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image'
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }
    }
}
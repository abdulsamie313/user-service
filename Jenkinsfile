pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = 'abdulsamie313/user-service'
        IMAGE_TAG = "${BUILD_NUMBER}"
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

        stage('Push Docker Image') {
            steps {
                withCredentials([
                  usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                  )
                ]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $IMAGE_NAME:$IMAGE_TAG
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Updating Kubernetes deployment image'
                sh 'kubectl set image deployment/user-service user-service=$IMAGE_NAME:$IMAGE_TAG'
            }
        }

        stage('Verify Rollout') {
            steps {
                echo 'Checking rollout status'
                sh 'kubectl rollout status deployment/user-service'
            }
        }
    }
}
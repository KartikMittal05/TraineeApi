pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "traineeapi"
        DOCKERHUB_USER = "kartikmittal05"
        CONTAINER_NAME = "traineeapi-container"
    }

    stages {

        

        stage('Build JAR') {
            steps {
                dir('TraineeAPI') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('TraineeAPI') {
                    sh 'docker build -t traineeapi .'
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                }
            }
        }

        stage('Tag Image') {
            steps {
                sh 'docker tag traineeapi $DOCKERHUB_USER/traineeapi:latest'
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh 'docker push $DOCKERHUB_USER/traineeapi:latest'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f traineeapi-container || true
                docker run -d -p 8080:8080 --name traineeapi-container traineeapi
                '''
            }
        }
    }
}

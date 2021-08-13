def frontendImage
def backendImage
pipeline {
    agent any
    stages {

        stage('Build Backend Development Image') { 
            steps {
                script {
                    docker.withRegistry('', 'Dockerhub') {
                        backendImage = docker.build("omarlaz/backend:dev", "-f Dockerfile.dev .")
                    }
                } 
            }

            post {
                success {
                    echo 'Backend image was built successfully!!'

                }
                failure {
                    emailext body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL} <br> Stage Name: ${env.STAGE_NAME}",
                            mimeType: 'text/html',
                            subject: "ERROR CI In ${env.STAGE_NAME}: Project name -> ${env.JOB_NAME}",
                            to: 'jenkinsproject51@gmail.com'
                }
            }
        }

        stage('Run backend unit tests') { 
            steps {
                sh "docker compose up"
            }

            post {
                success {
                    echo 'Backend image was unit tested successfully!!'

                }
                failure {
                    emailext body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL} <br> Stage Name: ${env.STAGE_NAME}",
                            mimeType: 'text/html',
                            subject: "ERROR CI In ${env.STAGE_NAME}: Project name -> ${env.JOB_NAME}",
                            to: 'jenkinsproject51@gmail.com'
                }
            }
        }

        stage('Push Backend Image') { 
            steps {
                script {
                    docker.withRegistry('', 'Dockerhub') {
                        backendImage.push()
                    }
                } 
            }

            post {
                success {
                    echo 'Pushed backend image successfully!!!'
                }
                failure {
                    emailext body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL} <br> Stage Name: ${env.STAGE_NAME}",
                            mimeType: 'text/html',
                            subject: "ERROR CI In ${env.STAGE_NAME}: Project name -> ${env.JOB_NAME}",
                            to: 'jenkinsproject51@gmail.com'
                }
            }
        }
    }    
}
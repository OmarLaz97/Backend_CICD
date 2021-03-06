def frontendImage
def backendImage
pipeline {
    agent any
    stages {
        stage('Build Test') { 
            steps {
                echo 'hello World!!'
            }
        }

        stage('Build Backend Image') { 
            steps {
                script {
                    docker.withRegistry('', 'Dockerhub') {
                        backendImage = docker.build("omarlaz/backend:latest")
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
                    echo 'Pushed backend image successfully!!'
                }
                failure {
                    emailext body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL} <br> Stage Name: ${env.STAGE_NAME}",
                            mimeType: 'text/html',
                            subject: "ERROR CI In ${env.STAGE_NAME}: Project name -> ${env.JOB_NAME}",
                            to: 'jenkinsproject51@gmail.com'
                }
            }
        }

        stage('Apply Kubernetes files') {
            steps {
                withKubeConfig([credentialsId: 'apiserver', serverUrl: 'https://34.207.141.172:8443/']) {
                    sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'  
                    sh 'chmod u+x ./kubectl'
                    sh './kubectl rollout restart deployment/api-deployment'
                }
            }
        } 
    }    
}
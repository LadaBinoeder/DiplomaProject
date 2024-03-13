 pipeline {
    agent any
    stages {
        stage("Pull Latest Image") {
            steps {
                 sh "docker pull ladabinoeder/selenium-docker"
            }
        }
        stage("Start Grid") {
             steps {
                 sh "docker-compose up -d hub chrome firefox"
            }
        }
        stage("Run Test") {
             steps {
                  sh "docker-compose up -d search-module"
            }
        }
        stage("Build Jar") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage("Build Image") {
            steps {
                sh "docker build -t ladabinoeder/selenium-docker -f ./Dockerfile.txt ."
            }
        }
        stage("Push Image") {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push ladabinoeder/selenium-docker:latest"
			    }
            }
        }
    }
    post{
        always{
            archiveArtifacts artifacts 'output/**'
            sh "docker-compose down"
        }
    }
}

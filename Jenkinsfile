pipeline {
    agent any
     environment {
    PATH = "/hot/new/bin:${env.PATH}"
  }
     triggers {
         pollSCM '0 * * * *' 
    }
    stages {
        stage('checkout') {
            steps {
               git branch: 'main', credentialsId: 'd318d469-b7e3-48ca-9570-e6d6facb1e33', url: 'git@github.com:mishaelrobert/hit-web-server-project.git'
            }
            script{
                 currentBuild.description="checkout from main github branch"
            }
            echo "Checkout from main branch"
        }
        stage('build') {
            steps {
               script{
                   dockerImageName="misharo/${env.JOB_NAME}:${env.BUILD_ID}"
                   image=docker.build(dockerImageName)
                   echo "Build stage"
                 currentBuild.description="build"
               }
               }
            }
             stage('push') {
            steps {
               script{
                   sh "docker tag ${image} {application-qa} "
                   image.push()
                   echo "Push stage"
                   currentBuild.description="push to dockerhub, into qa"
               }
               }
            }
             stage('deploy') {
            steps {
               script{
                   sh "docker run -id -p 80:80 $dockerImageName"
                   echo "Deploy stage"
                   currentBuild.description="deploy"
               }
               }
            }
        }
           stage('Deploy for prod') {
            when { tag "V *" & report.success==true }
            steps {
                echo 'Deploying  because this commit is tagged and the tests went through'
                sh "docker tag ${image} {application-prod} "
                   image.push()
                   currentBuild.description="deploy into prod"
            }
                 post {
                       always {
            junit 'test-results.xml'
        }
            success {
            echo 'Whole build succeeded!'
            script{
                   currentBuild.description="Mishael, 208571018, success, pushed to production"
            }
        }
        failure {
            echo 'Build failed :('
            script{
                echo "Tests failed, deleting image and not pushing into production"
                sh 'docker system prune --all'
                currentBuild.description="Mishael, 208571018, fail"
            }
            }
        }
        }
}

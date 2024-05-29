pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew' // Base directory where Maven is installed
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Get Source Code') {
            steps {
                git branch: 'main', credentialsId: '1b0cd892-6612-4b85-b3b0-8d8f9d34d5b9', url: 'https://github.com/naufalazhar65/SELENIUM-JAVA-BDD-POM'
                echo 'Hello World'
            }
        }
        stage('Verify Maven') {
            steps {
                sh 'echo $PATH'
                sh 'mvn -v'
            }
        }
        stage('Build Code') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Run Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Publish report') {
            steps {
                cucumber buildStatus: 'UNCHANGED', customCssFiles: '', customJsFiles: '', fileIncludePattern: '**/*.json', jsonReportDirectory: '/Users/naufalazhar/Documents/NAUFAL_AZHAR/ECLIPSE/WORK_SPACE/BDDframeworkPOM/target', reportTitle: 'My Report', sortingMethod: 'ALPHABETICAL'
                testNG()
            }
        }
    }
}

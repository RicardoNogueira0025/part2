pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git credentialsId: 'rn-bitbucket-credentials', url: 'https://bitbucket.org/RicardoNogueira0025/devops-20-21-1201780/src/master/CA2/Part2/'
            }
        }
        stage('Assemble') {
            steps {
                echo 'Assembling...'
				sh 'chmod u+x gradlew'
                sh './gradlew assemble'
            }
        }
		stage('Test') {
            steps {
                echo 'Running Tests...'
                sh './gradlew test'
            }
        }
        stage('Archiving') {
            steps {
                echo 'Archiving...'
                archiveArtifacts 'build/distributions/*'
            }
        }
    }
}
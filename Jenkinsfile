pipeline {
    agent any
    environment {
        PATH_GIT_REPO="https://github.com/VijayPatil86/grocery_system_back_end_12aug.git"
        PATH_MAVEN="E:/softwares/apache-maven/apache-maven-3.9.9/bin/"
    }
    stages {
        stage ('EMPTY_WORKSPACE') {
            steps {
                cleanWs()
            }
        }
        stage ('GIT_CHECKOUT') {
            steps {
                git branch: "main", url:"${PATH_GIT_REPO}"
            }
        }
        stage ('MAVEN_CLEAN') {
            steps{
                bat "${PATH_MAVEN}mvn clean"
            }
        }
        stage ('MAVEN_PACKAGE') {
            steps {
                bat "${PATH_MAVEN}mvn package"
            }
        }
        stage ('DOCKER_BUILD') {
			agent any
			steps {
				sh 'docker build . -t grocery'
			}
		}
    }
}

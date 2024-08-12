pipeline {
    agent any
    environment {
        PATH_GIT_REPO="https://github.com/VijayPatil86/grocery_system_back_end_12aug.git"
        PATH_MAVEN="D:/softwares/apache-maven-3.8.6-bin/apache-maven-3.8.6-bin/apache-maven-3.8.6/bin/"
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
    }
}

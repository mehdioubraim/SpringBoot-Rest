node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=SpringBoot-Rest'
    }
  }
}
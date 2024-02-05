pipeline{
    agent any 
    stages{
       stage('Cleaning up Workspace') {
            steps {
               cleanWs()
            }
        }
        
        stage('Cloning Git') {
            steps {
                git branch: 'main', url: 'https://github.com/ZeinebHaraketi/Kubernetes.git'
            }
        }

        stage('Build App') {
            steps {
                sh 'mvn clean package'
            }
        }

         stage('Test App') {
            steps {
                sh 'mvn test jacoco:report '
            }
        }

        stage('Install packages') {
            steps {
                sh 'mvn install '
            }
        }

          stage('Code Coverage') {
            steps {
                jacoco()
            }
        }

                 stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000 -Dsonar.login=admin -Dsonar.password=jenkins -Dsonar.java.coveragePlugin=jacoco  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
            }
        }
    }
   
}

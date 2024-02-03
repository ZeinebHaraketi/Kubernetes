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
                sh 'mvn test '
            }
        }

                 stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000 -Dsonar.login=admin -Dsonar.password=jenkins'
            }
        }
    }
   
}

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
    }
   
}

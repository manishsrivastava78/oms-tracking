pipeline {
    agent { 
        kubernetes{
            label 'jenkins-slave'
        }
        
    }
   
	 environment{
        DOCKER_USERNAME = credentials('DOCKER_USERNAME')
        DOCKER_PASSWORD = credentials('DOCKER_PASSWORD')
    }
	
    stages {
        
      
        
           stage('Checkout the code') {
            steps{
                sh(script: """
                    git clone https://github.com/manishsrivastava78/oms-products.git
                """, returnStdout: true) 
            }
        }
		
		  stage('Build the code') {
            steps {
			      sh script: '''
                #!/bin/bash
                cd $WORKSPACE/oms-products/
                export M2_HOME=/usr/share/maven
                export PATH=$PATH:$M2_HOME/bin
                mvn --version
                mvn install
                '''
              }
        }
        
         stage('docker build') {
            steps{
                sh script: '''
                #!/bin/bash
                cd $WORKSPACE/oms-products/
                docker build -t manishsrivastavaggn/oms-products:${BUILD_NUMBER} .
                '''
            }
        }
        
         stage('docker login') {
            steps{
                sh(script: """
                    docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
                """, returnStdout: true) 
            }
        }     
	
             stage('Push docker image') {
            steps{
                sh(script: """
                    docker push manishsrivastavaggn/oms-products:${BUILD_NUMBER}
                """)
            }
        }
       
           stage('Deploy microservice') {
				steps{
					sh script: '''
						#!/bin/bash
						cd $WORKSPACE/oms-products/
					#get kubectl for this demo
					curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
					chmod +x ./kubectl
					./kubectl -n app apply -f ./configmap.yaml
					./kubectl  -n app apply -f ./secret.yaml
					
					cat ./deployment.yaml | sed s/changeMePlease/${BUILD_NUMBER}/g | ./kubectl  -n app  apply -f -
					 ./kubectl  -n app  apply -f ./service.yaml
					'''
				}
			}

     
}
}

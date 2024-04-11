package com.utils

class DockerUtils {
    def steps

    DockerUtils(steps) {
        this.steps = steps
    }

    def buildAndPushImage(credentialsId, toolName) {
        steps.withDockerRegistry(credentialsId: credentialsId, toolName: toolName) {
            steps.sh "docker build -t devopsWeb ."
            steps.sh "docker tag chatbot prashant4875/devopsWeb:latest"
            steps.sh "docker push prashant4875/devopsWeb:latest"
        }
    }
}

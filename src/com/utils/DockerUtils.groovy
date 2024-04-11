package com.utils

class DockerUtils {
    def steps

    DockerUtils(steps) {
        this.steps = steps
    }

    def buildAndPushImage(credentialsId, toolName) {
        steps.withDockerRegistry(credentialsId: credentialsId, toolName: toolName) {
            steps.sh "docker build -t devopsweb ."
            steps.sh "docker tag devopsweb prashant4875/devopsweb:latest"
            steps.sh "docker push prashant4875/devopsweb:latest"
        }
    }
}

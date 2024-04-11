package com.utils

class DockerUtils {
    def steps

    DockerUtils(steps) {
        this.steps = steps
    }

    def buildAndPushImage(credentialsId, toolName, buildNumber) {
        steps.withDockerRegistry(credentialsId: credentialsId, toolName: toolName) {
            steps.sh "docker build -t devopsweb ."
            steps.sh "docker tag devopsweb prashant4875/devopsweb:v${buildNumber}"
            steps.sh "docker push prashant4875/devopsweb:v${buildNumber}"
        }
    }
}

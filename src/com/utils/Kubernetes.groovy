package com.utils

class Kubernetes{
    def steps

    Kubernetes(steps){
        this.steps = steps
    }

    def kubernetesDeploy(Map config) {
        steps.withKubeConfig(
            caCertificate: config.caCertificate,
            clusterName: config.clusterName,
            contextName: config.contextName,
            credentialsId: config.credentialsId,
            namespace: config.namespace,
            restrictKubeConfigAccess: config.restrictKubeConfigAccess,
            serverUrl: config.serverUrl,
        ) {
            steps.sh "kubectl apply -f ${config.manifestFilePath}"
            steps.sh "kubectl set image deploy/devopsweb devopsweb=prashant4875/devopsweb:v${config.buildNumber} "
        }
    }
}
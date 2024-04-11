package com.utils

class BuildStage{
    def steps
    BuildStage(steps){
        this.steps = steps
    }
    def execute(){
        steps.sh 'mvn clean package'
        steps.echo 'Now Archiving...'
        steps.archiveArtifacts artifacts: '**/target/*.war'
            
        }
    }
}
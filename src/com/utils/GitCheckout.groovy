package com.utils

class GitCheckout {
    def checkout(Map config){
        def branch = config.branch ?: 'master'
        def credentialsId = config.credentialsId ?: ''
        def url = config.url ?: ''

         git branch: branch, credentialsId: credentialsId, url: url
    }
}
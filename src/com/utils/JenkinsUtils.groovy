package com.utils

import jenkins.model.*
import hudson.model.*
import com.cloudbees.hudson.plugins.folder.*

class JenkinsUtils {

    static List<String> getJobChoices() {
        def jobChoices = []
        def rootFolder = Jenkins.get().getItem('test')
        def nonprdFolder = rootFolder?.getItem('nonprd')

        if (nonprdFolder == null) {
            throw new Exception("nonprd folder not found")
        }

        def jobs = nonprdFolder.getAllItems(Job.class)

        jobs.each { job ->
            jobChoices.add(job.getFullName())
        }

        return jobChoices
    }

    static void copyJob(String sourceJobPath, String destJobPath) {
        def sourceJob = Jenkins.get().getItemByFullName(sourceJobPath, Job.class)
        if (sourceJob == null) {
            throw new Exception("Source job ${sourceJobPath} not found")
        }

        def destFolderPath = destJobPath.substring(0, destJobPath.lastIndexOf('/'))
        def destFolder = Jenkins.get().getItemByFullName(destFolderPath, Folder.class)
        if (destFolder == null) {
            destFolder = createFolders(destFolderPath)
        }

        def configFile = sourceJob.getConfigFile()
        def configXml = configFile.asString()

        def newJob = destFolder.createProjectFromXML(sourceJob.name, new ByteArrayInputStream(configXml.getBytes("UTF-8")))
        if (newJob == null) {
            throw new Exception("Failed to create job at ${destJobPath}")
        }
    }

    private static Folder createFolders(String path) {
        def folders = path.split('/')
        def parent = Jenkins.get()

        folders.each { folder ->
            def existingFolder = parent.getItem(folder)
            if (existingFolder == null) {
                parent = parent.createProject(Folder.class, folder)
            } else {
                parent = existingFolder
            }
        }

        return parent
    }
}

stage 'Build'

node {
    try { 
      
        notifyStarted()

  /* ... existing build steps ... */

        notifySuccessful()
    } catch(e) {
        currentBuild.result = "FAILED" 
        notifyFailed()
        throw e
    }
}

def notifyStarted() {
  // send to Slack
  slackSend (color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})",  teamDomain: 'textiq', tokenCredentialId: '89f69208-0304-472c-bf91-c3a5df9b9e79')

    )

def notifySuccessful() {
    //send to Slack 
    slackSend (color:'#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})",  teamDomain: 'textiq', tokenCredentialId: '89f69208-0304-472c-bf91-c3a5df9b9e79')
}

def notifyFailed() {
    //send to Slack 
    slackSend (color: '#FF0000', message:"FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", teamDomain: 'textiq', tokenCredentialId: '89f69208-0304-472c-bf91-c3a5df9b9e79')
}


}
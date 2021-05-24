def call(Map config) {
   sh "ls ${config.name}" 
}

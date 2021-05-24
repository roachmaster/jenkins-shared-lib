def call(Map config) {
   sh "cd ${config.name}" 
   sh "chmod +x ./gradlew"
   sh "./gradlew clean build"
   sh "cd ../" 
   sh "pwd"
}

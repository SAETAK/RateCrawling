cd /root/test
/usr/bin/mvn compile exec:java -Dexec.mainClass="com.inhoan.test.RateCrawling" >> /root/test/stdout.log
/usr/bin/mvn compile exec:java -Dexec.mainClass="com.inhoan.test.RateCrawling" 2>> /root/test/error.log

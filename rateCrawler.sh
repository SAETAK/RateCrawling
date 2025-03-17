cd /root/test

# Lock 파일이 존재하면 실행 중지
if [ -f "$CRAWLINGLOCK" ]; then
	echo "Script stopped failure." >> /root/test/error.log
	exit 1
fi

#빌드 실행 ( 오류 발생 시 Lock 파일 생성 )
if ! /usr/bin/mvn compile exec:java -Dexec.mainClass="com.inhoan.test.RateCrawling" >> /root/test/stdout.log 2>> /root/test/error.log; then
	touch "CRAWLINGLOCK"
	echo "Compile failed, creating lock file." >> /root/test/error.log
	exit 1
fi

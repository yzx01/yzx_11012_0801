进入Exam2目录下运行  mvn clean compile

然后运行  mvn exec:java -Dexec.mainClass="com.hand.bank.App" -Dexec.args="arg0 arg1 arg2"   开启服务端

再新建一个CMD窗口，进入Exam2目录下，运行mvn exec:java -Dexec.mainClass="com.hand.bank.Client" -Dexec.args="arg0 arg1 arg2"

运行后再Exam2目录下的document文件夹多了一个new_target.pdf文件，即为成功

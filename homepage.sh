
#!/bin/bash
#
# chkconfig: 2345 10 90
# 
# description:auto_run
 
#程序名
RUN_NAME="homepage.jar"
 
#jar 位置
JAVA_OPTS=./homepage.jar
LOG_OPTS=./debug.log
 
#开始方法
start() {
        source /etc/profile; nohup java -Xms32M -Xmx100M -XX:PermSize=32M -XX:MaxPermSize=100M -jar $JAVA_OPTS >$LOG_OPTS 2>&1 &
        echo "$RUN_NAME started success."
}
 
#结束方法
stop() {
        echo "stopping $RUN_NAME ..."
        kill -9 `ps -ef|grep $JAVA_OPTS|grep -v grep|grep -v stop|awk '{print $2}'`
}
 
case "$1" in
        start)
            start
            ;;
        stop)
            stop
            ;;
        restart)
            stop
            start
            ;;
        *)
                echo "Userage: $0 {start|stop|restart}"
                exit 1
esac
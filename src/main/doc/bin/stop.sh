echo "stop bgp-mgr"
pid=`ps -ef|grep 'java -jar bgp-mgr'|grep -v grep|awk '{print $2}'`
echo "kill $pid"
kill -s 9 $pid
echo "start bgp-mgr"
nohup java -jar bgp-mgr-0.0.1-SNAPSHOT.jar --server.port=8181 --sys.storage-dir=/data/wwwroot/sharedFile/game/ >bgp-mgr.log 2>&1 &
tail -f bgp-mgr.log
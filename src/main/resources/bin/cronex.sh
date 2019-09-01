#!/bin/bash

show_usage() {
    echo "USAGE: $0 <OPTIONS> <CRON EXPRESSION>"
    echo "OPTIONS:  -c <COUNT>          [optional] number of trigger times in the future (default: 1)"
    echo "          -s <START DATE>     [optional] start date of calculation (pattern: yyyy-MM-dd HH:mm:ss, default: 'now()')"
    echo "e.g.:     $0 '0 15 20 * * ?'"
    echo "          $0 -c 3 '0 15 20 * * ?'"
    echo "          $0 -s '2019-08-30 19:00:00' '0 15 20 * * ?'"
    exit 1
}

if [ $# -ne 1 ] && [ $# -ne 3 ] && [ $# -ne 5 ]; then
  show_usage
fi

# getopts用法: https://www.cnblogs.com/klb561/p/8933992.html
while getopts c:s: arg
do  case "$arg" in
      c)
        count="$OPTARG"
        ;;
      s)
        start="$OPTARG"
        ;;
      ?)
        show_usage
        ;;
    esac
done

shift $(($OPTIND - 1))
cron="$*"

if [ "$count" = "" ]; then
  count="1"
fi

if [ "$start" = "" ]; then
  start=$(date "+%Y-%m-%d %H:%M:%S")
fi

if [ "$cron" = "" ]; then
  show_usage
fi

cd $(dirname $0)
cd ..
DEPLOY_DIR=$(pwd)
LIB_DIR=${DEPLOY_DIR}/lib/*
APP_MAIN=com.wanghao.Main

java -classpath ${LIB_DIR}:. ${APP_MAIN} "$cron" "$count" "$start"

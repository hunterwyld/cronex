#!/bin/bash

show_usage() {
    echo "Usage: $0 [CRON_EXPRESSION]"
    echo "Optional:  -c <count>          number of trigger times in the future (default: 1)"
    exit 1
}

if [ $# -ne 1 ] && [ $# -ne 3 ]; then
  show_usage
fi

if [ $# -eq 1 ]; then
  cron="$1"
  count="1"
fi

if [ $# -eq 3 ]; then
  cron="$1"
  count="$3"
fi

if [ "$cron" = "" ] || [ "$count" = "" ]; then
  show_usage
fi

cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
LIB_DIR=${DEPLOY_DIR}/lib/*
APP_MAIN=com.wanghao.Main

java -classpath ${LIB_DIR}:. ${APP_MAIN} "$cron" $count

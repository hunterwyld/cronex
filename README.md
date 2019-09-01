## cronex
cronex(**cron** **ex**plainer) is a simple explainer for cron expressions. It is a handy tool to get the next a few trigger times according to the input cron expression.

### Usage
Download executable [here](https://github.com/hunterwyld/cronex/releases) and extract tar file

`cd bin`

Run `./cronex.sh` as the following example shows

e.g.:

```bash
USAGE: /home/wanghao/IdeaProjects/cronex/src/main/resources/bin/cronex.sh <OPTIONS> <CRON EXPRESSION>
OPTIONS:  -c <COUNT>          [optional] number of trigger times in the future (default: 1)
          -s <START DATE>     [optional] start date of calculation (pattern: yyyy-MM-dd HH:mm:ss, default: 'now()')
```

- To get next trigger time: `./cronex.sh '0 15 20 * * ?'`

- To get next 3 trigger times from now: `./cronex.sh -c 3 '0 15 20 * * ?'`

- To get next 3 trigger times from specified date: `./cronex.sh -c 3 -s '2019-08-30 19:00:00' '0 15 20 * * ?'`

Note that at most 10 future trigger times might be produced.

## cronex
cronex(**cron** **ex**plainer) is a simple explainer for cron expressions. It is a handy tool to get the next a few trigger times according to the input cron expression.

### Usage
Download executable [here](https://github.com/hunterwyld/cronex/releases/download/v1.0.0/cronex-1.0.0.tar.gz) and extract tar file

`cd bin`

Run `./cronex.sh` as the following example shows

e.g.:

```bash
Usage: ./cronex.sh [CRON_EXPRESSION]
Optional:  -c <count>          number of trigger times in the future (default: 1)
```

- To get next trigger time: `./cronex.sh '0 15 20 3 FEB/2 ?'`

- To get next 3 trigger times: `./cronex.sh '0 15 20 3 FEB/2 ?' -c 3`

Note that at most 10 future trigger times may be produced.

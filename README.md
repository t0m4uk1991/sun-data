# sunrise-sunset

Small application that uses api.sunrise-sunset.org to get information about sunrise,sunset and day duration.

Used to calculate/configure additional light and solve problem of scarcity of light for home plants at late
autumn/winter/early spring.

## Build
Execute `mvn clean install` in project root and use executable from target dir.

## Usage

edit app.properties file

```
api.url=https://api.sunrise-sunset.org
location.lat=49.8397
location.long=24.0297
location.timezone=Europe/Kiev
period.start=2020-10-01
period.duration=31
```

Run the app and get output in data.csv file:
```
sunrise,sunset,day_length
2020-10-01T07:25:17,2020-10-01T19:01:12,11:35:55
...
```

## TODO
1. Calculate sunrise/sunset based on location and do not use API.

## License
[MIT](https://choosealicense.com/licenses/mit/)
# RadiusAgent
RadiusAgent property matching problem

## Assumptions
* If a value is missing in query then -1 is substituted in place of max value.
* Following linear reduction in match match percentage when actual != expected.<br /> Eg.
If budget is 1000 and 10% is relaxed and +/-25% is limit. Then matching budget of value
between 750 - 900 or 1100 - 1250 is as follows: <br />
Error allowed = 150 (25% - 10%)<br/>
Actual error = actual value - expected value (Error is 100 for value 800) <br />
`Match = (Error allowed - Actual error + 1) / (Error allowed + 1)`

## Runing

### App.java
It creates some random properties and plot it on the map.
Specify query in main to get the results.

#### Sample Output:
`Loading time:2797ms` <br />
`Query time:2877us` <br />
`Match %99.03473725554315 Id: 160328 Bedrooms: 5 Bathrooms: 2 Price: 1130.4095466644421 Latitude: 50.499958719312566 Longitude: 8.200757155996882 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 29.034737255543156` <br />
`Match %93.52067809502282 Id: 453669 Bedrooms: 5 Bathrooms: 3 Price: 1043.0351725268188 Latitude: 50.49839638579182 Longitude: 8.204247615445183 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 23.520678095022824` <br />
`Match %92.94047179797212 Id: 593604 Bedrooms: 5 Bathrooms: 3 Price: 1196.9028055280733 Latitude: 50.49780027819659 Longitude: 8.204120259084164 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 22.94047179797212` <br />
`Match %91.35119964633007 Id: 538640 Bedrooms: 5 Bathrooms: 1 Price: 1058.8718281180527 Latitude: 50.49939010699708 Longitude: 8.20119318160647 Bath% 13.333333333333332 Bed% 20.0 Budget% 30.0 Dis% 28.01786631299675` <br />
`Match %90.64922252863457 Id: 771143 Bedrooms: 5 Bathrooms: 3 Price: 1719.6871078176296 Latitude: 50.49980130927099 Longitude: 8.207109160100458 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 20.64922252863456` <br />
`Match %86.67524177478977 Id: 699187 Bedrooms: 4 Bathrooms: 3 Price: 1233.7931799721487 Latitude: 50.497236586585146 Longitude: 8.20262318447729 Bath% 20.0 Bed% 13.333333333333332 Budget% 30.0 Dis% 23.341908441456443` <br />
`Match %86.66453035863057 Id: 208570 Bedrooms: 5 Bathrooms: 3 Price: 1955.4169601265196 Latitude: 50.49476633300476 Longitude: 8.20591961364994 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 16.664530358630564` <br />
`Match %86.51427393848786 Id: 740466 Bedrooms: 5 Bathrooms: 2 Price: 1327.871689626633 Latitude: 50.49365436000111 Longitude: 8.2023543091058 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 16.51427393848786` <br />
`Match %85.35001316784627 Id: 132093 Bedrooms: 5 Bathrooms: 2 Price: 1228.3734258639288 Latitude: 50.493908982054165 Longitude: 8.205677713846626 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 15.35001316784627` <br />
`Match %85.0003782961819 Id: 395 Bedrooms: 5 Bathrooms: 2 Price: 1715.1410616490612 Latitude: 50.49715570672357 Longitude: 8.210483675137084 Bath% 20.0 Bed% 20.0 Budget% 30.0 Dis% 15.00037829618191` <br />

## Query Time:
100 - 500 us <br/>
Enable commented code in App.java to test.
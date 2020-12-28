#!/usr/bin/env python

import memcache
mc = memcache.Client(['127.0.0.1:5701'], debug=0)

city = {
  "name": "London",
  "country": "GB",
  "population": 8174100
}

mc.set("London-GB", city)
london = mc.get("London-GB")

print london

(ns b1tw1se.test
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint
		b1tw1se.models.topic)
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))
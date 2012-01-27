(ns b1tw1se.models.test-topic
	(:require [b1tw1se.models.topic :as topic]
        [b1tw1se.config.database :as database]
		[b1tw1se.test-support :as support])
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint)
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))

; (deftest find-)

(deftest create-topic )
	; (with-test-mongo
	; 	(let [b ])))
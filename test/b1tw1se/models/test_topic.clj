(ns b1tw1se.models.test-topic
	(:require [b1tw1se.models.topic :as topic]
		[b1tw1se.models.board :as board]
        [b1tw1se.config.database :as database])
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint
		b1tw1se.test-support)
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))

(deftest create-topic 
	(with-test-mongo
		(let [t "What are some things to do in Denver when one is dead?"
			  brdid get-seed-board-id
			  subj (topic/create t brdid)
			  brd (board/find-one brdid)]
			(is (= 1 (count (:posts brd)))))))
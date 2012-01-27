(ns b1tw1se.models.test-board
	(:require [b1tw1se.models.board :as board])
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint
		b1tw1se.test-support)
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))

(deftest find-all-boards
	(with-test-mongo
		(let [results (board/find-all)]
			(is (= 1 (count results)))
			(is (= seed-board-title (:title (first results)))))))
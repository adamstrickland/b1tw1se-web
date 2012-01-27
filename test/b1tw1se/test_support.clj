(ns b1tw1se.test-support
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint)
	(:require [b1tw1se.config.database :as database])
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))

(def test-db-host "127.0.0.1")

(def test-db "b1tw1setestdb")

(def test-db-port 27017)

(def test-connection (make-connection test-db :host test-db-host :port test-db-port))

(def seed-board-title "The Increasingly Inaccurately-Named Hitchhiker's Guide To The Galaxy Trilogy")

(def get-seed-board-id (with-mongo test-connection (first (map #(str (:_id %)) (fetch :boards)))))

(def seed-board {:title seed-board-title :topics []})

(defn seed! []
	(let [a (make-connection test-db :host test-db-host)]
		(with-mongo a
			(insert! :boards seed-board))))

(defn setup! [] 
	(mongo! :db test-db :host test-db-host)
	(seed!))

(defn teardown! []
	(drop-database! test-db))

(defmacro with-test-mongo [& body]
	`(do
		(setup!)
		(binding [database/connection test-connection]
			~@body)
		(teardown!)))
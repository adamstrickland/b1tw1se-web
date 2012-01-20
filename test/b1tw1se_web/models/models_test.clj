(ns b1tw1se.test.models
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint
		b1tw1se.models.topic)
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))

(def test-db-host "127.0.0.1")

(def test-db "b1tw1setestdb")

(defn setup! [] 
	(mongo! :db test-db :host test-db-host)
	(seed!))

(defn teardown! []
	(drop-database! test-db))

(defmacro with-test-mongo [& body]
	`(do
		(setup!)
		~@body
		(teardown!)))

; (deftest update
;   (with-test-mongo
;     (make-points!)
;     (let [point-id (:_id (fetch-one :points))]
;       (update! :points
;                {:_id point-id}
;                {:x "suffusion of yellow"})
;       (is (= (:x (fetch-one :points
;                             :where {:_id point-id}))
;              "suffusion of yellow")))))

; (defn create [title board_id]
; 	(with-mongo conn/init
; 		(let [b (fetch-by-id :boards (object-id board_id))]
; ; (merge t {:topics (cons {:title "dbag 2"} (:topics t))})
;       (update! :boards b (merge b {:topics (cons {:_id (uuid) 
;                                                   ; :posts [(post/create content user)]
;                                                   :title title } (:topics b))})))))

; (let [a (make-connection "congomongotest-db-a" :host test-db-host)
;           b (make-connection "congomongotest-db-b" :host test-db-host)]
;       (with-mongo a
;         (testing "with-mongo sets the mongo-config"
;           (is (= "congomongotest-db-a" (.getName (*mongo-config* :db)))))
;         (testing "mongo! inside with-mongo stomps on current config"
;           (mongo! :db "congomongotest-db-b" :host test-db-host)
;           (is (= "congomongotest-db-b" (.getName (*mongo-config* :db))))))
;       (testing "and previous mongo! inside with-mongo is visible afterwards"
;         (is (= "congomongotest-db-b" (.getName (*mongo-config* :db))))))))

(def seed-board-title "The Increasingly Inaccurately-Named Hitchhiker's Guide To The Galaxy Trilogy")

(def seed-board {:title seed-board-title :topics []})

(def seed!
	(let [a (make-connection test-db :host test-db-host)]
		(with-mongo a
			(insert! :boards seed-board))))

(deftest create-topic
	(with-test-mongo
		(let [b ])))
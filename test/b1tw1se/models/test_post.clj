(ns b1tw1se.models.test-post
	(:require [b1tw1se.models.post :as post])
	(:use clojure.test
		somnium.congomongo
		somnium.congomongo.config
		somnium.congomongo.coerce
		clojure.pprint
		b1tw1se.test-support)
	(:use [clojure.data.json :only (read-json json-str)])
	(:import [com.mongodb BasicDBObject BasicDBObjectBuilder]))

(deftest find-all
	(with-test-mongo
		(let [results (post/find-all)]
			(is (= 1 (count results)))
			(is (= post-title (:title (first results))))
			(is (= get-seed-board-id (:board_id (first results)))))))

(deftest find-all-for-board
	(with-test-mongo
		(let [results (post/find-all-for-board get-seed-board-id)]
			(is (= 1 (count results)))
			(is (= post-title (:title (first results))))
			(is (= get-seed-board-id (:board_id (first results)))))))

(deftest find-one
	(with-test-mongo
		(let [postid 
			  result (post/find-one postid)]
			)))

; (deftest find-all
; 	(with-test-mongo
; 		(let [results (board/find-all)]
; 			(is (= 1 (count results)))
; 			(is (= seed-board-title (:title (first results)))))))

; (deftest find-one
; 	(with-test-mongo
; 		(let [brdid get-seed-board-id
; 			  result (board/find-one (str brdid))]
; 			(is (= seed-board-title (:title result))))))

; (deftest create
; 	(with-test-mongo
; 		(let [t "Flurb"]
; 			(is (= t (:title (board/create t)))))))